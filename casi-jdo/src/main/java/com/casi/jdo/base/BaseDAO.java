package com.casi.jdo.base;
import java.util.List;

public interface BaseDAO<T extends BaseDO>
{
    public void delete(T o);

    public void deleteAll(List<T> list);

    public T findById(long id);

    public void save(T o);

    public void saveAll(List<T> list);

    public List<T> findAll();

    public int countAll();
}
