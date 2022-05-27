package bucket.list.service.about;

import bucket.list.domain.About;
import bucket.list.repository.about.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AboutService {
    @Value("${file.dir}")
    private String fileDir;

    private final AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    //글작성
    @Transactional
    public void save(About about, MultipartFile file) throws IOException {

//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        boolean noneFIle = file.isEmpty();

        if(!noneFIle) {

            String path = fileDir;

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(path, fileName);
            file.transferTo(saveFile);

            about.setAbout_file(fileName);


            aboutRepository.save(about);


        }else{
            about.setAbout_file(null);
            aboutRepository.save(about);

        }


    }
    //게시글리스트 처리, 최신글 정렬
    //페이징구현하기위해 Pageable을 매개변수입력
    public Page<About> allContentList(Pageable pageable) {

        Page<About> page = aboutRepository.findAll(pageable);
        return page;
    }

    //특정게시글 보는 메서드
    public About oneContentList(Integer aboutnumber) {

//        File file = new File()

        About about = aboutRepository.findById(aboutnumber).get();

        return about;
    }

    @Transactional
    //글삭제메서드
    public void deleteContent(Integer aboutnumber){
        aboutRepository.deleteById(aboutnumber);

    }

    @Transactional
    public String selectIdSQL(int aboutnumber){
        return aboutRepository.selectIdSQL(aboutnumber);
    }


}
