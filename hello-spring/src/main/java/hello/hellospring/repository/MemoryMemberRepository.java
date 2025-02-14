package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

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
                .filter(member -> member.getName().equals(name))
                .findAny();
        // stream : store에 저장된 데이터를 loop로 쭉 훑는다.
        // filter : store에 저장된 name과 현재 들어온 name을 찾는다.
        // findAny : 한개 이상 찾았을때(중복이 있을때)
        // 중복을 찾았다면 이미 있는 데이터를 optional로 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // 반환형태가 List이므로 new로 list 생성
        // store.values() 하면 store의 모든 value 반환
    }

    public void clearStore(){
        store.clear();
        // store를 싹 비운다.
    }
}
