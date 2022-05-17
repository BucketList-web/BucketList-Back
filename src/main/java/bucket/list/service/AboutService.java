package bucket.list.service;

import bucket.list.domain.About;
import bucket.list.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService {

    private final AboutRepository aboutRepository;

    @Autowired
    public AboutService(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    public About Save(About about) {
        About save = aboutRepository.Save(about);

        return save;
    }

    public List<About> allContentList() {
        List<About> about_items = aboutRepository.allContentList();
        return about_items;
    }
}
