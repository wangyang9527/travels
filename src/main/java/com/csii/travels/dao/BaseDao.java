package com.csii.travels.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BaseDao<T,K> {

    void save(T t);

    void update(T t);

    void delete(K k);

    T findOne(K k);

    List<T> findAll();

    List<T> findByPage(@Param("start") Integer start ,@Param("size") Integer size);

    Integer findTotal();
}
