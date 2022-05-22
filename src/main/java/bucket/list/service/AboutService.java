package bucket.list.service;

import bucket.list.domain.About;
import bucket.list.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AboutService {

    private final AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    //글작성
    @Transactional
    public void save(About about, MultipartFile file) throws IOException {

        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(path, fileName);

        file.transferTo(saveFile);

        about.setAbout_file(fileName);
        about.setAbout_filepath("/files/" + fileName);

        aboutRepository.save(about);


    }
    //게시글리스트 처리, 최신글 정렬
    //페이징구현하기위해 Pageable을 매개변수입력
    public Page<About> allContentList(Pageable pageable) {

        Page<About> page = aboutRepository.findAll(pageable);
        return page;
    }

    //특정게시글 보는 메서드
    public About oneContentList(Integer aboutnumber) {
        About about = aboutRepository.findById(aboutnumber).get();

        return about;
    }
}
