package BSRG.BSBS.repository;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.domain.etc.ProblemState;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static BSRG.BSBS.domain.entity.QMemberProblem.memberProblem;
import static BSRG.BSBS.domain.entity.QProblem.problem;

@Repository
@RequiredArgsConstructor
public class MemberProblemRepository {

    private final EntityManager em;
    private final JPAQueryFactory qm;

    public void save(MemberProblem memberProblem) {
        em.persist(memberProblem);
    }


    public List<MemberProblem> findAllByMemberNow(Member member) {
        return qm.selectFrom(memberProblem)
                .where(memberProblem.member.eq(member),
                        memberProblem.problemState.eq(ProblemState.NOW))
                .orderBy(memberProblem.createdAt.desc())
                .fetch();
    }

    public List<MemberProblem> findAllByMemberPrev(Member member) {
        return qm.selectFrom(memberProblem)
                .where(memberProblem.member.eq(member),
                        memberProblem.problemState.eq(ProblemState.PREV))
                .orderBy(memberProblem.createdAt.desc())
                .fetch();
    }

    public Boolean isRecommended(Long num) { // 수정 필요
        return qm.selectFrom(memberProblem)
                .leftJoin(memberProblem.problem, problem)
                .where(memberProblem.problem.number.eq(num))
                .fetchOne() != null;
    }

    public void updateMemberProblemStateToPrev(Member member) {
        qm.update(memberProblem)
                .set(memberProblem.problemState, ProblemState.PREV)
                .where(memberProblem.member.eq(member),
                        memberProblem.problemState.eq(ProblemState.NOW))
                .execute();

        em.clear();
    }
}
