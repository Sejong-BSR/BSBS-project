package BSRG.BSBS.service;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.repository.MemberProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProblemService {

    private final MemberProblemRepository memberProblemRepository;

    public void save(MemberProblem memberProblem) {
        memberProblem.checkSolved(); // 풀었는지 확인
        memberProblemRepository.save(memberProblem);
    }

    public List<MemberProblem> findAllByMemberNow(Member member) {
        return memberProblemRepository.findAllByMemberNow(member);
    }
}
