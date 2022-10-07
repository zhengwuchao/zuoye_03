package geektime.spring.springbucks.zuoye01.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhengwuhao
 * @Date: 2022/08/15/20:25
 * @Description: 分页返回
 */
public class QueryResult<T> implements Serializable {

    private static final long serialVersionUID = -4747243863363981917L;

    private transient List<T> resultList = new ArrayList();
    private long totalRecord;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
