package bucket.list.service;

import bucket.list.domain.BoardInfo;
import bucket.list.repository.TopMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopMenuService {

    private final TopMenuRepository topMenuRepository;

    @Autowired
    public TopMenuService(TopMenuRepository topMenuRepository) {
        this.topMenuRepository = topMenuRepository;
    }

    public List<BoardInfo> allInfoName() {
        List<BoardInfo> boardInfo = topMenuRepository.allInfoName();
        return boardInfo;
    }
}
