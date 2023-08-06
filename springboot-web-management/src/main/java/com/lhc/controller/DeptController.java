package com.lhc.controller;

import com.lhc.anno.Log;
import com.lhc.pojo.Dept;
import com.lhc.pojo.Result;
import com.lhc.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 */
@Slf4j
@RestController
public class DeptController {

//    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门数据
     * @return
     */
    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 删除部门数据
     */
    @Log
    @RequestMapping(value = "/depts/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @RequestMapping(value = "/depts", method = RequestMethod.POST)
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 修改部门
     */
    @Log
    @RequestMapping(value = "/depts", method = RequestMethod.PUT)
    public Result modify(@RequestBody Dept dept){
        log.info("修改部门:{}", dept);
        deptService.modify(dept);
        return Result.success();
    }

}




