package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // DataSource : 데이터베이스 커넥션을 획득할 때 사용하는 객체
    // 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource를 생성하고 스프링 빈으로 만들어 둠.
    // -> 의존성 주입 가능
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService( ){
        // 아래 스프링 빈에 등록된 memberRepository 리턴
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);

    }


}
