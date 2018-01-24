package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.SaleVisit;
import cn.itcast.utils.PageBean;

public interface SaleVisitService {

	//保存用户拜访客户记录
	void save(SaleVisit sv);

	//获得分页对象
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	//修改拜访记录(根据id获得拜访对象)
	SaleVisit getById(String visit_id);
}
