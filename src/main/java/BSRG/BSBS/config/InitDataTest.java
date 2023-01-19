package BSRG.BSBS.config;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.service.MemberProblemService;
import BSRG.BSBS.service.MemberService;
import BSRG.BSBS.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataTest {
    private final InitService initService;
    @PostConstruct
    public void dbInit() {
        initService.dbInit1();

    }
    @Component
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final ProblemService problemService;
        private final MemberProblemService memberProblemService;

        public void dbInit1() { // create Member, Problem, MemberProblem(solved problem's infos)
            Member bob8dod = Member.create("bob8dod");
            memberService.save(bob8dod);

            Problem p1001 = Problem.create(1001L, "S5", "아기상어와함께 여행을");
            Problem p2002 = Problem.create(2002L,"S4","주사위게임");
            Problem p3003 = Problem.create(3003L,"S3","윷놀이게임");
            Problem p4004 = Problem.create(4004L,"S2","아기상어");
            Problem p5005 = Problem.create(5005L,"S1","청소년상어");
            Problem p6006 = Problem.create(6006L,"G5","소용돌이 예쁘게 출력하기");
            Problem p7007 = Problem.create(7007L,"G4","숫자 정사각형");
            Problem p8008 = Problem.create(8008L,"G3","알 수 없는 문장");
            Problem p9009 = Problem.create(9009L,"G2","음식 평론가");
            Problem p10001 = Problem.create(10001L,"G1","최소 스패닝 트리");

            problemService.save(p1001);
            problemService.save(p2002);
            problemService.save(p3003);
            problemService.save(p4004);
            problemService.save(p5005);
            problemService.save(p6006);
            problemService.save(p7007);
            problemService.save(p8008);
            problemService.save(p9009);
            problemService.save(p10001);

            MemberProblem mp1001 = MemberProblem.create(bob8dod, p1001);
            MemberProblem mp3003 = MemberProblem.create(bob8dod, p3003);
            MemberProblem mp6006 = MemberProblem.create(bob8dod, p6006);
            memberProblemService.save(mp1001);
            memberProblemService.save(mp3003);
            memberProblemService.save(mp6006);

        }

    }


}
