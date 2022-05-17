package spring.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.demo.domain.CreateLogin;
import spring.basic.demo.repository.CreateLoginRepositoryInterface;

@Service     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class CreateLoginService {

    private CreateLoginRepositoryInterface repository;

    @Autowired
    public CreateLoginService(CreateLoginRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
        // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }


    public void CreateLoginCreate(CreateLogin m){                 // join == (repository)saveMember

        repository.CreateLoginCreate(m);
    }

}
