package com.lhc.controller;

import com.lhc.anno.Log;
import com.lhc.pojo.Emp;
import com.lhc.pojo.PageBean;
import com.lhc.pojo.Result;
import com.lhc.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 条件分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数:{}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Log
    @RequestMapping(value = "/emps/{ids}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作, ids:", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     */
    @Log
    @RequestMapping(value = "/emps", method = RequestMethod.POST)
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 根据id查询回显员工
     * @param id
     * @return
     */
    @RequestMapping(value = "/emps/{id}", method = RequestMethod.GET)
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息，id:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     * @param emp
     * @return
     */
    @Log
    @RequestMapping(value = "/emps", method = RequestMethod.PUT)
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息:{}", emp);
        empService.update(emp);
        return Result.success();
    }

}
