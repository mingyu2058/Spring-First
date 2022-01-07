package first.firstspring.controller;

import first.firstspring.domain.Member;
import first.firstspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// component 스캔 방법 (component와 관련된 anntation이 있으면 스프링이 객체를 생성해서 등록
// component 스캔 방법(@controller,@service,@repository)은 스프링 빈으로 자동 등록된다.
@Controller
public class MemberController {


    //필드 주입 잘 사용x
    //@Autowired private  MemberService memberService;
    private final MemberService memberService;

    /*
     setter injection , 이 경우는 public으로 열려 있어야 해서 효울 x
     @Autowired
     public void setMemberService(MemberService memberService){
          this.memberService = memberService;
     }
     */

    //autowired 있으면 memberserive를 연결해줌 - 의존관계 주입, 연관관계
    //DI(Dependent Injection
    //생성자 주입
    @Autowired 
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/members/new") // 그냥 URL에서 넘어올 때는 getmapping
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 타고 넘어와 data를 주면 postmapping
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
