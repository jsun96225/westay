package com.westay.live.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_reviews")
public class UserReview extends BaseEntity {
    private Long userId;
    private String review;
    private Date reviewTime;

    // Getters and Setters
}
