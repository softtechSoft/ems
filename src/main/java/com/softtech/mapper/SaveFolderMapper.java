package com.softtech.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SaveFolder;

@Mapper
public interface SaveFolderMapper {
	SaveFolder findFileDir(String findFileTypeName);

}
