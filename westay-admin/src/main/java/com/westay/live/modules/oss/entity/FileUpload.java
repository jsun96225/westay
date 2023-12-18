package com.westay.live.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.westay.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("file_uploads")
public class FileUpload extends BaseEntity {
    private Long userId;
    private String fileUrl;
    private Date uploadTime;

    // Getters and Setters
}
