package com.casi.jdo.base;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.Query;
import org.springframework.orm.jdo.support.JdoDaoSupport;

public abstract class BaseDAOImpl<T extends BaseDO> extends JdoDaoSupport implements BaseDAO<T> {

    private Class<T> domainClass;

    public BaseDAOImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    @Transactional
    public void delete(T object) {
        getJdoTemplate().deletePersistent(object);
    }

    @Transactional
    public void deleteAll(List<T> list){
        getJdoTemplate().deletePersistentAll(list);
    }

    @Transactional
    public T findById(long id) {
        return (T) getJdoTemplate().getObjectById(domainClass, id);
    }

    @Transactional
    public void save(T object) {
        getJdoTemplate().makePersistent(object);
    }

    @Transactional
    public void saveAll(List<T> list){
        getJdoTemplate().makePersistentAll(list);
    }

    protected Query newQuery()
    {
        return getPersistenceManager().newQuery(domainClass);
    }

    @Transactional
    public List<T> findAll() {
        Query query = newQuery();
        Object r = query.execute();
        return new ArrayList<T>( (Collection<T>) r );
    }

    @Transactional
    public int countAll() {
        return findAll().size();
    }
}
