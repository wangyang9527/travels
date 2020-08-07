package com.csii.travels.dao;

import com.csii.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProvinceDao extends BaseDao<Province,String>{

    List<Province> findAll();

    List<Province> findByPage(@Param("start") Integer start , @Param("size") Integer size);

    Integer findTotal();

    List<Province> findAllPro();
}
