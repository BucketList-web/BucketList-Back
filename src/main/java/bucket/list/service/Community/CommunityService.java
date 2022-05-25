package bucket.list.service.Community;

import bucket.list.domain.Community;
import bucket.list.domain.CreateLogin;
import bucket.list.domain.User;
import bucket.list.repository.Community.CommunityJpaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class CommunityService {

    private CommunityJpaRepositoryInterface repository;

    @Autowired
    public CommunityService(CommunityJpaRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
        // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }


    public Community createCommunity(Community community, MultipartFile file) throws IOException {             // 작성

        if(file.isEmpty()) {
            return repository.save(community);
        }else{
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\community";        // 파일 경로 저장

            UUID uuid = UUID.randomUUID();      // 식별자 , 파일 이름을 랜덤으로 저장하기 위해

            String filename = uuid + "_" + file.getOriginalFilename();      // 파일 이름 변수에 랜덤이름 저장 => uuid(랜덤 이름)_업로드한파일명

            File saveFile = new File(projectPath, filename);   // projectpath 경로에 name이름으로 저장할것

            file.transferTo(saveFile);

            community.setCommunityfilename(filename);                   // 파일 이름  db 저장
            community.setCommunityfilepath("/files/community/" + filename);         // 파일 경로 db 저장
            return repository.save(community);
        }

    }

    public void createeditCommunity(Community m){             // 작성
        repository.save(m);
    }

    public Community showCommunityById(int id){       // 해당 게시물 출력
        return repository.findById(id).get();
    }

    public Page<Community> showCommunityAll(Pageable pageable){      // 전체 게시물 출력
        Page<Community> communities = repository.findAll(pageable);
        return communities;            // repository에 findAll 함수 호출
    }

    public Community commumitymodify(int id) {          // 게시물 수정
        return repository.findById(id).get();
    }

    public List<Community> selectAllSQL(String name){       // 아이디를 통해 나머지 정보 구하기
        return repository.selectAllSQL(name);
    }

    public String selectIdSQL(int id){
        return repository.selectIdSQL(id);
    }

    public void communitydelete(int id){           // 게시물 삭제
        repository.deleteById(id);
    }

//   public void commumitywrite(Community m){
//        repository.writecommunity(m);
//    }



}
