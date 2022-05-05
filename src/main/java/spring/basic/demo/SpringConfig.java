package spring.basic.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.basic.demo.repository.JdbcBoardRepository;
import spring.basic.demo.repository.BoardRepositoryInterface;
import spring.basic.demo.service.BoardService;

import javax.sql.DataSource;

@Configuration      // 사용하는 java bean을 모아둠 (Controller는 제외한 나머지 @~ 값들)  - spring bean 설정 파일 하는 법
                    // (1) 프로젝트 진행할때 다른 사람들 코드와 합쳐지면 내가 사용하는 코드들을 구분하기 위해
                    // (2) 만약 db가 연결이 에러가 발생하게 되면 다른 db에 연결하여 bean설정하여 다른 길로 유도함
                    // configuration을 사용하면 각 페이지에 @repository, @service로 설정한 것들을 지워도 됨 
public class SpringConfig {


    private DataSource dataSource;      // spring에서 알아서 dataSource안에 DB에 저장된 데이터를 가져옴

    public SpringConfig(DataSource dataSource){

        this.dataSource = dataSource;
    }

    @Bean
    public BoardService BoardService() {           // 사용할 서비스는
        return new BoardService(BoardRepositoryInterface());  // MemberService()이다. 서비스는 repository에서 데이터를 가져옴
    }

    @Bean
    public BoardRepositoryInterface BoardRepositoryInterface() {      // 사용할 repository는
        return new JdbcBoardRepository(dataSource);                  // JdbcMemberRepository()이다. repository는 DB에서 데이터를 가져옴
    }
}
