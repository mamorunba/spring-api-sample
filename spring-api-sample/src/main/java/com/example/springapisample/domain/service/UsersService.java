package com.example.springapisample.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapisample.domain.dao.UsersDao;
import com.example.springapisample.domain.model.UsersEntity;

@Service
public class UsersService {
	@Autowired
	UsersDao usersDao;

	public List<UsersEntity> selectAll() {
		return usersDao.selectAll();
	}

}