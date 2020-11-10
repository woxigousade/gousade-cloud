package com.gousade.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: woxigousade <woxigsd@gmail.com>
 * @date: 2020/9/1/0001 20:35
 * @description: response common result class
 */
@ApiModel(description = "公共响应类")
@Data
@Builder
public class ResponseResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5352331298926161615L;

    @ApiModelProperty(value = "响应状态")
    private Boolean status;

    @ApiModelProperty(value = "响应码")
    private Integer code;

    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "响应数据")
    @Builder.Default
    private Map<String, Object> data = new HashMap<>();

    public static ResponseResult renderSuccess() {
		return ResponseResult
				.builder()
				.status(true)
				.code(StatusCode.SUCCESS)
				.message("操作成功")
				.build();
    }

    public static ResponseResult renderError() {
		return ResponseResult
				.builder()
				.status(false)
				.code(StatusCode.ERROR)
				.message("操作失败")
				.build();
    }

    public static ResponseResult renderBoolean(Boolean b) {
        if (b) {
            return renderSuccess();
        } else {
            return renderError();
        }
    }

    public ResponseResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


}
