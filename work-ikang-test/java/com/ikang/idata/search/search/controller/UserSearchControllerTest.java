package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.CheckItem;
import com.ikang.idata.common.entity.Item;
import com.ikang.idata.common.entity.UserSearchTwo;
import com.ikang.idata.common.entity.vo.UserSearchTwoVO;
import com.ikang.idata.common.entity.vo.UserSearchVO;
import com.ikang.idata.search.search.common.SoMap;
import com.ikang.idata.search.search.service.UserSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserSearchController.class)
class UserSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSearchService mockUserSearchService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure UserSearchService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");
        when(mockUserSearchService.find(any(UserSearchVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/userSearch/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindByTimeAndOrigin() throws Exception {
        // Setup
        // Configure UserSearchService.findByTimeAndOrigin(...).
        final UserSearchTwo userSearchTwo = new UserSearchTwo();
        userSearchTwo.setTotalStr("totalStr");
        userSearchTwo.setPageSize(0);
        userSearchTwo.setPageNum(0);
        userSearchTwo.setMapList(Arrays.asList(new HashMap<>()));
        userSearchTwo.setTotal(0);
        userSearchTwo.setColumnNames(Arrays.asList());
        when(mockUserSearchService.findByTimeAndOrigin(any(UserSearchTwoVO.class))).thenReturn(userSearchTwo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/userSearch/findByTimeAndOrigin")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowCheckItem() throws Exception {
        // Setup
        // Configure UserSearchService.showCheckItem(...).
        final CheckItem checkItem = new CheckItem();
        checkItem.setdCode("dCode");
        checkItem.setdName("dName");
        final Item item = new Item();
        item.setReportCode("reportCode");
        item.setReportName("reportName");
        item.setReportmapping("reportmapping");
        checkItem.setItems(Arrays.asList(item));
        final List<CheckItem> checkItems = Arrays.asList(checkItem);
        when(mockUserSearchService.showCheckItem()).thenReturn(checkItems);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/userSearch/showCheckItem")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowCheckItem_UserSearchServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserSearchService.showCheckItem()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/userSearch/showCheckItem")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        // Configure UserSearchService.findGroupBy(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");
        when(mockUserSearchService.findGroupBy(any(UserSearchVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/userSearch/findGroupBy")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/userSearch/path")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserSearchService).exportExcel(any(UserSearchVO.class), any(HttpServletResponse.class));
    }

    @Test
    void testExportExcel_UserSearchServiceThrowsException() throws Exception {
        // Setup
        doThrow(Exception.class).when(mockUserSearchService).exportExcel(any(UserSearchVO.class),
                any(HttpServletResponse.class));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/userSearch/path")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    /**
     * 将一个一维集合转换为树形集合
     * @param list         集合
     * @param idKey        id标识key
     * @param parentIdKey  父id标识key
     * @param childListKey 子节点标识key
     * @return 转换后的tree集合
     */
    public static List<com.ikang.idata.search.search.common.SoMap> listToTree(List<com.ikang.idata.search.search.common.SoMap> list, String idKey, String parentIdKey, String childListKey) {
        // 声明新的集合，存储tree形数据
        List<com.ikang.idata.search.search.common.SoMap> newTreeList = new ArrayList<SoMap>();
        // 声明hash-Map，方便查找数据
        com.ikang.idata.search.search.common.SoMap hash = new com.ikang.idata.search.search.common.SoMap();
        // 将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < list.size(); i++) {
            com.ikang.idata.search.search.common.SoMap json = (com.ikang.idata.search.search.common.SoMap) list.get(i);
            hash.put(json.getString(idKey), json);
        }
        // 遍历结果集
        for (int j = 0; j < list.size(); j++) {
            // 单条记录
            com.ikang.idata.search.search.common.SoMap aVal = (com.ikang.idata.search.search.common.SoMap) list.get(j);
            // 在hash中取出key为单条记录中pid的值
            com.ikang.idata.search.search.common.SoMap hashVp = (com.ikang.idata.search.search.common.SoMap) hash.get(aVal.get(parentIdKey, "").toString());
            // 如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVp != null) {
                // 检查是否有child属性，有则添加，没有则新建
                if (hashVp.get(childListKey) != null) {
                    @SuppressWarnings("unchecked")
                    List<com.ikang.idata.search.search.common.SoMap> ch = (List<com.ikang.idata.search.search.common.SoMap>) hashVp.get(childListKey);
                    ch.add(aVal);
                    hashVp.put(childListKey, ch);
                } else {
                    List<com.ikang.idata.search.search.common.SoMap> ch = new ArrayList<com.ikang.idata.search.search.common.SoMap>();
                    ch.add(aVal);
                    hashVp.put(childListKey, ch);
                }
            } else {
                newTreeList.add(aVal);
            }
        }
        return newTreeList;
    }



    /** 指定字符串的字符串下划线转大写模式 */
    private static String wordEachBig(String str){
        String newStr = "";
        for (String s : str.split("_")) {
            newStr += wordFirstBig(s);
        }
        return newStr;
    }
    /** 返回下划线转小驼峰形式 */
    private static String wordEachBigFs(String str){
        return wordFirstSmall(wordEachBig(str));
    }

    /** 将指定单词首字母大写 */
    private static String wordFirstBig(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    /** 将指定单词首字母小写 */
    private static String wordFirstSmall(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }

    /** 下划线转中划线 */
    private static String wordEachKebabCase(String str) {
        return str.replaceAll("_", "-");
    }

    /** 驼峰转下划线  */
    private static String wordHumpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

}
