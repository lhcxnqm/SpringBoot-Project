package com.lhc.mapper;

import com.lhc.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_emp")
    public Long count();

    /**
     * 分页查询，获取列表数据
     */
//    @Select("select * from tb_emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Integer> ids);

    @Delete("delete from tb_emp where job = #{deptId}")
    void deleteByDeptId(Integer deptId);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into tb_emp(username, name, gender, image, job, entrydate, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entryDate}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from tb_emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    @Select("select * from tb_emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
