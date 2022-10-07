package geektime.spring.springbucks.zuoye01.service.impl;

import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.StudentDto;
import geektime.spring.springbucks.zuoye01.entity.Student;
import geektime.spring.springbucks.zuoye01.mapper.StudentMapper;
import geektime.spring.springbucks.zuoye01.redis.RedisUtils;
import geektime.spring.springbucks.zuoye01.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-08-18
 */
@Service("iStudentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    @Transactional
    public int add(StudentDto studentDto) {
        return studentMapper.insert(Student.builder()
                .name(studentDto.getName())
                .studentNum(studentDto.getStudentNum())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .state("1")//正常
                .build());
    }

    @Override
    @Transactional
    public int update(StudentDto studentDto) {
        return studentMapper.update(Student.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .studentNum(studentDto.getStudentNum())
                .updateTime(LocalDateTime.now())
                .state("1")//正常
                .build());
    }

    @Override
    @Transactional
    public int delete(String id) {
        return studentMapper.delete(id);
    }

    @Override
    public Student selectOne(StudentDto studentDto) {

        //redis key
        String key = Student.class.getSimpleName() + ":" + studentDto.getId();
        //获取缓存数据
        Object obj = redisUtils.get(key);
        if (obj != null) {
            return (Student) obj;
        }

        Student student = studentMapper.selectOne(Student.builder()
                .id(studentDto.getId())
                .build());
        redisUtils.set(key, student, 3600L);
        return student;
    }

    @Override
    public QueryResult<Student> selectPage(StudentDto studentDto) {

        //redis key
        String key = Student.class.getSimpleName() + ":" + studentDto.hashCode();
        //获取缓存数据
        Object obj = redisUtils.get(key);
        if (obj != null) {
            return (QueryResult<Student>) obj;
        }

        Student student = Student.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .studentNum(studentDto.getStudentNum())
                .state("1")//正常
                .build();
        //查询总数
        int count = studentMapper.count(student);
        //查询分页数
        List<Student> students = studentMapper.selectPage(studentDto.getPageStart(), studentDto.getPageSize(), student);

        QueryResult<Student> queryResult = new QueryResult();
        queryResult.setResultList(students);
        queryResult.setTotalRecord(count);

        //添加redis缓存
        redisUtils.set(key, queryResult, 3600L);

        return queryResult;
    }

    @Override
    public Student selectById(Long id) {
        Student student = new Student();
        student.setId(id);
        return studentMapper.selectOne(student);
    }
}
