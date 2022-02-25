package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.WorkDetail;

public interface WorkDetailListService {

	List<WorkDetail> queryWorkDetail(Map<String, String> map);

}
