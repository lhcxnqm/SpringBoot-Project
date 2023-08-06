package com.lhc.mapper;

import com.lhc.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    @Select("select * from tb_dept")
    List<Dept> list();

    @Delete("delete from tb_dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into tb_dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Update("update tb_dept set name = #{name} where id = #{id}")
    void update(Dept dept);
}
