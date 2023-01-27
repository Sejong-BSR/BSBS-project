package BSRG.BSBS.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;
    private String bojId;
    @Column(length = 5000)
    private String solvedNums;
    private int solvedTotal;
    private String link; // profile link (BOJ site)

    public static Member create(String bojId) {
        Member member = new Member();
        member.bojId = bojId;
        member.link = "https://www.acmicpc.net/user/" + bojId;
        member.setCreatedAt(now());
        return member;
    }

    public void setSolvedNums(String updatedSolvedNums) {
        this.solvedNums = updatedSolvedNums;
    }

    public void editSolved(String solvedNums, int solvedTotal) {
        this.solvedNums = solvedNums;
        this.solvedTotal = solvedTotal;
    }

    public Boolean checkSolved(Long num) {
        return this.solvedNums.contains(num.toString());
    }

}
