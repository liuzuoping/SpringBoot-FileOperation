package com.lemonyliu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("report_group_access")
@ApiModel(value = "ReportGroupAccess对象", description = "")
public class ReportGroupAccess extends Model<ReportGroupAccess> {

    private static final long serialVersionUID = 1L;

    @TableField("rid")
    private Long rid;

    @TableField("group_id")
    private Long groupId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
