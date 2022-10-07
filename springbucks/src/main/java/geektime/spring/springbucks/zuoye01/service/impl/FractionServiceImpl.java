package geektime.spring.springbucks.zuoye01.service.impl;

import geektime.spring.springbucks.zuoye01.dto.FractionDto;
import geektime.spring.springbucks.zuoye01.dto.QueryResult;
import geektime.spring.springbucks.zuoye01.dto.RankingDto;
import geektime.spring.springbucks.zuoye01.entity.Fraction;
import geektime.spring.springbucks.zuoye01.mapper.FractionMapper;
import geektime.spring.springbucks.zuoye01.redis.RedisUtils;
import geektime.spring.springbucks.zuoye01.service.IFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 学生成绩表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-08-18
 */
@Service("iFractionService")
public class FractionServiceImpl implements IFractionService {

    @Autowired
    private FractionMapper fractionMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional
    public int add(FractionDto fractionDto) {
        return fractionMapper.insert(Fraction.builder()
                .studentId(fractionDto.getStudentId())
                .subject(fractionDto.getSubject())
                .subjectName(fractionDto.getSubjectName())
                .fraction(fractionDto.getFraction())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .state("1")//正常
                .build());
    }

    @Override
    @Transactional
    public int update(FractionDto fractionDto) {
        return fractionMapper.update(Fraction.builder()
                .id(fractionDto.getId())
                .studentId(fractionDto.getStudentId())
                .subject(fractionDto.getSubject())
                .subjectName(fractionDto.getSubjectName())
                .fraction(fractionDto.getFraction())
                .updateTime(LocalDateTime.now())
                .state("1")//正常
                .build());
    }

    @Override
    @Transactional
    public int delete(String id) {
        return fractionMapper.delete(id);
    }

    @Override
    public Fraction selectOne(FractionDto fractionDto) {

        //redis key
        String key = Fraction.class.getSimpleName() + ":" + fractionDto.getId();
        //获取缓存数据
        Object obj = redisUtils.get(key);
        if (obj != null) {
            return (Fraction) obj;
        }
        Fraction fraction = fractionMapper.selectOne(Fraction.builder()
                .studentId(fractionDto.getStudentId())
                .subject(fractionDto.getSubject())
                .subjectName(fractionDto.getSubjectName())
                .fraction(fractionDto.getFraction())
                .build());

        //添加redis缓存
        redisUtils.set(key, fraction, 3600L);
        return fraction;
    }

    @Override
    public QueryResult<Fraction> selectPage(FractionDto fractionDto) {

        //redis key
        String key = Fraction.class.getSimpleName() + ":" + fractionDto.hashCode();
        //获取缓存数据
        Object obj = redisUtils.get(key);
        if (obj != null) {
            return (QueryResult<Fraction>) obj;
        }

        Fraction fraction = Fraction.builder()
                .studentId(fractionDto.getStudentId())
                .subject(fractionDto.getSubject())
                .subjectName(fractionDto.getSubjectName())
                .fraction(fractionDto.getFraction())
                .build();
        //查询总数
        int count = fractionMapper.count(fraction);
        //查询分页数
        List<Fraction> fractions = fractionMapper.selectPage(fractionDto.getPageStart(), fractionDto.getPageSize(), fraction);

        QueryResult<Fraction> queryResult = new QueryResult();
        queryResult.setResultList(fractions);
        queryResult.setTotalRecord(count);


        //添加redis缓存
        redisUtils.set(key, queryResult, 3600L);

        return queryResult;
    }

    /**
     * 统计总分排名
     *
     * @param pageStart
     * @param pageSize
     * @return
     */
    @Override
    public QueryResult<RankingDto> ranking(int pageStart, int pageSize) {

        //根据学号分页统计总分数
        List<RankingDto> rankingDtos = fractionMapper.selectByStudentId(pageStart, pageSize);
        //查询总数
        int count = fractionMapper.count(new Fraction());
        QueryResult<RankingDto> queryResult = new QueryResult();
        queryResult.setResultList(rankingDtos);
        queryResult.setTotalRecord(count);
        return queryResult;
    }

    @Override
    public Fraction selectById(Long id) {
        Fraction fraction = new Fraction();
        fraction.setId(id);
        return fractionMapper.selectOne(fraction);
    }
}
