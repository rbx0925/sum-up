package com.rbx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rbx.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("usrId") Long usrId);
}