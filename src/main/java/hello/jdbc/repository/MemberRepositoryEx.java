package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

// 잘못된 인터페이스 예시
// 인터페이스가 특정 기술(JDBC)에 종속되어 있다.
// 변경을 줄이려고 인터페이스를 사용하는데, 기술이 바뀌면 인터페이스부터 다 고쳐야하는 것!!
public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;
    Member findById(String memberId) throws SQLException;
    void update(String memberId, int money) throws SQLException;
    void delete(String memberId) throws SQLException;
}
