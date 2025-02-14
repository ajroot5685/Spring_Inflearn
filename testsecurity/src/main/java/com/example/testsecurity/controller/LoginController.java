package com.example.testsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginP() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";

        // http basic은 인증 정보를 헤더에 저장해서 매번 요청하기 때문에 서버에서 로그아웃해도 헤더를 통해 계속 로그인 상태가 된다.
        // 따라서 클라이언트에서 로그아웃 로직을 구현하고 서버에서는 해당 로직을 수행하도록 응답메세지를 보내야 한다.
        // 예시 - 401 Unauthorized
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("WWW-Authenticate", "Basic realm=\"Access to the staging site\", charset=\"UTF-8\"");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).build();
    }
}
