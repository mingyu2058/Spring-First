package first.firstspring.service;

import first.firstspring.domain.Member;
import first.firstspring.repository.MemberRepository;
import first.firstspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// autowired할 수 있게 spring이 올라올 때 memberservice를 등록해줌
// Component scan 사용
//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //memberservice는 meberrepository가 필요하니까
    //@Autowired

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member);
        //있으면 꺼낸다. 그냥 get해도 되지만 권장x

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
