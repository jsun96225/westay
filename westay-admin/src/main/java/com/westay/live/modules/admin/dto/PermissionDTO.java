package com.westay.live.modules.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westay.common.validator.group.AddGroup;
import com.westay.common.validator.group.DefaultGroup;
import com.westay.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限数据传输对象
 */
@Data
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Null(message="{id.null}", groups = AddGroup.class)
    @NotNull(message="{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "permission名称")
    @NotBlank(message="{sysmenu.name.require}", groups = DefaultGroup.class)
    private String name;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;
    // 可以根据需要添加更多字段
}