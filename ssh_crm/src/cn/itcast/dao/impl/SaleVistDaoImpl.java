package cn.itcast.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.SaleVistDao;
import cn.itcast.domain.SaleVisit;

@Repository("saleVistDao")
public class SaleVistDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVistDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
}
