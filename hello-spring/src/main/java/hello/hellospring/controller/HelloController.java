package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // key value 값으로 만듬
        model.addAttribute("data", "spring!!");

        // return "hello" 라 하면 Resources 에 templates 에 hello.html을 찾아줌
        // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버(View Resolver)가 화면을 찾아서 처리한다.
        return "hello";
    }

    // 외부에서 Parameter를 받을때
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API방식 1
    // 그대로 Data를 내려주는 방식
    @GetMapping("hello-string")
    @ResponseBody   // http 프로토콜 body부분에 이 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API 방식 2
//    @GetMapping("hello-api")
//    @ResponseBody


    static class Hello {
        private String name;

        




    }

}
