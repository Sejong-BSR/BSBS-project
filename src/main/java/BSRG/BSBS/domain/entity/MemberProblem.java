package BSRG.BSBS.domain.entity;

import BSRG.BSBS.domain.etc.ProblemStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProblem {

    @Id
    @GeneratedValue
    @Column(name = "member_problem_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Enumerated(EnumType.STRING)
    private ProblemStatus status;
}
