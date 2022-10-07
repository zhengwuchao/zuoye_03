package geektime.spring.springbucks.zuoye01.controller;


import geektime.spring.springbucks.zuoye01.dto.FractionDto;
import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.RankingDto;
import geektime.spring.springbucks.zuoye01.dto.StudentDto;
import geektime.spring.springbucks.zuoye01.entity.Fraction;
import geektime.spring.springbucks.zuoye01.service.IFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhengwuhao
 * @Date: 2022/08/15/20:25
 * @Description:
 */
@RestController
@RequestMapping("/fraction")
@Validated
public class FractionController {

    @Autowired
    private IFractionService iFractionService;
    @Autowired
    private Validator validator;

    @RequestMapping("/add")
    public String add(@RequestBody FractionDto fractionDto) {
        Set<ConstraintViolation<FractionDto>> violationSet = validator.validate(fractionDto);
        //如果set.length()不为空，则有参数不符合校验要求
        for (ConstraintViolation<FractionDto> fractionDtos : violationSet) {
            return fractionDtos.getMessage();
        }
        int i = iFractionService.add(fractionDto);
        if (i <= 0) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/update")
    public String update(@RequestBody FractionDto fractionDto) {
        Set<ConstraintViolation<FractionDto>> violationSet = validator.validate(fractionDto);
        //如果set.length()不为空，则有参数不符合校验要求
        for (ConstraintViolation<FractionDto> fractionDtos : violationSet) {
            return fractionDtos.getMessage();
        }
        int i = iFractionService.update(fractionDto);
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
        int i = iFractionService.delete(id);
        if (i <= 0) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("/selectOne")
    public Fraction selectOne(@RequestBody FractionDto fractionDto) {
        Fraction fraction = iFractionService.selectOne(fractionDto);
        return fraction;
    }

    @RequestMapping("/selectPage")
    public QueryResult<Fraction> selectPage(@RequestBody FractionDto fractionDto) {
        QueryResult<Fraction> queryResult = iFractionService.selectPage(fractionDto);
        return queryResult;
    }

    /**
     * 总分排名
     *
     * @return
     */
    @RequestMapping("/ranking")
    public QueryResult<RankingDto> ranking(@RequestParam Integer pageStart, @RequestParam Integer pageSize) {
        QueryResult<RankingDto> queryResult = iFractionService.ranking(pageStart, pageSize);
        return queryResult;
    }




}
