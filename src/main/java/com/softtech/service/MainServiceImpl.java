package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.Ofcfunction;
import com.softtech.mapper.MainMapper;

@Service("mainServiceImpl")
public class MainServiceImpl implements MainService {
	@Autowired
	private MainMapper mainMpper;

	@Override
	public List<Ofcfunction> queryOfcfunction(String authority) {
		return mainMpper.queryOfcfunction(authority);
	}

	public MainMapper getMainMpper() {
		return mainMpper;
	}

	public void setMainMpper(MainMapper mainMpper) {
		this.mainMpper = mainMpper;
	}
}
