package BSRG.BSBS.service;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.repository.MemberProblemRepository;
import BSRG.BSBS.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProblemService {

    private final MemberProblemRepository memberProblemRepository;
    private final ProblemRepository problemRepository;

    public void save(MemberProblem memberProblem) {
        memberProblem.checkSolved(); // 풀었는지 확인
        memberProblemRepository.save(memberProblem);
    }

    public List<MemberProblem> findAllByMemberNow(Member member) {
        return memberProblemRepository.findAllByMemberNow(member);
    }

    public void recommend(Member member) {
        Long recommendNum = runModel(member);
        while (memberProblemRepository.isRecommended(recommendNum) || member.checkSolved(recommendNum)) {
            recommendNum = runModel(member);
        }
        Problem recommendProblem = problemRepository.findByNumber(recommendNum);
        MemberProblem recommendedMemberProblem = MemberProblem.create(member, recommendProblem);
        save(recommendedMemberProblem);
    }

    private Long runModel(Member member) {
        Long resultProblemNum = 1190L; // recommendation model result
        return resultProblemNum;
    }
}
