package geektime.spring.springbucks.zuoye01.service;

import geektime.spring.springbucks.zuoye01.dto.FractionDto;
import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.RankingDto;
import geektime.spring.springbucks.zuoye01.entity.Fraction;

/**
 * <p>
 * 学生成绩表 服务类
 * </p>
 *
 * @author admin
 * @since 2022-08-18
 */
public interface IFractionService {

    int add(FractionDto fractionDto);

    int update(FractionDto fractionDto);

    int delete(String id);

    Fraction selectOne(FractionDto fractionDto);

    QueryResult<Fraction> selectPage(FractionDto fractionDto);

    QueryResult<RankingDto> ranking(int pageStart, int pageSize);

    Fraction selectById(Long id);
}
