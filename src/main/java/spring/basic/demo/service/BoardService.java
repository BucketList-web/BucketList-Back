package spring.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.demo.domain.Community;
import spring.basic.demo.repository.BoardRepositoryInterface;

import java.util.List;

@Service     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class BoardService {

    private BoardRepositoryInterface repository;

    @Autowired
    public BoardService(BoardRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
        // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }


    public void createCommunity(Community m){                 // join == (repository)saveMember

        repository.writecommunity(m);
    }

    public Community showCommunityById(int id){       // findMemberBuId == (repository)findById

        return repository.findcommunityById(id);
    }

    public List<Community> showCommunityAll(){

        return repository.findcommunityAll();            // repository에 findAll 함수 호출
    }

    public void commumitymodify(Community community) {
        repository.communitymodify(community);
    }

    public void communitydelete(Community community){
        repository.communitydelete(community);
    }

//   public void commumitywrite(Community m){
//        repository.writecommunity(m);
//    }



}
