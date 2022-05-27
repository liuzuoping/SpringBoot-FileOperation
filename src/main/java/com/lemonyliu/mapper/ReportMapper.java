package com.lemonyliu.mapper;

import com.lemonyliu.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-05-15
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    @Select("select title from report where uid = #{uid}")
    List<String> selectTitleList(@Param("uid") String uid);

    @Select("select path from report where id = #{id}")
    String selectReportPathById(@Param("id") long id);

    @Select("select title from report where id = #{id}")
    String selectReportTitleById(@Param("id") long id);
}
