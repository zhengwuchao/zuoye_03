package geektime.spring.springbucks.zuoye01.controller;

import geektime.spring.springbucks.zuoye01.entity.Fraction;
import geektime.spring.springbucks.zuoye01.entity.Student;
import geektime.spring.springbucks.zuoye01.service.IFractionService;
import geektime.spring.springbucks.zuoye01.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 第二次作业
 */
@RestController
public class RestfulController {

    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private IFractionService iFractionService;

    /**
     * 返回json数据格式
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public Student selectOne(@PathVariable Long id) {
        Student student = iStudentService.selectById(id);
        return student;
    }


    /**
     * 返回xml数据格式
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/query/xml/{id}",produces = MediaType.APPLICATION_XML_VALUE)
    public Fraction selectOneXml(@PathVariable Long id) {
        Fraction fraction = iFractionService.selectById(id);
        return fraction;
    }
}
