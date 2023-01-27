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

        Member loginMember = getLoginMember(loginForm.getLoginId());
        if (loginMember == null) {
            return "redirect:/login";
        }
        log.info(loginMember.getBojId());
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
    public String addProblem(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {
        Member loginMember = getLoginMember(loginMemberBojId);
        memberProblemService.recommend(loginMember);
        return "redirect:/"; // url 을 통한 문제 추가 방지
    }

    @GetMapping("/reset_problems")
    public String resetProblemList(Model model, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberBojId) {
        Member loginMember = getLoginMember(loginMemberBojId);
        memberProblemService.resetRecommend(loginMember);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model,  @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) String loginMemberId){
        Member loginMember = getLoginMember(loginMemberId);
        List<MemberProblem> memberProblems = memberProblemService.findAllByMemberPrev(loginMember);
        model.addAttribute("memberProblemList",memberProblems);
        return "profile";
    }

    private Member getLoginMember(String loginMemberId) {
        return memberService.findByBojId(loginMemberId);
    }

    @Data
    static class LoginForm {
        private String loginId;
    }

}
