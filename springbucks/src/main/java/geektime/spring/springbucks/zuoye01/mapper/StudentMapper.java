package geektime.spring.springbucks.zuoye01.mapper;

import geektime.spring.springbucks.zuoye01.entity.Fraction;
import geektime.spring.springbucks.zuoye01.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学生信息表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-08-18
 */
@Mapper
public interface StudentMapper {

    int insert(Student student);

    int update(Student student);

    int delete(String id);

    Student selectOne(Student student);

    int count(Student student);

    List<Student> selectPage(@Param("pageStart") int pageStart, @Param("pageSize") int pageSize, Student student);

}
