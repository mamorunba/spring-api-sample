package com.example.springapisample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapisample.domain.model.UsersEntity;
import com.example.springapisample.domain.service.UsersService;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UsersEntity> getUsers () {
        return usersService.selectAll();
    }
}
