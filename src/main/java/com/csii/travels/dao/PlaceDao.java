package com.csii.travels.dao;

import com.csii.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlaceDao extends BaseDao<Place, String>{

    //根据省份id查询所有的景点信息
    List<Place> findPageByProId(@Param("start")Integer start ,@Param("size") Integer size ,@Param("provinceId") String provinceId);

    //根据省份id查询景点总数
    Integer findCountsByProId(String provinceId);
}
