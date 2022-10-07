package geektime.spring.springbucks.zuoye01.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 学生信息表
 * </p>
 *
 * @author zhengwuchao
 * @since 2022-08-18
 */
@Data
public class StudentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    @NotEmpty(message = "姓名")
    private String name;

    /**
     * 学号
     */
    @NotEmpty(message = "学号")
    private Integer studentNum;

    /**
     * 起始页
     */
    private Integer pageStart;

    /**
     * 查询条数
     */
    private Integer pageSize;


}
