package geektime.spring.springbucks.zuoye01.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author: zhengwuhao
 * @Date: 2022/08/15/20:25
 * @Description: 学生成绩
 */
@Data
public class FractionDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

    /**
     * 学生信息id
     */
    @NotEmpty(message = "学生信息id")
    private Long studentId;

    /**
     * 学科
     */
    @NotEmpty(message = "学科")
    private String subject;

    /**
     * 学科名称
     */
    @NotEmpty(message = "学科名称")
    private String subjectName;

    /**
     * 分数
     */
    @NotEmpty(message = "分数")
    private Double fraction;

    /**
     * 起始页
     */
    private Integer pageStart;

    /**
     * 查询条数
     */
    private Integer pageSize;


}
