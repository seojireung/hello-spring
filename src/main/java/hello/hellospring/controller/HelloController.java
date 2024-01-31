package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // MVC 1
    // 모델 기본 적용
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute( "data", "hello!!" );
        return "hello";
    }

    // MVC 2
    // @RequestParam
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 문자 데이터 그대로 HTTP BODY에 전달
    // @ResponseBody -> viewResolver
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API 1
    // 데이터 전달 (HTTP BODY에 Hello라는 객체 전달(json 방식))
    // @ResponseBody -> HttpMessageConverter
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}


