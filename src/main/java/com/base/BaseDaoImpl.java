package com.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;



@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<M extends Serializable, P extends Serializable> extends HibernateDaoSupport  implements BaseDAO<M,P>{

	// 实体类类型(由构造方法自动赋值)
	private Class<M> entityClass;
	private String entityName;

	// 构造方法，根据实例类自动获取实体类类型
	public BaseDaoImpl() {
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<M>) p[0];
			String getSimpleName = entityClass.getSimpleName();
			String upperCase = getSimpleName.substring(0, 1);
			String lowerCase = upperCase.toLowerCase();
			entityName = entityClass.getSimpleName().replaceFirst(upperCase,lowerCase);
		}
	}
	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	//获取Session
	public Session getCurrentSession(){
		return getSessionFactory().getCurrentSession();
	}
	
	@Override
	public P save(M mode) throws DataAccessException {
		return (P) getHibernateTemplate().save(mode);
	}

	@Override
	public P add(M mode) throws DataAccessException {
		return (P) getHibernateTemplate().save(mode);
	}

	@Override
	public void update(M mode) throws DataAccessException {
		getHibernateTemplate().update(mode);
	}

	@Override
	public void remove(M mode) throws DataAccessException {
		getHibernateTemplate().delete(mode);
	}

	@Override
	public void removeById(P id) throws DataAccessException {
		remove(get(id));
	}

	@Override
	public List<M> findAll() throws DataAccessException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		detachedCriteria.addOrder(Order.asc("id"));
		return (List<M>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public M get(P id) throws DataAccessException {
		return getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public M load(P id) throws DataAccessException {
		return getHibernateTemplate().load(entityClass, id);
	}

	@Override
	public PaginationSupport<M> findPageByCriteria(DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE, 0); 
	}

	@Override
	public PaginationSupport<M> findPageByCriteria(final DetachedCriteria detachedCriteria, final int pageSize, final int startIndex) {
		return (PaginationSupport) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {  
            public Object doInHibernate(Session session) throws HibernateException {  
                Criteria criteria = detachedCriteria.getExecutableCriteria(session); 
                int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();  
                criteria.setProjection(null);  
                List items = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();  
                PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);  
                return ps;  
            }  
        });  
	}

}
