package bucket.list.service.Community;

import bucket.list.domain.Community;
import bucket.list.domain.CreateLogin;
import bucket.list.domain.Participation;
import bucket.list.domain.User;
import bucket.list.repository.Community.CommunityJpaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class CommunityService {

    @Value("${file.dir}")
    private String fileDir;

    private CommunityJpaRepositoryInterface repository;

    @Autowired
    public CommunityService(CommunityJpaRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
        // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }

    @Transactional
    public void createCommunity(Community community, MultipartFile file) throws IOException {             // 작성

        //파일의 저장경로를 위한 경로설정
        boolean noneFIle = file.isEmpty();

        if (!noneFIle) {
            String path = fileDir;

            //이미지파일 중복을 방지하기위해 uuid설정
            UUID uuid = UUID.randomUUID();

            //filename의 uuid + 파일명
            String fileName = uuid + "_" + file.getOriginalFilename();


            File saveFile = new File(path, fileName);

            //파일전송
            file.transferTo(saveFile);

            community.setCommunityfile(fileName);

            repository.save(community);
        } else {
            community.setCommunityfile(null);
            repository.save(community);
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
