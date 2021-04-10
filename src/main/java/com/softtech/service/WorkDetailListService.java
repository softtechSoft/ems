package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.WorkDetail;

public interface WorkDetailListService {

	List<WorkDetail> queryWorkDetail(String month);

}
