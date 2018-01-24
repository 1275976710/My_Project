package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.SaleVistDao;
import cn.itcast.domain.SaleVisit;
import cn.itcast.service.SaleVisitService;
import cn.itcast.utils.PageBean;

@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImpl implements SaleVisitService {

	//注入dao
	@Resource(name="saleVistDao")
	private SaleVistDao svd;
	public void setSvd(SaleVistDao svd) {
		this.svd = svd;
	}

	@Override
	//保存用户拜访客户记录
	public void save(SaleVisit sv) {
		
		svd.saveOrUpdate(sv);
	}

	@Override
	//获得分页对象
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		
		//调用dao查询总记录数
		Integer totalCount = svd.getTotalCount(dc);
		//创建pageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//调用dao查询分页列表数据(传一个起始索引start)
		List<SaleVisit> list = svd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//列表数据放入pageBean中，并返回pageBean对象
		pb.setList(list);
		return pb;
	}

	@Override
	//修改拜访记录(根据id获得拜访对象)
	public SaleVisit getById(String visit_id) {
		SaleVisit saleVisit = svd.getById(visit_id);
		return saleVisit;
	}

}
