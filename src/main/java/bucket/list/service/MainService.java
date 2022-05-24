package bucket.list.service;

import bucket.list.domain.About;
import bucket.list.domain.Participation;
import bucket.list.repository.AboutRepository;
import bucket.list.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


//메인화면에서 참여하기게시판에 베스트 게시글 3개만보여주기
@Service
public class MainService {

    private final ParticipationRepository participationRepository;
    private final AboutRepository aboutRepository;

    @Autowired
    public MainService(ParticipationRepository participationRepository, AboutRepository aboutRepository) {
        this.participationRepository = participationRepository;
        this.aboutRepository = aboutRepository;
    }

    public List<Participation> bestContent(){
        List<Participation> bestContents = participationRepository.findTop3ByOrderByCountDesc();

//        List<Participation> bestContents = participationRepository.findTop3ByOrderByparticipation_costDesc();
        return bestContents;
    }

    public List<About> newContent(){
        List<About> newContents = aboutRepository.newContent();

        return newContents;
    }
}
