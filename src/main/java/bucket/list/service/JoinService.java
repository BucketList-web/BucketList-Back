package bucket.list.service;

import bucket.list.domain.Join;
import bucket.list.repository.JoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinService {

    private final JoinRepository joinRepository;

    @Autowired
    public JoinService(JoinRepository joinRepository) {
        this.joinRepository = joinRepository;
    }

    public Join save(Join join) {
        //게시글 저장
        Join save = joinRepository.save(join);
        return save;
    }
    public List<Join> AllContentList() {
        //전체게시글
        List<Join> Joins = joinRepository.AllContentList();
        return Joins;
    }

    public Join oneContentList(int number) {
        //하나의 게시글
        Join join = joinRepository.oneContentList(number);
        return join;
    }
    public void updateContentInfo(Join join) {
        joinRepository.updateContentInfo(join);

    }
}
