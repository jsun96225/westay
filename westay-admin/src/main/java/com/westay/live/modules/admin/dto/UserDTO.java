package com.westay.live.modules.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.westay.common.validator.group.AddGroup;
import com.westay.common.validator.group.DefaultGroup;
import com.westay.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "用户管理")
public class UserDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @Null(message="{id.null}", groups = AddGroup.class)
    @NotNull(message="{id.require}", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message="{sysuser.username.require}", groups = DefaultGroup.class)
    private String username;

    @ApiModelProperty(value = "密码")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message="{sysuser.password.require}", groups = AddGroup.class)
    private String password;

    @ApiModelProperty(value = "姓", required = true)
    @NotBlank(message="{sysuser.realname.require}", groups = DefaultGroup.class)
    private String lastname;

    @ApiModelProperty(value = "名", required = true)
    @NotBlank(message="{sysuser.realname.require}", groups = DefaultGroup.class)
    private String firstname;

    @ApiModelProperty(value = "头像")
    private String headUrl;

    @ApiModelProperty(value = "性别   0：男   1：女    2：保密", required = true)
    @Range(min=0, max=2, message = "{sysuser.gender.range}", groups = DefaultGroup.class)
    private Integer gender;

    @ApiModelProperty(value = "邮箱")
    @Email(message="{sysuser.email.error}", groups = DefaultGroup.class)
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态  0：停用    1：正常", required = true)
    @Range(min=0, max=1, message = "{sysuser.status.range}", groups = DefaultGroup.class)
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateDate;

    @ApiModelProperty(value = "vip   0：否   1：是")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer vipUser;

    @ApiModelProperty(value = "角色ID列表")
    private List<Long> roleIdList;

    private String profilePictureUrl;

    private Integer points;

}
