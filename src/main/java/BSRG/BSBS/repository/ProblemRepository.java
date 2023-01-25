package BSRG.BSBS.repository;

import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.domain.entity.QProblem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static BSRG.BSBS.domain.entity.QProblem.problem;

@Repository
@RequiredArgsConstructor
public class ProblemRepository {

    private final EntityManager em;
    private final JPAQueryFactory qm;

    public void save(Problem problem) {
        em.persist(problem);
    }

    public Problem findByNumber(Long recommendNum) {
        return qm.selectFrom(problem)
                .where(problem.number.eq(recommendNum))
                .fetchOne();
    }

}
