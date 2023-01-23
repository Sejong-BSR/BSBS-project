package BSRG.BSBS.repository;

import BSRG.BSBS.domain.entity.Problem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProblemRepository {

    private final EntityManager em;
    private final JPAQueryFactory qm;

    public void save(Problem problem) {
        em.persist(problem);
    }

}
