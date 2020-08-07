package com.csii.travels.service;

import com.csii.travels.dao.PlaceDao;
import com.csii.travels.entity.Place;
import com.csii.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PlaceServiceImpl implements PlaceService {

    //注入PlaceDao
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Place> findPageByProId(Integer page, Integer size, String provinceId) {

        int start = (page - 1)*size;
        return  placeDao.findPageByProId(start , size , provinceId);

    }

    @Override
    public Integer findCountsByProId(String provinceId) {

        return placeDao.findCountsByProId(provinceId);
    }

    @Override
    public void save(Place place) {

        //给当前省份的景点个数加1
        String provinceid = place.getProvinceid()+"";
        Province province = provinceService.findOne(provinceid);
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);

        placeDao.save(place);
    }

    @Override
    public void delete(String id) {

        //更新景点个数   删除景点
        Place place = placeDao.findOne(id);
        Province province = provinceService.findOne(place.getProvinceid() + "");
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.update(province);

        placeDao.delete(id);
    }

    @Override
    public Place findOne(Integer id) {

        return placeDao.findOne(id+"");
    }

    @Override
    public void update(Place place) {

        placeDao.update(place);
    }


}
