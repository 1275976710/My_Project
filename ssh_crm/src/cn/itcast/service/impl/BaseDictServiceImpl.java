package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;

@Service("baseDictService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class BaseDictServiceImpl implements BaseDictService {

	//通过spring注入baseDictDao
	@Resource(name="baseDictDao")
	private BaseDictDao baseDictDao;
	
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	//根据dict_type_code获得数据字典对象
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
				
		List<BaseDict> list = baseDictDao.getListByTypeCode(dict_type_code);
		
		return list;
	}

}
