package entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Classname Result
 * @Description 返回码
 * @Date 2021/4/23 1:55
 * @Created by Zhao.J
 */

@ApiModel("返回码")
public class Result {

    @ApiModelProperty("是否成功")
    private boolean flag;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;

    public Result(boolean flag, Integer code, String message, Object
            data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result() {
    }
    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}