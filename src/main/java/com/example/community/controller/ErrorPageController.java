package com.example.community.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Amar_amo
 * @date 2019/12/7 21:32
 */
public class ErrorPageController {
    @GetMapping("/error")
    public String Error(){
        return "error";
    }
}
