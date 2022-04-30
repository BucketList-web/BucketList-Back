package spring.basic.demo.repository;

import spring.basic.demo.domain.Member;

public interface MemberRepositoryInterface {

    void saveMember(Member m);
    Member findById(int id);
}
