package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // aop 필요성1) 시간 측정 로직과 핵심 비즈니스 로직이 섞여 있어 유지보수 어려움.
        //long start = System.currentTimeMillis();

        //try {
            // 중복 회원 검증
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    // 메서드 길어서 따로 뽑음
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            // ifPresent() : Optional<>의 메서드 (값이 존재하면~)
                // findByName()의 결과가 Optional<Member>형임.
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        // aop 필요성2) 시간 측정 로직 변경 시 모든 로직을 찾아가면서 변경해야 하는 번거로움.
        //long start = System.currentTimeMillis();

        //try {
            return memberRepository.findAll();
//        } finally {
//           long finish = System.currentTimeMillis();
//           long timeMs = finish - start;
//           System.out.println("find = " + timeMs + "ms");
//        }
    }

    /**
    * 회원 조회
    */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
