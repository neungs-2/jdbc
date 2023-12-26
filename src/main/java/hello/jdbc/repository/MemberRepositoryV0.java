package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;


/**
 * JDBC - DriverManager 사용 (Low한 레벨)
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null; // Statement를 상속, ?에 파라미터를 바인딩하는 기능 있음

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 그냥 close 문 3개만 위치하면 한 곳에서 close하다가 오류났을 때 다른 자원들은 close되지 않아서 아래와 같이 작성
     * 리소스 정리 중요함!
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
       if (rs != null) {
           try {
               rs.close();
           } catch (SQLException e) {
               log.info("error", e);
           }
       }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
