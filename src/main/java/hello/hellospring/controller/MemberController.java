package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // 객체에 별 기능이 없음 -> 여러 개의 인스턴스 생성 할 필요x
    // --> 스프링 컨테이너에 하나만 등록해서 사용
    private final MemberService memberService;

    // memberService를 스프링이 스프링 컨테이너에 bean으로 등록된 memberService 가져다가 연결.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // creatMemberForm.html의 form -> post 방식, 매핑주소 /members/new
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원가입 끝나고 홈화면으로.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList.html";
    }


}
