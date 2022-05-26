package bucket.list.controller;


import bucket.list.domain.About;
import bucket.list.domain.Customer;
import bucket.list.domain.Login;
import bucket.list.service.Customer.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //전체 게시글 리스트 보여주는 곳
    @GetMapping()
    public String customer(Model model,@PageableDefault(page = 0, size = 10, sort = "customerIdx",direction = Sort.Direction.DESC) Pageable pageable){


        Page<Customer> customer_items = customerService.allContentList(pageable);


        //현재 페이지 변수 Pageable 0페이지부터 시작하기 +1을해줘서 1페이지부터 반영한다
        int nowPage = customer_items.getPageable().getPageNumber() +1;
        //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
        int startPage =Math.max(nowPage-4,1) ;
        //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
        //그렇기에 getTotalpage를  min으로설정)
        int endPage = Math.min(nowPage + 5,  customer_items.getTotalPages()) ;

        model.addAttribute("customer_items", customer_items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "/customer/main";
    }
    //고객센터 글작성 보여주는 메서드
    @GetMapping("/write")
    public String writeForm(){
        return "/customer/write";
    }
    //고객센터 글작성폼에서 넘어오는 메서드
    @PostMapping("/write")
    public String writeForm(HttpServletRequest request, @ModelAttribute("customer") Customer customer, MultipartFile file) throws IOException {


        HttpSession session = request.getSession();
        if(session != null){
            customer.setCustomerWriter(((Login)session.getAttribute("loginMember")).getId());
            customerService.Save(customer, file);
        }


        return "redirect:/customer";
    }

    //고객센터 하나의 게시글보여주고 비밀글여부확인
    @GetMapping("/{customerIdx}/read")
    public String readOrSecretRead(@PathVariable int customerIdx, Model model,HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionwriter = ((Login)session.getAttribute("loginMember")).getId();   // sessionwriter에 현재 세션의 user이름을 넣어줌
        String dbwriter = customerService.selectIdSQL(customerIdx);       // 해당 게시물의 writer 정보를 dbwriter에 저장함


        Customer customer = customerService.oneContentList(customerIdx);

        if(customer.getCustomerSecret().equals("f")){
            model.addAttribute("customer", customer);
            return "/customer/secretread";
        }else {

            if(sessionwriter.equals(dbwriter)){
                model.addAttribute("dbwriter", dbwriter);
                model.addAttribute("customer",customer);

                return "/customer/read";
            }else{
                model.addAttribute("customer",customer);

                return "/customer/read";
            }

        }
    }
    //비밀글에서 비밀번호 입력
    @PostMapping("/{customerIdx}/read")
    public String secretReadConfirm(@RequestParam("customerPassword")String customerPassword,
                                    @RequestParam("customerIdx")int customerIdx,Model model,
                                    HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionwriter = ((Login)session.getAttribute("loginMember")).getId();   // sessionwriter에 현재 세션의 user이름을 넣어줌
        String dbwriter = customerService.selectIdSQL(customerIdx);       // 해당 게시물의 writer 정보를 dbwriter에 저장함

        Customer customer = customerService.oneContentList(customerIdx);

        if(customer.getCustomerPassword().equals(customerPassword)){

            if(sessionwriter.equals(dbwriter)){
                model.addAttribute("dbwriter", dbwriter);
                model.addAttribute("customer",customer);

                return "/customer/read";
            }else{
                model.addAttribute("customer",customer);

                return "/customer/read";
            }


        }else{

            model.addAttribute("customerIdx", customerIdx);
            return "/customer/readfail";
        }
    }

    //글수정 폼으로 이동하는 메서드
    @GetMapping("/edit/{customerIdx}")
    public String editForm(@PathVariable int customerIdx, Model model){
        Customer customer = customerService.oneContentList(customerIdx);
        model.addAttribute("customer", customer);
        model.addAttribute("customerIdx", customerIdx);
        return "customer/edit";
    }

    @PostMapping("/edit/{customerIdx}")
    //실제 게시글수정, 파일이미지 업로드
    public String edit(@ModelAttribute("customer") Customer customer, @PathVariable int customerIdx, MultipartFile file) throws IOException {
        customerService.Save(customer,file);
        return "redirect:/customer/";
    }


    //고객센터 글삭제 컨트롤러
    //게시글 삭제
    @GetMapping("/delete/{customerIdx}")
    public String delete(@PathVariable int customerIdx){
        customerService.deleteContent(customerIdx);

        return "redirect:/customer";
    }



}
