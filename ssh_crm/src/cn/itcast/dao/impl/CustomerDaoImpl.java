package cn.itcast.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	//按照行业统计客户数量
	public List<Object[]> getIndustry() {
		
		//使用原生SQL查询
		@SuppressWarnings("all")
		List<Object[]> list=getHibernateTemplate().execute(new HibernateCallback<List>() {

		String sql = "SELECT bd.`dict_item_name`,COUNT(*) total  "+
					"FROM cst_customer c,base_dict bd            "+
					"WHERE c.`cust_industry`=bd.`dict_id`        "+
					"GROUP BY c.`cust_industry`";                 
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
			
		});
		return list;
	}


		
	
	
}
