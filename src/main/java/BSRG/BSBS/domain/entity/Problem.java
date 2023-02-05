package BSRG.BSBS.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseEntity {

    @Id
    @GeneratedValue
//    @Column(name = "problem_id")
    private long id;

    private Long number;
    private String grade;
    private String name;
    private String link;

    public static Problem create(Long number, String grade, String name) {
        Problem problem = new Problem();
        problem.number = number;
        problem.grade = grade;
        problem.name = name;
        problem.link = "https://www.acmicpc.net/problem/" + number;

        return problem;
    }
}
