package com.csii.travels.service;


import com.csii.travels.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceService {

    //根据省份id查询所有的景点信息  业务层拿到当前页  和页码大小
    List<Place> findPageByProId(Integer page ,  Integer size ,  String provinceId);

    //根据省份id查询景点总数
    Integer findCountsByProId(String provinceId);

    void save(Place place);

    void delete(String id);

    Place findOne(Integer id);

    void update(Place place);
}
