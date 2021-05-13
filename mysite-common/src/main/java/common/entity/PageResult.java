package common.entity;

import lombok.Data;

import java.util.List;

/**
 * @Classname PageResult
 * @Description 分页
 * @Date 2021/4/23 1:57
 * @Created by Zhao.J
 */

@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;
    public PageResult(Long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
}