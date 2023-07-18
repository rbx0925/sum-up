package com.ikang.idata.common.utils;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 编写自定义Typehandler
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2021年07月25日 下午 2:01
 */
public class HobbyTypehandler {
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
//        if (CollUtil.isNotEmpty(strings)) {
//            //1.List集合转字符串
//            StringBuffer sb = new StringBuffer();
//            for (String string : strings) {
//                sb.append(string).append(",");
//            }
//            //2.设置给ps
//            preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
//        }else {
//            preparedStatement.setString(i, "");
//        }
//    }
//    @Override
//    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        String string = resultSet.getString(s);
//        if (StringUtils.isBlank(string)) {
//            return new ArrayList<>();
//        }
//        String[] split = resultSet.getString(s).split(",");
//        return Arrays.asList(split);
//    }
//    @Override
//    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        String string = resultSet.getString(i);
//        if (StringUtils.isBlank(string)) {
//            return new ArrayList<>();
//        }
//        String[] split = resultSet.getString(i).split(",");
//        return Arrays.asList(split);
//    }
//    @Override
//    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        String string = callableStatement.getString(i);
//        if (StringUtils.isBlank(string)) {
//            return new ArrayList<>();
//        }
//        String[] split = callableStatement.getString(i).split(",");
//        return Arrays.asList(split);
//    }
}
