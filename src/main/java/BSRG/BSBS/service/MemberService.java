package BSRG.BSBS.service;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member findByBojId(String bojId) {
        return memberRepository.findByBojId(bojId);
    }
}
