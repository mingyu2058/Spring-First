package first.firstspring.service;

import first.firstspring.repository.JdbcMemberRepository;
import first.firstspring.repository.MemberRepository;
import first.firstspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean // 스프링이 뜰 때 읽고 bean에 등록해줌
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

        //메모리 사용
        //return new MemoryMemberRepository();


        // DB로 연결할 때 얘마 dbmemoryrepository 수정하면 됨
        return new JdbcMemberRepository(dataSource);
    }
}
