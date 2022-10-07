package geektime.spring.springbucks.zuoye01.controller;


import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.StudentDto;
import geektime.spring.springbucks.zuoye01.entity.Student;
import geektime.spring.springbucks.zuoye01.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author: zhengwuhao
 * @Date: 2022/08/15/20:25
 * @Description:
 */
@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private Validator validator;

    @RequestMapping("/add")
    public String add(@RequestBody StudentDto studentDto) {
        Set<ConstraintViolation<StudentDto>> violationSet = validator.validate(studentDto);
        //如果set.length()不为空，则有参数不符合校验要求
        for (ConstraintViolation<StudentDto> studentDtos : violationSet) {
            return studentDtos.getMessage();
        }
        int i = iStudentService.add(studentDto);
        if (i <= 0) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update(@RequestBody StudentDto studentDto) {
        Set<ConstraintViolation<StudentDto>> violationSet = validator.validate(studentDto);
        //如果set.length()不为空，则有参数不符合校验要求
        for (ConstraintViolation<StudentDto> studentDtos : violationSet) {
            return studentDtos.getMessage();
        }
        int i = iStudentService.update(studentDto);
        if (i <= 0) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        if (id == null) {
            return "id不能为空";
        }
        int i = iStudentService.delete(id);
        if (i <= 0) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/selectOne")
    public Student selectOne(@RequestBody StudentDto studentDto) {
        Student student = iStudentService.selectOne(studentDto);
        return student;
    }

    @RequestMapping("/selectPage")
    public QueryResult<Student> selectPage(@RequestBody StudentDto studentDto) {
        QueryResult<Student> queryResult = iStudentService.selectPage(studentDto);
        return queryResult;
    }

}
