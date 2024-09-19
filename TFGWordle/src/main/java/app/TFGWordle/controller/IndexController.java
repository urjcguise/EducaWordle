package app.TFGWordle.controller;

import app.TFGWordle.service.WordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private WordleService wordleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/wordle")
    public String index() {
        return "index";
    }
}