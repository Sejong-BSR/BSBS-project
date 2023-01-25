package BSRG.BSBS.domain.entity;

import BSRG.BSBS.domain.etc.ProblemState;
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

    private ProblemState problemState;
    private Boolean solved = Boolean.FALSE;


    public static MemberProblem create(Member member, Problem problem) {
        MemberProblem memberProblem = new MemberProblem();
        memberProblem.member = member;
        memberProblem.problem = problem;
        memberProblem.problemState = ProblemState.NOW;

        return memberProblem;
    }

    public void UpdateProblemState() {
        this.problemState = ProblemState.PREV;
    }

    public Boolean checkSolved() {
        Long number = this.problem.getNumber();
        String solvedNums = this.member.getSolvedNums();
        if (solvedNums != null && solvedNums.contains(String.valueOf(number))) {
            this.solved = Boolean.TRUE;
        }
        return this.solved;
    }
}
