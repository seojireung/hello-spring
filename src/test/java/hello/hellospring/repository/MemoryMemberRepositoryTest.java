package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    // 테스트
    // new 객체랑 db에서 꺼낸 거 같은지 비교

    // Member 저장하기
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional에서 값을 꺼낼 때 get() 사용
        // get()이 좋은 방법은 아닌데 테스트 시 간단 사용
        Member result = repository.findById(member.getId()).get();
        // Assertions
        //
        assertThat(member).isEqualTo(result);
    }

    // 이름으로 찾기
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }


}
