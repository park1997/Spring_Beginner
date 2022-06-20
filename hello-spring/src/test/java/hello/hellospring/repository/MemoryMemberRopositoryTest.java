package hello.hellospring.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

// TDD란?
// Test 주도 개발 이라고해서 테스트를 먼저만들고 구현클래스를 만들어서 검증함

// 모든테스트는 순서가 보장이 안됨
// 모든테스트는 순서와 관계 없이 서로가 의존하면 안됨
// 따라서 테스트가 끝나면 모두 다 지워줘야함
class MemoryMemberRopositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // CallBack함수 처럼 매 Test Code가 끝날때마다 실행됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();    // 이전 데이터 다 지움
    }

    // Test 어노테이션을 달면 Junit Framework를 import 해줌
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);


        Member result = repository.findById(member.getId()).get();

        // 출력으로 같은지 확인할 수 있지만
        System.out.println("result : " + (result == member));

        // Assertions를 활용해서 틀린지 볼수 있음
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        Assertions.assertEquals(result1,member1);
        Assertions.assertEquals(result2,member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(result.size(), 2);

    }


}
