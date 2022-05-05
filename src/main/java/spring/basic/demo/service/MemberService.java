package spring.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import spring.basic.demo.domain.Member;
import spring.basic.demo.repository.MemberRepositoryInterface;

import java.util.List;

// @Service     spring bean 사용하여 따로 설정했으므로 삭제해야함
public class MemberService {

    private MemberRepositoryInterface repository;

    @Autowired
    public MemberService(MemberRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
                                                                    // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }

    public void join(Member m){                 // join == (repository)saveMember

        repository.saveMember(m);
    }

    public Member findMemberBuId(int id){       // findMemberBuId == (repository)findById

        return repository.findById(id);
    }

    public List<Member> findAll(){

        return repository.findAll();            // repository에 findAll 함수 호출
    }
}
