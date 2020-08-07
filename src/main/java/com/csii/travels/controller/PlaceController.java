package com.csii.travels.controller;

import com.csii.travels.entity.Place;
import com.csii.travels.entity.Result;
import com.csii.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("place")
@RestController
public class PlaceController {

    //注入placeService
    @Autowired
    private PlaceService placeService;
    //注入文件上传路径
    @Value("${upload.dir}")
    private String realPath;

    /**
      * @Description: 根据省份id查询所有的景点信息
      * @Param: [page, size, provinceId]
      * @return:
      */
    @GetMapping("/findAllPlaces")
    public Map<String , Object> findPageByProId(Integer page , Integer size , String provinceId){

        //新建集合保存所有的分页信息
        Map<String, Object> map = new HashMap<>();

        page = page ==null?1:page;
        size = size ==null?3:size;
        //景点集合
        List<Place> places = placeService.findPageByProId(page , size , provinceId);
        //查询景点总数
        Integer counts = placeService.findCountsByProId(provinceId);
        //总页数
        Integer totalPage = counts%size==0?counts/size:counts/size+1;

        map.put("totalPage",totalPage);
        map.put("placeList",places);
        map.put("page",page);
        map.put("counts",counts);
        return map;
    }

    /**
     * @Description: 保存景点图片信息
     * @Param: [pic]
     * @return:
     */
    @PostMapping("/savePlace")
    public Result save(MultipartFile pic,Place place) throws Exception {

       /* System.out.println(pic.getOriginalFilename());

        System.out.println("place = " + place);*/
//       pic.getOriginalFilename()

        Result result = new Result();

        try{
            //获取图片扩展名
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+extension;

            //先进行base64编码 在进行文件上传
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //保存图片信息
            pic.transferTo(new File(realPath,newFileName));



            //保存place对象
            placeService.save(place);
            result.setMsg("添加景点成功");

        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }

        return result;
    }

    /**
      * @Description: 删除景点
      * @Param: [id]
      * @return:
      */
    @GetMapping("/delete")
    public Result delete(String id){

        Result result = new Result();
        try{
        placeService.delete(id);
        result.setMsg("景点删除成功");
        }catch(Exception e){
        result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
      * @Description: 根据id查询景点信息
      * @Param: [id]
      * @return:
      */
    @GetMapping("/findPlace/{id}")
    public Place findPlace(@PathVariable("id") Integer id ){

        Place place = placeService.findOne(id);
        return place;
    }

    @PostMapping("/update")
    public Result update(MultipartFile pic , Place place){

        Result result = new Result();
        try{
            //对接收文件进行base64处理
            String picPath = Base64Utils.encodeToString(pic.getBytes());
            place.setPicpath(picPath);

            //处理文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+extension;
            pic.transferTo(new File(realPath,newFileName));

            //修改景点信息
            placeService.update(place);
            result.setMsg("修改景点成功");
        }catch(Exception e){
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }
}
