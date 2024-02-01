package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // Lamda
                // member.getName() 시 파라미터로 넘어온 name과 map의 name이 일치하는 것을
                .filter(member -> member.getName().equals(name))
                // 하나로 찾음
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // map의 value값들, 즉, Member들 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }



}
