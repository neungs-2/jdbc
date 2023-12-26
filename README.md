[MAC H2 실행법]
- 디렉토리 이동: `cd bin`
- 권한 주기: `chmode 755 h2.sh`
- 실행: `./h2.sh`
  - 사용자명: `sa`
  - JDBC URL
    - 최초 실행: `jdbc:h2:~/test`
    - 최초 이후 실행: `jdbc:h2:tcp://localhost/~/test`
