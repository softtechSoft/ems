package com.softtech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softtech.entity.Ofcfunction;
import com.softtech.mapper.MainMapper;

@Service("mainServiceImpl")
public class MainServiceImpl implements MainService
{
	@Autowired
	private MainMapper mainMpper;
	
	@Override
	public List<Ofcfunction> queryOfcfunction(String EmployeeID) {
		return mainMpper.queryOfcfunction(EmployeeID);
	}

	public MainMapper getMainMpper() {
		return mainMpper;
	}

	public void setMainMpper(MainMapper mainMpper) {
		this.mainMpper = mainMpper;
	}
}
