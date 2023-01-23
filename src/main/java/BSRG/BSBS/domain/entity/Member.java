package BSRG.BSBS.domain.entity;

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
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;
    private String bojId;
    private String solvedNums;
    private String link; // profile link (BOJ site)

    public static Member create(String bojId) {
        Member member = new Member();
        member.bojId = bojId;
        member.link = "https://www.acmicpc.net/user/" + bojId;
        return member;
    }

    public void setSolvedNums(String updatedSolvedNums) {
        this.solvedNums = updatedSolvedNums;
    }

}
