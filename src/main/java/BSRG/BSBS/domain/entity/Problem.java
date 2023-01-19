package BSRG.BSBS.domain.entity;

import BSRG.BSBS.domain.etc.ProblemState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {

    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    private long id;

    private Long number;
    private String rank;
    private String name;
    private String link;

    public static Problem create(Long number, String rank, String name) {
        Problem problem = new Problem();
        problem.number = number;
        problem.rank = rank;
        problem.name = name;
        problem.link = "https://www.acmicpc.net/problem/" + number;

        return problem;
    }
}
