package com.myrest.example.application.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {


    @RequestMapping(value = {"/healthcheck"}, produces = {"application/json"},
            method = {RequestMethod.GET})
    @ResponseBody
    public String healthcheck1() {
        return "OK";
    }

    @RequestMapping(value = {"/"}, produces = {"application/json"},
            method = {RequestMethod.GET})
    @ResponseBody
    public String healthcheck2() {
        return "OK";
    }

}
