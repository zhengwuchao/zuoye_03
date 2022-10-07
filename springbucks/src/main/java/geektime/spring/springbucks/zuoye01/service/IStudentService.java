package geektime.spring.springbucks.zuoye01.service;

import geektime.spring.springbucks.zuoye01.dto.FractionDto;
import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.StudentDto;
import geektime.spring.springbucks.zuoye01.entity.Fraction;
import geektime.spring.springbucks.zuoye01.entity.Student;

/**
 * <p>
 * 学生信息表 服务类
 * </p>
 *
 * @author admin
 * @since 2022-08-18
 */
public interface IStudentService {

    int add(StudentDto studentDto);

    int update(StudentDto studentDto);

    int delete(String id);

    Student selectOne(StudentDto studentDto);

    QueryResult<Student> selectPage(StudentDto studentDto);

    Student selectById(Long id);
}
