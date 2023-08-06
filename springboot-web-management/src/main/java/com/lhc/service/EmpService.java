package com.lhc.service;

import com.lhc.pojo.Emp;
import com.lhc.pojo.PageBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
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
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    Emp login(Emp emp);
}
