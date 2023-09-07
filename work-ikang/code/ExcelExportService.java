package com.ikang.idata.search.search.common;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.service.BaseService;
import com.ikang.idata.search.search.util.ReflectUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 导出服务
 * @date 2022/2/18 14:17
 */
@Slf4j
public class ExcelExportService {
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String GROUP_BY = "groupBy";
    public static final String FIND_GROUP_BY = "findGroupBy";
    public static final String FIND = "find";
    public static final String SHEET_NAME = "数据列表";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    public static final String UTF_8 = "utf-8";
    public static final String UTF_81 = "UTF-8";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String CONTENT_DISPOSITION1 = "Content-Disposition";
    public static final String ATTACHMENT_FILE_NAME = "attachment;filename*=utf-8''";
    public static final String XLSX = ".xlsx";
    public static final String REPLACEMENT = "%20";
    private final BaseService baseService;

    public ExcelExportService(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * 基础导出 优化
     *
     * @param response 响应
     * @param clz      服务类
     * @param param    参数
     * @param title    表名
     * @throws IOException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 15:47
     */
    public void export(HttpServletResponse response, Class<?> clz, Object param, String title) throws IOException {
        setHeader(response, title);
        BaseSearch returnData;
        JSONObject object = (JSONObject) JSON.toJSON(param);
        if (CollUtil.isNotEmpty(object.getJSONArray(GROUP_BY))) {
            //分组统计分析
            returnData = ReflectUtils.springInvokeMethod(clz, FIND_GROUP_BY, param);
            EasyExcel.write(response.getOutputStream()).head(baseService.head(returnData.getColumnNames()))
                    .sheet(SHEET_NAME).doWrite(baseService.dataListGroup(returnData.getListMap(), returnData.getColumnNames()));
        } else {
            object.fluentPut(PAGE_NUM, MagicConst.INTEGER_1).fluentPut(PAGE_SIZE, MagicConst.PAGE_SIZE_9999);
            returnData = ReflectUtils.springInvokeMethod(clz, FIND, param);
            EasyExcel.write(response.getOutputStream()).head(baseService.head(returnData.getColumnNames()))
                    .sheet(SHEET_NAME).doWrite(baseService.dataList(returnData.getReturnData(), returnData.getColumnNames()));
        }
    }

    /**
     * 直接导出,调用find方法
     *
     * @param response 响应
     * @param service  服务类
     * @param param    参数
     * @param title    表名
     * @throws IOException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date 2022/11/30 17:00
     */
    public void findExport(HttpServletResponse response, Object service, Object param, String title) throws IOException {
        setHeader(response, title);
        BaseSearch returnData = ReflectUtils.springInvokeMethod(service, FIND, param);
        EasyExcel.write(response.getOutputStream()).head(baseService.head(returnData.getColumnNames()))
                .sheet(SHEET_NAME).doWrite(baseService.dataList(returnData.getReturnData(), returnData.getColumnNames()));
    }

    /**
     * Class导出
     * @param response 响应
     * @param data 导出的数据
     * @param clz 导出类
     * @param title 文件名
     * @throws IOException
     */
    public <T> void  exportByClass(HttpServletResponse response, List<?> data, Class<T> clz, String title) throws IOException {
        setHeader(response, title);
        EasyExcel.write(response.getOutputStream(), clz).sheet(SHEET_NAME).doWrite(data);
    }

    /**
     * 通过 find方法, findGroupBy方法 导出数据
     *
     * @param response 响应
     * @param service  服务
     * @param param    参数
     * @param title    表名
     * @throws IOException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 19:35
     */
    public void export(HttpServletResponse response, Object service, Object param, String title) throws IOException {
        export(response, service, param, title, null, null);
    }

    /**
     * 通过 指定方法 导出数据
     *
     * @param response 响应
     * @param service  服务
     * @param param    参数
     * @param title    表名
     * @throws IOException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 19:35
     */
    public void exportByMethodAndBaseSearch(HttpServletResponse response, Object service,String methodName, Object param, String title) throws IOException {
        export(response, service, param, title, methodName, null);
    }

    /**
     * 通过方法名 来导出数据
     *
     * @param response   响应
     * @param service    服务
     * @param param      参数
     * @param title      表名
     * @param findMethod 基本查询方法
     * @param aggrMethod 聚合查询方法
     * @throws IOException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 19:37
     */
    public void export(HttpServletResponse response, Object service, Object param, String title, String findMethod, String aggrMethod) throws IOException {
        setHeader(response, title);
        BaseSearch returnData;
        JSONObject object = (JSONObject) JSON.toJSON(param);
        if (CollUtil.isNotEmpty(object.getJSONArray(GROUP_BY))) {
            //分组统计分析
            returnData = ReflectUtils.springInvokeMethod(service, StringUtil.isEmpty(aggrMethod) ? FIND_GROUP_BY : aggrMethod, param);
            EasyExcel.write(response.getOutputStream()).head(baseService.head(returnData.getColumnNames()))
                    .sheet(SHEET_NAME).doWrite(baseService.dataListGroup(returnData.getListMap(), returnData.getColumnNames()));
        } else {
            object.fluentPut(PAGE_NUM, MagicConst.INTEGER_1).fluentPut(PAGE_SIZE, MagicConst.PAGE_SIZE_9999);
            returnData = ReflectUtils.springInvokeMethod(service, StringUtil.isEmpty(findMethod) ? FIND : findMethod, object.toJavaObject(param.getClass()));
            EasyExcel.write(response.getOutputStream()).head(baseService.head(returnData.getColumnNames()))
                    .sheet(SHEET_NAME).doWrite(baseService.dataList(returnData.getReturnData(), returnData.getColumnNames()));
        }
    }

    /**
     * @param response 响应
     * @param title    表名
     * @throws UnsupportedEncodingException 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 19:38
     */
    public void setHeader(HttpServletResponse response, String title) throws UnsupportedEncodingException {
        //设置返回的数据格式
        response.setContentType(APPLICATION_VND_MS_EXCEL);
        //设置返回的数据编码
        response.setCharacterEncoding(UTF_8);
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(title, UTF_81).replaceAll("\\+", REPLACEMENT);
//        String fileName = "电商主题";
        response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILE_NAME + fileName + XLSX);
        // 服务端要在header设置Access-Control-Expose-Headers, 前端才能正常获取到
        response.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS, CONTENT_DISPOSITION1);
    }
}
