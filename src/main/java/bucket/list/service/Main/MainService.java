package bucket.list.service.Main;

import bucket.list.domain.Participation;

import bucket.list.repository.Participation.ParticipationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//메인화면에서 참여하기게시판에 베스트 게시글 3개만보여주기
@Service
public class MainService {

    private final ParticipationRepository participationRepository;


    @Autowired
    public MainService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;

    }

    public List<Participation> bestContent(){
        List<Participation> bestContents = participationRepository.findTop3ByOrderByCountDesc();

        return bestContents;
    }

}
