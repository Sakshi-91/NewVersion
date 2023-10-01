package com.example.demo;


import org.springframework.stereotype.Service;

import io.javalin.Javalin;

@Service
public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        
        app.get("/", ctx -> {
        ctx.result("Hello World from Java!") ;
        });
        }
}

