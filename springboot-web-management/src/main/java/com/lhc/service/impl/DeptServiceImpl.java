package com.lhc.service.impl;

import com.lhc.mapper.DeptMapper;
import com.lhc.mapper.EmpMapper;
import com.lhc.pojo.Dept;
import com.lhc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    public List<Dept> list(){
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //所有异常都回滚
    public void delete(Integer id){
        deptMapper.deleteById(id);  //根据id删除部门

        empMapper.deleteByDeptId(id);   //根据id删除部门下的员工
    }

    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    public void modify(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
