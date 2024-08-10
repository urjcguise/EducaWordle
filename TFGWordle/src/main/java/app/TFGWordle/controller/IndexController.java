package app.TFGWordle.controller;

import app.TFGWordle.service.WordleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class IndexController {

    @Autowired
    private WordleService wordleService;

    @GetMapping
    public String index() {
        return "index";
    }
}
