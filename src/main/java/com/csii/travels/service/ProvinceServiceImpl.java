package com.csii.travels.service;

import com.csii.travels.dao.ProvinceDao;
import com.csii.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    //注入ProvinceDao
    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public List<Province> findByPage(Integer page, Integer size) {

        Integer start = (page - 1) * size;
        List<Province> list = provinceDao.findByPage(start, size);
        return list;
    }


    @Override
    public Integer findTotal() {
        return provinceDao.findTotal();
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0); //设置景点个数为零
        provinceDao.save(province);
    }

    @Override
    public void delete(String id) {
        provinceDao.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDao.findOne(id);
    }

    @Override
    public void update(Province province) {
        provinceDao.update(province);
    }

    @Override
    public List<Province> findAllPro() {
        return provinceDao.findAllPro();
    }


}
