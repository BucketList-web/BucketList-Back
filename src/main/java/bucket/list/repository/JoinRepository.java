package bucket.list.repository;

import bucket.list.domain.Join;

import java.util.List;


public interface JoinRepository {

    Join save(Join join);

    List<Join> AllContentList();

    Join oneContentList(int number);

   void updateContentInfo(Join join);
}
