package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.SaveFolder;
import com.softtech.mapper.SaveFolderMapper;

@Service
public class SaveFolderService {

	@Autowired
	private SaveFolderMapper saveFolderMapper;

	public SaveFolder findFileTypeName(String findFileTypeName) {
		SaveFolder saveFolder = saveFolderMapper.findFileDir(findFileTypeName);
		if (saveFolder == null) {
            throw new RuntimeException("m_fileから取得されません: " + findFileTypeName);
        }
		return saveFolder;
	}

}
