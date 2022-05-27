package com.lemonyliu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ${author}
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("report")
@ApiModel(value = "Report对象", description = "")
public class Report extends Model<Report> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("cid")
    private Long cid;

    @TableField("uid")
    private String uid;

    @TableField("access")
    private Integer access;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("last_update_time")
    private LocalDateTime lastUpdateTime;

    @TableField("version")
    private String version;

    @TableField("size")
    private String size;

    @ApiModelProperty(value = "删除标识（0 否 1 是）")
    @TableField("del_flag")
    private Boolean delFlag;

    @TableField("path")
    private String path;

    @TableField("cid0")
    private Long cid0;

    @TableField("version_lock")
    private Integer versionLock;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
