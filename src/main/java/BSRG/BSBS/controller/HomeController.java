package BSRG.BSBS.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String temp(Model model){


        return "login";
    }

    @GetMapping("/")
    public String Thymeleaf(Model model){
        List<Problem> productList = new ArrayList<>();
        productList.add(new Problem(1001,"5","아기상어와함께 여행을",1,"silver"));
        productList.add(new Problem(2002,"4","주사위게임",0,"silver"));
        productList.add(new Problem(3003,"3","윷놀이게임",0,"silver"));
        productList.add(new Problem(4004,"2","아기상어",1,"silver"));
        productList.add(new Problem(5005,"1","청소년상어",0,"silver"));

        productList.add(new Problem(6006,"5","소용돌이 예쁘게 출력하기",0,"gold"));
        productList.add(new Problem(7007,"4","숫자 정사각형",0,"gold"));
        productList.add(new Problem(8008,"3","알 수 없는 문장",1,"gold"));
        productList.add(new Problem(9009,"2","음식 평론가",0,"gold"));
        productList.add(new Problem(10001,"1","최소 스패닝 트리",0,"gold"));

        productList.add(new Problem(15066,"5","컨닝",1,"platinum"));
        productList.add(new Problem(22000,"4","소수 쌍",1,"platinum"));
        productList.add(new Problem(24000,"3","Random Number Generator",0,"platinum"));
        productList.add(new Problem(28000,"2","마지막 요세푸스 문제",0,"platinum"));
        productList.add(new Problem(30000,"1","오일러 회로",0,"platinum"));

        //add data to view
        model.addAttribute("productList",productList);
        return "home";
    }

    @Data
    public class Problem{
        private int num;
        private String rank;
        private String name;
        private int pass;

        private String ranktype;


        public Problem(int num, String rank, String name, int pass,String ranktype) {
            this.num = num;
            this.rank = rank;
            this.name = name;
            this.pass = pass;
            this.ranktype = ranktype;
        }
    }
}
