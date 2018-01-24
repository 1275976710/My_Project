package cn.itcast.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import cn.itcast.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;//用于接收运行期泛型T的类型
	
	public BaseDaoImpl() {
		//获得当前类型的带有泛型类型的父类
		//返回的是BaseDaoImpl的类型
		//接口中的Type相当于对象中的Object
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型，返回的是一个数组，取数组中的第一个元素
		clazz = (Class) ptClass.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		T t = this.getById(id);//先取,再删
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	//根据id查询对象
	public T getById(Serializable id) {
		//怎么获得当前运行的泛型的类型
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	//获得总记录数
	public Integer getTotalCount(DetachedCriteria dc) {
		//设置查询的聚合函数,总记录数
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		
		//清空之前设置的聚合函数
		dc.setProjection(null);
		
		if(list!=null && list.size()>0){
			Long count = list.get(0);
			return count.intValue();
		}else{
			return null;
		}
	}

	@Override
	//获得分页后的对象集合
	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		//直接调用现成的方法
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		return list;
	}

	@Override
	//增加或修改对象
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

}
