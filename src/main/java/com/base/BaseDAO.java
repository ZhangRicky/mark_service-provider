package com.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

/**
 * 基础DAO层
 * @author Mark
 *
 * @param <M>
 * @param <P>
 */
public interface BaseDAO<M extends Serializable, P extends Serializable> {

	P save(M mode) throws DataAccessException;
	
	P add(M mode) throws DataAccessException;
	
	void update(M mode) throws DataAccessException;
	
	void remove(M mode) throws DataAccessException;
	
	void removeById(P id) throws DataAccessException;
	
	List<M> findAll()throws DataAccessException;
	
	M get(P id)throws DataAccessException;
	
	M load(P id)throws DataAccessException;
	
	PaginationSupport<M> findPageByCriteria(DetachedCriteria detachedCriteria);

	PaginationSupport<M> findPageByCriteria(DetachedCriteria detachedCriteria,int pageSize, int startIndex);
	
}
