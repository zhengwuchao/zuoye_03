package geektime.spring.springbucks.zuoye01.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: zhengwuhao
 * @Date: 2022/08/15/20:25
 * @Description: 排名统计
 */
@Getter
@Setter
public class RankingDto {

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 总分
     */
    private Double totalScore;

    /**
     * 排名
     */
    private Integer ranking;
}
