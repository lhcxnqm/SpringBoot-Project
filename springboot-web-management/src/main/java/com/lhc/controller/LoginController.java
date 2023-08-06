package com.lhc.controller;

import com.lhc.pojo.Emp;
import com.lhc.pojo.Result;
import com.lhc.service.EmpService;
import com.lhc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Emp emp){
        log.info("用户登录:{}", emp);
        Emp e = empService.login(emp);

        //登录成功，生成令牌
        if(e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        //登录失败，返回错误信息
        return Result.error("用户名或密码出错");
    }
}
