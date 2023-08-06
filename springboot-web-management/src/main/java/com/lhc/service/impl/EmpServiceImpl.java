package com.lhc.service.impl;

import com.lhc.mapper.EmpMapper;
import com.lhc.pojo.Emp;
import com.lhc.pojo.PageBean;
import com.lhc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

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
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //获取总记录数
        Long count = empMapper.count();
        //获取分页查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.page(start, pageSize, name, gender, begin, end);
        //封装PageBean对象
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    }

    /**
     * 批量删除
     */
    public void delete(List<Integer> ids){
        empMapper.delete(ids);
    }

    /**
     * 新增员工
     * @param emp
     */
    public void save(Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    public Emp login(Emp emp){
        return empMapper.getByUsernameAndPassword(emp);
    }

}
