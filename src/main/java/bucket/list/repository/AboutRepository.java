package bucket.list.repository;

import bucket.list.domain.About;

import java.util.List;

public interface AboutRepository {

    About Save(About about);

    List<About> allContentList();

}
