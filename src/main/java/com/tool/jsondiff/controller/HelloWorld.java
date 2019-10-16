package com.tool.jsondiff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ ClassName HelloWorld
 * @ Description
 * @ Auther wb-dl321273
 * @ Version 1.0
 **/
@RestController
public class HelloWorld {


    @GetMapping("/index")
    public String helloworld(){
        return "Hello world";
    }

}
