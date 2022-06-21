package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
테스트코드는 한글로 적어도 댐
직관적으로 쉽게 알아들을 수 있으니까
또 빌드 될떄 테스트코드는 포함되지 않음
 */

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 코드 동작하기 전에 넣기
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();   // 이전 데이터 다 지움
    }

    @Test
    void 회원가입() {
        // given (어떤 상황이 주어졌을때)
        Member member = new Member();
        member.setName("spring");

        // when(이걸 실행했을떄)
        Long saveId = memberService.join(member);

        // then(~~한 결과가 나와야해!)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(findMember, member);
        Assertions.assertEquals(findMember.getName(), member.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);


        // then
        // 예외 확인하는 방법 1
        try {
            memberService.join(member2); // 여기서 예외가 터져야함
            fail("예외가 발생해야 합니다."); // 예외가 안터졌을 시
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(),"이미 존재 하는 회원 입니다.");
        }

        // 예외 확인하는 방법 2
        // 람다 뒤에 예외가 터져야함 어떤 예외? IllegalStateException이 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertEquals(e.getMessage(), "이미 존재 하는 회원 입니다.");
    }


    @Test
    void findMember() {

    }

    @Test
    void findOne() {
    }
}