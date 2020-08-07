package com.csii.travels.service;

import com.csii.travels.entity.Province;

import java.util.List;


public interface ProvinceService {

    /**
     * @Description: 当前页  每页显示的记录数
     * @Param: [start, size]
     * @return:
     */
    List<Province> findByPage(Integer page, Integer size);

    Integer findTotal();

    void save(Province province);

    void delete(String id);

    Province findOne(String id);

    void update(Province province);

    List<Province> findAllPro();
}
