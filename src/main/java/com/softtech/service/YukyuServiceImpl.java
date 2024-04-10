package com.softtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuDetail;
@Service
public class YukyuServiceImpl implements YukyuService{

	@Override
	public List<YukyuDetail> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

//	@Autowired
//	YukyuMapper yukyuMapper;
//
//	public YukyuServiceImpl(YukyuMapper yukyuMapper) {
//		this.yukyuMappers=yukyuMapper;
//	}
//
//	@Override
//	public List<YukyuDetail> findAll() {
//        return yukyuRepository.findAll();
//    }
}
