package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

// Repository를 Spring Container에 올려줌

public class MemoryMemberRepository implements MemberRepository {

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
        for (Member mem : store.values()) {
            if (mem.getName().equals(name)) {
                return Optional.ofNullable(mem);
            }
        }
//        store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store를 싹 비우는 역할 Test Code를 위해
    public void clearStore() {
        store.clear();
    }

}
