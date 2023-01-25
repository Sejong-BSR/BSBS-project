package BSRG.BSBS.controller;

import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.service.MemberProblemService;
import BSRG.BSBS.service.MemberService;
import BSRG.BSBS.service.ProblemService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ProblemService problemService;
    private final MemberProblemService memberProblemService;

    @GetMapping("/login")
    public String flogin(Model model){
        return "login";
    }

    @GetMapping("/profile")
    public String fprofile(Model model){
        Member loginMember = memberService.findByBojId("bob8dod");
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberNow(loginMember);
        model.addAttribute("memberProblemList",memberProblems);
        return "profile";
    }

    @GetMapping("/")
    public String home(Model model){
        Member loginMember = memberService.findByBojId("bob8dod");
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberNow(loginMember);
        model.addAttribute("memberProblemList",memberProblems);

        return "home";
    }

    @GetMapping("/add_problem")
    public String addProblem(Model model) {
        Member loginMember = memberService.findByBojId("bob8dod");
        memberProblemService.recommend(loginMember);
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberNow(loginMember);
        model.addAttribute("memberProblemList",memberProblems);
        return "home";
    }
}
