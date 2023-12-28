package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());

    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        // DataSource 사용시 URL, USERNAME, PASSWORD를 한번만 넘기면 된다!!
        // 설정과 사용을 분리!!
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target)
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        useDataSource(dataSource);
        Thread.sleep(1000); //커넥션 풀에서 커넥션 생성 시간 대기
    }

    private void useDataSource(DataSource datasource) throws SQLException {
        Connection con1 = datasource.getConnection();
        Connection con2 = datasource.getConnection();
//        Connection con3 = datasource.getConnection();
//        Connection con4 = datasource.getConnection();
//        Connection con5 = datasource.getConnection();
//        Connection con6 = datasource.getConnection();
//        Connection con7 = datasource.getConnection();
//        Connection con8 = datasource.getConnection();
//        Connection con9 = datasource.getConnection();
//        Connection con10 = datasource.getConnection();
//        Connection con11 = datasource.getConnection();
        log.info("\nconnection={}, \nclass={}", con1, con1.getClass());
        log.info("\nconnection={}, \nclass={}", con2, con2.getClass());
    }
}
