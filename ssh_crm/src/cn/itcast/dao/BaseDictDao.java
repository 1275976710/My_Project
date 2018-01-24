package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {

	//根据dict_type_code获得数据字典对象
	List<BaseDict> getListByTypeCode(String dict_type_code);

}
