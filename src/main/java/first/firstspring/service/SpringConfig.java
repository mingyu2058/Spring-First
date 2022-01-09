package first.firstspring.service;

import first.firstspring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //Jdbc,Jdbc template 사용할 떄
    //private DataSource dataSource;

    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.em = em;
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
        //return new JdbcMemberRepository(dataSource);

        //Jdbctemplate 사용
        //return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);
    }
}
