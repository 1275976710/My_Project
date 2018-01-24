package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {
	
	@Resource(name="customerDao")
	private CustomerDao cd;
	
	//获得分页对象
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		
		//调用dao查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//创建pageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//调用dao查询分页列表数据(传一个起始索引start)
		List<Customer> list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//列表数据放入pageBean中，并返回pageBean对象
		pb.setList(list);
		return pb;
	}
	
	public void setCd(CustomerDao cd) {
		
		this.cd=cd;
	}

	//保存客户对象
	@Override
	public void save(Customer customer) {
		
		//1.维护Customer对象与数据字典的关系，由于struts2参数封装，会将参数封装到数据字典的id属性
		//我们无需手动封装
		//2.调用Dao保存客户
		cd.saveOrUpdate(customer);
	}

	//根据id获得客户对象
	@Override
	public Customer getById(Long cust_id) {
		// TODO Auto-generated method stub
		return cd.getById(cust_id);
	}

	@Override
	//获得按行业统计客户数量
	public List<Object[]> getIndustryCount() {
		return cd.getIndustry();
	}
}
