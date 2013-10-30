package com.casi.jdo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

public abstract class BaseDAO<T>{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private PersistenceManagerFactory persistenceManagerFactory = null;


    private Class<T> domainClass;

    public BaseDAO(Class<T> domainClass) {
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
        return getJdoTemplate().newQuery(domainClass);
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

    protected PersistenceManager getJdoTemplate(){
        return this.persistenceManagerFactory.getPersistenceManager();
    }

    public void setPersistenceManagerFactory(PersistenceManagerFactory persistenceManagerFactory) {
        this.persistenceManagerFactory = persistenceManagerFactory;
    }

    public PersistenceManagerFactory getPersistenceManagerFactory() {
        return persistenceManagerFactory;
    }

    protected Connection getJDBCConnect(){
        return (Connection) getJdoTemplate().getDataStoreConnection().getNativeConnection();
    }
}
