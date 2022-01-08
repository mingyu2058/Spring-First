package first.firstspring.service;

import first.firstspring.domain.Member;
import first.firstspring.repository.MemberRepository;
import first.firstspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest

// test를 실행할 때 트랜젝션 실행 후 data를 롤백 시켜줌 
// so, 초기화 안 하고 회원가입, 등등 테스트 사용이 가능 다음 테스트에 영향x
@Transactional 
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;

    @Autowired
    MemberRepository memberRepository;



    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName())
                .isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예약() {
        //given
        Member member1 = new Member();
        member1.setName("spring3");

        Member member2 = new Member();
        member2.setName("spring3");

        //when
/*      방법 1
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재");
        }
*/
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재");

        //then

    }

}