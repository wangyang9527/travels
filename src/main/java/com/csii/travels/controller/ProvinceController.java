package com.csii.travels.controller;

import com.csii.travels.entity.Province;
import com.csii.travels.entity.Result;
import com.csii.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("province")
public class ProvinceController {

    //注入provinceServince
    @Autowired
    private ProvinceService provinceService;


    /** 查询所有省份
      * @Description:
      * @Param: [page, size]
      * @return:
      */
    @GetMapping("/findByPage")
    @ResponseBody
    public Map<String , Object> findByPage(Integer page , Integer size){

        page = page==null?1:page;
        size = size==null?4:size;

        Map<String, Object> map = new HashMap<>();
        //分页查询
        List<Province> list = provinceService.findByPage(page, size);
//        System.out.println("list = " + list);
        //总数目
        Integer total = provinceService.findTotal();
        //总页数
        Integer totalPage = total%size==0?total/size:(total/size+1);
        map.put("list" , list);
        map.put("total",total);
        map.put("totalPage",totalPage);
        map.put("page",page);
        return map;
    }

    /**
      * @Description: 添加省份
      * @Param: [province]
      * @return:
      */
    @PostMapping("/saveProvince")
    @ResponseBody
    public Result saveProvince(@RequestBody Province province){

        Result result = new Result();
        try{
            provinceService.save(province);
            result.setMsg("保存省份成功 点击跳转到首页!!!");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("保存省份信息失败!!!");
        }
        return  result;
    }


    @GetMapping("/delProvince")
    @ResponseBody
    public Result delProvince(String id){

        Result result = new Result();
        try{
            provinceService.delete(id);
            result.setMsg("删除省份成功!!!");
        }catch(Exception e){
            e.printStackTrace();
            result.setMsg("删除省份失败!!!").setState(false);
        }
        return result;
    }

    /**
      * @Description: 根据id查询省份信息
      * @Param: [id]
      * @return:
      */
    @GetMapping("/findOne")
    @ResponseBody
    public Province findOne(String id){

//        Province one = provinceService.findOne(id);
//        System.out.println("one = " + one);
        return provinceService.findOne(id);
    }


    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Province province){
        Result result = new Result();
        try{
            provinceService.update(province);
            result.setMsg("更新信息成功!!!");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("更新失败!!!");
        }

        return result;
    }

    @GetMapping("/findAllPro")
    @ResponseBody
    public List<Province> findAll(){

        List<Province> pros = provinceService.findAllPro();
        return pros;
    }
}
