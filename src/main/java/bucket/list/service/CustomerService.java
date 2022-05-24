package bucket.list.service;

import bucket.list.domain.About;
import bucket.list.domain.Customer;
import bucket.list.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//고객센터 서비스
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //고객센터 게시글저장
    public void Save(Customer customer, MultipartFile file) throws IOException {

        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(path, fileName);

        file.transferTo(saveFile);

        customer.setCustomerFile(fileName);
        customer.setCustomerFilepath("/files/" + fileName);

        customerRepository.save(customer);
    }

    //고객센터 전체게시글보여주는 곳
    @Transactional
    public Page<Customer> allContentList(Pageable pageable) {

        Page<Customer> page = customerRepository.findAll(pageable);
        return page;
    }

    //하나의 게시글읽는곳
    @Transactional
    public Customer  oneContentList(int customerIdx){
        Customer customer = customerRepository.findById(customerIdx).get();

        return customer;
    }
    //게시글 삭제 서비스
    @Transactional
    public void deleteContent(int customerIdx){
        customerRepository.deleteById(customerIdx);


    }
}
