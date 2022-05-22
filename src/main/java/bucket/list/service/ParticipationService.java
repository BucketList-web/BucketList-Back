package bucket.list.service;

import bucket.list.domain.Participation;
import bucket.list.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    //글저장 메서드
    @Transactional
    public void save(Participation participation, MultipartFile file) throws IOException {
        
        //파일의 저장경로를 위한 경로설정
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        
        //이미지파일 중복을 방지하기위해 uuid설정
        UUID uuid = UUID.randomUUID();

        //filename의 uuid + 파일명
        String fileName = uuid + "_" + file.getOriginalFilename();

        
        File saveFile = new File(path, fileName);

        //파일전송
        file.transferTo(saveFile);

        participation.setParticipation_file(fileName);
        participation.setParticipation_filepath("/files/" + fileName);

        participationRepository.save(participation);
    }
    //전체게시글
    public Page<Participation> AllContentList(Pageable pageable) {
        Page<Participation> participation = participationRepository.findAll(pageable);
        return participation;
    }
    //하나의 게시글
    public Participation oneContentList(Integer participationidx) {
        Participation participation = participationRepository.findById(participationidx).get();
        return participation;
    }

    //글삭제 메서드
    @Transactional
    public void deleteContent(Integer participationidx){
        participationRepository.deleteById(participationidx);
    }

    //조회수 증가 메서드
    @Transactional
    public int updateCount(Integer participationidx){
        Integer count = participationRepository.updateCount(participationidx);
        return count;

    }
}
