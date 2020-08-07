package com.csii.travels.controller;

import com.csii.travels.entity.Result;
import com.csii.travels.entity.User;
import com.csii.travels.service.UserService;
import com.csii.travels.utils.CreateImageCode;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("user")
@Controller
@CrossOrigin  //允许跨域
@Slf4j
public class UserController {


    //注入UserService
    @Autowired
    private UserService userService;
    /** 用户注册
      * @Description:
      * @Param: [code, user]
      * @return:
      */
    @PostMapping("/register")
    @ResponseBody
    public Result register(String code , String key , @RequestBody User user, HttpServletRequest request){

        Result result = new Result();
        log.info("接收的验证码:" + code);
        log.info("接收的对象:" + user);
        log.info("接收的key:"+key);
        String keyCode = (String) request.getServletContext().getAttribute(key);
        log.info("接收的code"+keyCode);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                //注册用户
                userService.register(user);
                result.setMsg("注册成功!!!");
            } else {
                throw new RuntimeException("验证码错误");

            }
        }catch(Exception e) {
                e.printStackTrace();
                result.setMsg(e.getMessage());
                result.setState(false);
            }
        return result;
    }


    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user,HttpServletRequest request){
        Result result = new Result();

        try{
            User resUser = userService.login(user);
            result.setMsg("登录成功");
            result.setUserId(resUser.getId());
            //将用户信息保存到域中
            request.getServletContext().setAttribute(resUser.getId(),resUser);
        }catch(Exception e){
            result.setState(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }




    /** 生成验证码
      * @Description:
      * @Param: [response, session]
      * @return:
      */
    @GetMapping("/getImage")
    @ResponseBody
    public Map<String,String> getImage(HttpServletRequest request) throws IOException {

//        //获取验证码
//        String code = ValidateImageCodeUtils.getSecurityCode();
//        //验证码存入session
//        session.setAttribute("code",code);
//        //生成图片
//        BufferedImage image = ValidateImageCodeUtils.createImage(code);
//        //相应浏览器
//        //设置相应类型
//        response.setContentType("image/png");
//        ImageIO.write(image,"png",response.getOutputStream());

        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        String code = createImageCode.getCode();

        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key,code);

        BufferedImage image = createImageCode.getBuffImg();

        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image,"png",bos);

        String string = Base64Utils.encodeToString(bos.toByteArray());

        result.put("key",key);
        result.put("image",string);
        return result;
    }
}
