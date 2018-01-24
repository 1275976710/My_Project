package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.BaseDict;

public interface BaseDictService {

	//根据dict_type_code获得数据字典对象
	List<BaseDict> getListByTypeCode(String dict_type_code);

}
