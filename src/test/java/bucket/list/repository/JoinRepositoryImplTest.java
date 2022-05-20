package bucket.list.repository;

import bucket.list.domain.About;
import bucket.list.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryImplTest {

    @Autowired
    AboutRepositoryImpl aboutRepository;
    
    @Test
    public void 잘가지고오는지(){
        List<About> abouts = aboutRepository.allContentList();

        About about = abouts.get(0);

        int about_number = about.getAbout_number();

        System.out.println("about_number = " + about_number);


    }

    
}