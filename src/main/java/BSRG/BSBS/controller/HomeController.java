package BSRG.BSBS.controller;

import BSRG.BSBS.config.SessionConst;
import BSRG.BSBS.domain.entity.Member;
import BSRG.BSBS.domain.entity.MemberProblem;
import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.service.MemberProblemService;
import BSRG.BSBS.service.MemberService;
import BSRG.BSBS.service.ProblemService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ProblemService problemService;
    private final MemberProblemService memberProblemService;

    @GetMapping("/login")
    public String forLogin(Model model, @ModelAttribute("loginForm") LoginForm loginForm){
        log.info("try login");
        return "login";
    }

    @PostMapping("/login")
    public String toLogin(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request,
                          @RequestParam(name = "redirectURL", defaultValue = "/") String redirectURL) {

        String loginId = loginForm.getLoginId();
        Member loginMember = getLoginMember(loginId);

        // 정보가 있는 Member 의 로그인 -> 기존 Member Entity 수정
        // 정보가 없는 Member 의 로그인 -> 새로운 Member Entity 생성
        loginMember = createOrEditMember(loginId, loginMember);

        if (loginMember == null) {
            return "redirect:/login";
        }

        log.info(loginMember.getBojId() + " login!!");
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getBojId());
        return "redirect:" + redirectURL;
    }


    @GetMapping("/")
    public String home(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {

        Member loginMember = getLoginMember(loginMemberBojId);
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberNow(loginMember);
        model.addAttribute("memberProblemList", memberProblems);

        return "home";
    }

    @GetMapping("/add_problem")
    public String addProblem(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {
        Member loginMember = getLoginMember(loginMemberBojId);
        memberProblemService.recommend(loginMember);
        return "redirect:/"; // url 을 통한 문제 추가 방지
    }

    @GetMapping("/reload_problems")
    public String reloadProblems(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {
        Member loginMember = getLoginMember(loginMemberBojId);
        // 로직 생성 필요 (Using Jsoup)
        return "redirect:/";
    }

    @GetMapping("/reset_problems")
    public String resetProblemList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {
        Member loginMember = getLoginMember(loginMemberBojId);
        memberProblemService.resetRecommend(loginMember);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model,  @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberId){
        Member loginMember = getLoginMember(loginMemberId);
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberPrev(loginMember);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("memberProblemList",memberProblems);
        return "profile";
    }

    private Member createOrEditMember(String loginId, Member loginMember) {
        String loginURL = "https://www.acmicpc.net/user/" + loginId;
        try {
            Connection conn = Jsoup.connect(loginURL);
            Document html = conn.get();
            Elements problemList = html.getElementsByClass("result-ac");
            StringBuilder solvedNumsList = new StringBuilder();
            int solvedTotal = 0;
            for (Element problem : problemList) {
                solvedTotal += 1;
                solvedNumsList.append(problem.text()).append(" ");
            }
            String solvedNums = solvedNumsList.toString();
            if (loginMember == null) {
                Member newMember = Member.create(loginId);
                memberService.save(newMember);
                loginMember = newMember;
            }
            loginMember.editSolved(solvedNums, solvedTotal);


        } catch (Exception e) {
            log.error(e.getMessage());

        }
        return loginMember;
    }

    private Member getLoginMember(String loginMemberId) {
        return memberService.findByBojId(loginMemberId);
    }

    @Data
    static class LoginForm {
        private String loginId;
    }

}
