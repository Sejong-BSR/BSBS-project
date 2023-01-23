package BSRG.BSBS.repository;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static BSRG.BSBS.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory qm;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findByBojId(String bojId) {
        return qm.selectFrom(member)
                .where(member.bojId.eq(bojId))
                .fetchOne();
    }

}
