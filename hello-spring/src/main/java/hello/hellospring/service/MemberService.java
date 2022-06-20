package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
/*
MemoryMemberRepository는 단순히 데이터를 저장 조회 등과 같음
하지만 Service 쪽은 회원가입, member 찾기 등과 같이 서비스 느낌이 든다.
 */


public class MemberService {
    // final이 붙어있으면 값을 생성자에서 초기화 한 이후에 변경 할 수 없다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    // 같은 이름이 있는 중복회원 X
    private void validateDuplicateMember(Member member) {
        // Command + Option + v
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재 하는 회원 입니다. ");
        });
    }
    /*
    전체 회원 조회
     */
    public List<Member> findMember() {

    }


}
