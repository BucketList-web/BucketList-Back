package spring.basic.demo.repository;

import org.springframework.stereotype.Repository;
import spring.basic.demo.domain.Member;

import java.util.HashMap;
import java.util.Map;


public class MemberRepository implements MemberRepositoryInterface{
    // DB대신 MAP 사용하여 데이터 저장
    public static Map<Integer, Member> dbMap = new HashMap<>();
    public static int index = 0;

    @Override
    public void saveMember(Member m) {
        m.setId(index++);               // 기준값 설정
        dbMap.put(m.getId(),m);
    }

    @Override
    public Member findById(int id) {            // 기준값을 비교하여 member값을 출력함

        return dbMap.get(id);
    }
}
