package geektime.spring.springbucks.zuoye01.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 学生成绩表
 * </p>
 *
 * @author zhengwuchao
 * @since 2022-08-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Fraction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 学生信息id
     */
    private Long studentId;

    /**
     * 学科
     */
    private String subject;

    /**
     * 学科名称
     */
    private String subjectName;

    /**
     * 分数
     */
    private Double fraction;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 描述
     */
    private String remarks;

}
