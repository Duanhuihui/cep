package xyz.eduplatform.cep.common;

import lombok.Builder;

import java.util.Date;

@Builder
public class ResultVO {
    /**
     * 响应码
     */
    public int code;
    /**
     * 返回的数据
     */
    public Object data;
    /**
     * 时间戳
     */
    public Date timestamp;

    /**
     * 消息
     */
    public String msg;

    public static ResultVO error() {
        return ResultVO.builder().code(ResultStatus.FAIL).data(false).timestamp(new Date()).build();
    }

    public static ResultVO error(Object data) {
        return ResultVO.builder().code(ResultStatus.FAIL).data(data).timestamp(new Date()).build();
    }

    public static ResultVO success() {
        return ResultVO.builder().code(ResultStatus.SUCCESS).data(true).timestamp(new Date()).build();
    }

    public static ResultVO success(Object data) {
        return ResultVO.builder().code(ResultStatus.SUCCESS).data(data).timestamp(new Date()).build();
    }
}
