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
            bob8dod.setSolvedNums("1001 3003 4004");
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

            Problem p11001 = Problem.create(11001L, "S5", "보도블럭");
            Problem p12001 = Problem.create(12001L,"S4","오목");
            Problem p13001 = Problem.create(13001L,"G3","테트로미노");
            Problem p14001 = Problem.create(14001L,"P2","부분수열의 합");
            Problem p15001 = Problem.create(15001L,"P2","대학생상어");

            Problem recommend = Problem.create(1190L,"S1","추천된 문제");

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



            problemService.save(recommend);

            problemService.save(p11001);
            problemService.save(p12001);
            problemService.save(p13001);
            problemService.save(p14001);
            problemService.save(p15001);



            // All recommended Problems
            MemberProblem mp1001 = MemberProblem.create(bob8dod, p1001);
            MemberProblem mp2002 = MemberProblem.create(bob8dod, p2002);
            MemberProblem mp3003 = MemberProblem.create(bob8dod, p3003);
            MemberProblem mp4004 = MemberProblem.create(bob8dod, p4004);
            MemberProblem mp5005 = MemberProblem.create(bob8dod, p5005);
            MemberProblem mp6006 = MemberProblem.create(bob8dod, p6006);
            MemberProblem mp7007 = MemberProblem.create(bob8dod, p7007);
            MemberProblem mp8008 = MemberProblem.create(bob8dod, p8008);
            MemberProblem mp9009 = MemberProblem.create(bob8dod, p9009);
            MemberProblem mp10001 = MemberProblem.create(bob8dod, p10001);

            MemberProblem mp11001 = MemberProblem.create(bob8dod, p11001);
            MemberProblem mp12001 = MemberProblem.create(bob8dod, p12001);
            MemberProblem mp13001 = MemberProblem.create(bob8dod, p13001);
            MemberProblem mp14001 = MemberProblem.create(bob8dod, p14001);
            MemberProblem mp15001 = MemberProblem.create(bob8dod, p15001);


            // Prev recommended Problems
            mp6006.UpdateProblemState();
            mp7007.UpdateProblemState();
            mp8008.UpdateProblemState();
            mp9009.UpdateProblemState();
            mp10001.UpdateProblemState();

            memberProblemService.save(mp1001);
            memberProblemService.save(mp2002);
            memberProblemService.save(mp3003);
            memberProblemService.save(mp4004);
            memberProblemService.save(mp5005);
            memberProblemService.save(mp6006);
            memberProblemService.save(mp7007);
            memberProblemService.save(mp8008);
            memberProblemService.save(mp9009);
            memberProblemService.save(mp10001);

            memberProblemService.save(mp11001);
            memberProblemService.save(mp12001);
            memberProblemService.save(mp13001);
            memberProblemService.save(mp14001);
            memberProblemService.save(mp15001);



        }

    }


}
