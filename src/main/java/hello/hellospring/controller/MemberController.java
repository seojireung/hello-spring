package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
