package first.firstspring.service;

import first.firstspring.domain.Member;
import first.firstspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    //초기화 위해서
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }



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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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

    @Test
    void findOne() {
    }
}