package com.ikang.idata.search.search.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class SoMap extends LinkedHashMap<String, Object> {


    private static final long serialVersionUID = 1L;

    public SoMap() {
    }


    /** 以下元素会在isNull函数中被判定为Null， */
    public static final Object[] NULL_ELEMENT_ARRAY = {null, ""};
    public static final List<Object> NULL_ELEMENT_LIST;


    static {
        NULL_ELEMENT_LIST = Arrays.asList(NULL_ELEMENT_ARRAY);
    }
    // 工具方法

    /**
     * 取出一个值，我保证不乱码,tomcat8及以上版本此方法废掉
     */
    public static String getParam(HttpServletRequest request, String key) {
        try {
            request.setCharacterEncoding("UTF-8");
            String value = request.getParameter(key); // 获得v
            if (value != null && request.getMethod().equals("GET")) {
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
            }
            return value;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    // ============================= 读值 =============================

    /** 获取一个值 */
    @Override
    public Object get(Object key) {
        if("this".equals(key)) {
            return this;
        }
        return super.get(key);
    }

    /** 如果为空，则返回默认值 */
    public Object get(Object key, Object defaultValue) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return defaultValue;
        }
        return value;
    }

    /** 转为String并返回 */
    public String getString(String key) {
        Object value = get(key);
        if(value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    /** 如果为空，则返回默认值 */
    public String getString(String key, String defaultValue) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return defaultValue;
        }
        return String.valueOf(value);
    }

    /** 转为int并返回 */
    public int getInt(String key) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return 0;
        }
        return Integer.valueOf(String.valueOf(value));
    }
    /** 转为int并返回，同时指定默认值 */
    public int getInt(String key, int defaultValue) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return defaultValue;
        }
        return Integer.valueOf(String.valueOf(value));
    }

    /** 转为long并返回 */
    public long getLong(String key) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return 0;
        }
        return Long.valueOf(String.valueOf(value));
    }

    /** 转为double并返回 */
    public double getDouble(String key) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return 0.0;
        }
        return Double.valueOf(String.valueOf(value));
    }

    /** 转为boolean并返回 */
    public boolean getBoolean(String key) {
        Object value = get(key);
        if(valueIsNull(value)) {
            return false;
        }
        return Boolean.valueOf(String.valueOf(value));
    }

    /** 转为Date并返回，根据自定义格式 */
    public Date getDateByFormat(String key, String format) {
        try {
            return new SimpleDateFormat(format).parse(getString(key));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 转为Date并返回，根据格式： yyyy-MM-dd */
    public Date getDate(String key) {
        return getDateByFormat(key, "yyyy-MM-dd");
    }

    /** 转为Date并返回，根据格式： yyyy-MM-dd HH:mm:ss */
    public Date getDateTime(String key) {
        return getDateByFormat(key, "yyyy-MM-dd HH:mm:ss");
    }

    /** 获取集合(必须原先就是个集合，否则会创建个新集合并返回) */
    @SuppressWarnings("unchecked")
    public List<Object> getList(String key) {
        Object value = get(key);
        List<Object> list = null;
        if(value == null || value.equals("")) {
            list = new ArrayList<Object>();
        }
        else if(value instanceof List) {
            list = (List<Object>)value;
        } else {
            list = new ArrayList<Object>();
            list.add(value);
        }
        return list;
    }

    /** 获取集合 (指定泛型类型) */
    public <T> List<T> getList(String key, Class<T> cs) {
        List<Object> list = getList(key);
        List<T> list2 = new ArrayList<T>();
        for (Object obj : list) {
            T objC = getValueByClass(obj, cs);
            list2.add(objC);
        }
        return list2;
    }

    /** 获取集合(逗号分隔式)，(指定类型) */
    public <T> List<T> getListByComma(String key, Class<T> cs) {
        String listStr = getString(key);
        if(listStr == null || listStr.equals("")) {
            return new ArrayList<>();
        }
        // 开始转化
        String [] arr = listStr.split(",");
        List<T> list = new ArrayList<T>();
        for (String str : arr) {
            if(cs == int.class || cs == Integer.class || cs == long.class || cs == Long.class) {
                str = str.trim();
            }
            T objC = getValueByClass(str, cs);
            list.add(objC);
        }
        return list;
    }


    /** 根据指定类型从map中取值，返回实体对象 */
    public <T> T getModel(Class<T> cs) {
        try {
            return getModelByObject(cs.newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 从map中取值，塞到一个对象中 */
    public <T> T getModelByObject(T obj) {
        // 获取类型
        Class<?> cs = obj.getClass();
        // 循环复制
        for (Field field : cs.getDeclaredFields()) {
            try {
                // 获取对象
                Object value = this.get(field.getName());
                if(value == null) {
                    continue;
                }
                field.setAccessible(true);
                Object valueConvert = getValueByClass(value, field.getType());
                field.set(obj, valueConvert);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException("属性取值出错：" + field.getName(), e);
            }
        }
        return obj;
    }



    /**
     * 将指定值转化为指定类型并返回
     * @param obj
     * @param cs
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getValueByClass(Object obj, Class<T> cs) {
        String obj2 = String.valueOf(obj);
        Object obj3 = null;
        if (cs.equals(String.class)) {
            obj3 = obj2;
        } else if (cs.equals(int.class) || cs.equals(Integer.class)) {
            obj3 = Integer.valueOf(obj2);
        } else if (cs.equals(long.class) || cs.equals(Long.class)) {
            obj3 = Long.valueOf(obj2);
        } else if (cs.equals(short.class) || cs.equals(Short.class)) {
            obj3 = Short.valueOf(obj2);
        } else if (cs.equals(byte.class) || cs.equals(Byte.class)) {
            obj3 = Byte.valueOf(obj2);
        } else if (cs.equals(float.class) || cs.equals(Float.class)) {
            obj3 = Float.valueOf(obj2);
        } else if (cs.equals(double.class) || cs.equals(Double.class)) {
            obj3 = Double.valueOf(obj2);
        } else if (cs.equals(boolean.class) || cs.equals(Boolean.class)) {
            obj3 = Boolean.valueOf(obj2);
        } else {
            obj3 = (T)obj;
        }
        return (T)obj3;
    }


    // ============================= 写值 =============================

    /**
     * 给指定key添加一个默认值（只有在这个key原来无值的情况先才会set进去）
     */
    public void setDefaultValue(String key, Object defaultValue) {
        if(isNull(key)) {
            set(key, defaultValue);
        }
    }

    /** set一个值，连缀风格 */
    public com.ikang.idata.search.search.util.SoMap set(String key, Object value) {
        // 防止敏感key
        if(key.toLowerCase().equals("this")) {
            return this;
        }
        put(key, value);
        return this;
    }

    /** 将一个Map塞进SoMap */
    public com.ikang.idata.search.search.util.SoMap setMap(Map<String, ?> map) {
        if(map != null) {
            for (String key : map.keySet()) {
                this.set(key, map.get(key));
            }
        }
        return this;
    }

    /** 将一个对象解析塞进SoMap */
    public com.ikang.idata.search.search.util.SoMap setModel(Object model) {
        if(model == null) {
            return this;
        }
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            try{
                field.setAccessible(true);
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(!isStatic) {
                    this.set(field.getName(), field.get(model));
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return this;
    }


    /** 与isNull()相反 */
    public boolean isNotNull(String key) {
        return !isNull(key);
    }
    /** 指定key的value是否为null，作用同isNotNull() */
    public boolean has(String key) {
        return !isNull(key);
    }

    /** 指定value在此SoMap的判断标准中是否为null */
    public boolean valueIsNull(Object value) {
        return NULL_ELEMENT_LIST.contains(value);
    }



    /** 指定key是否为null，判定标准为 NULL_ELEMENT_ARRAY 中的元素  */
    public boolean isNull(String key) {
        return valueIsNull(get(key));
    }

    /** 验证指定key不为空，为空则抛出异常 */
    public com.ikang.idata.search.search.util.SoMap checkNull(String ...keys) {
        for (String key : keys) {
            if(this.isNull(key)) {
                throw new RuntimeException("参数" + key + "不能为空");
            }
        }
        return this;
    }

    static Pattern patternNumber = Pattern.compile("[0-9]*");
    /** 指定key是否为数字 */
    public boolean isNumber(String key) {
        String value = getString(key);
        if(value == null) {
            return false;
        }
        return patternNumber.matcher(value).matches();
    }




    /**
     * 转为JSON字符串
     */
    public String toJsonString() {
        try {
//			SoMap so = SoMap.getSoMap(this);
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转为JSON字符串, 带格式的
     */
    public String toJsonFormatString() {
        try {
            return JSON.toJSONString(this, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ============================= web辅助 =============================


    /**
     * 返回当前request请求的的所有参数
     * @return
     */
    public static com.ikang.idata.search.search.common.SoMap getRequestSoMap() {
        // 大善人SpringMVC提供的封装
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null) {
            throw new RuntimeException("当前线程非JavaWeb环境");
        }
        // 当前request
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request.getAttribute("currentSoMap") == null || request.getAttribute("currentSoMap") instanceof com.ikang.idata.search.search.common.SoMap == false ) {
            initRequestSoMap(request);
        }
        return (com.ikang.idata.search.search.common.SoMap)request.getAttribute("currentSoMap");
    }

    /** 初始化当前request的 SoMap */
    private static void initRequestSoMap(HttpServletRequest request) {
        com.ikang.idata.search.search.common.SoMap soMap = new com.ikang.idata.search.search.common.SoMap();
        Map<String, String[]> parameterMap = request.getParameterMap();	// 获取所有参数
        for (String key : parameterMap.keySet()) {
            try {
                String[] values = parameterMap.get(key); // 获得values
                if(values.length == 1) {
                    soMap.set(key, values[0]);
                } else {
                    List<String> list = new ArrayList<String>();
                    for (String v : values) {
                        list.add(v);
                    }
                    soMap.set(key, list);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("currentSoMap", soMap);
    }

    /**
     * 验证返回当前线程是否为JavaWeb环境
     * @return
     */
    public static boolean isJavaWeb() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();// 大善人SpringMVC提供的封装
        if(servletRequestAttributes == null) {
            return false;
        }
        return true;
    }



    // ============================= 常见key （以下key经常用，所以封装以下，方便写代码） =============================

    /** get 当前页  */
    public int getKeyPageNo() {
        int pageNo = getInt("pageNo", 1);
        if(pageNo <= 0) {
            pageNo = 1;
        }
        return pageNo;
    }
    /** get 页大小  */
    public int getKeyPageSize() {
        int pageSize = getInt("pageSize", 10);
        if(pageSize <= 0 || pageSize > 1000) {
            pageSize = 10;
        }
        return pageSize;
    }

    /** get 排序方式 */
    public int getKeySortType() {
        return getInt("sortType");
    }





    // ============================= 分页相关(封装mybatis的page-help插件 ) =============================
    /*
     *//** 分页插件 *//*
	private com.github.pagehelper.Page<?> pagePlug;
	*//** 分页插件 - 开始分页 *//*
	public SoMap startPage() {
		this.pagePlug= com.github.pagehelper.PageHelper.startPage(getKeyPageNo(), getKeyPageSize());
		return this;
	}
	*//** 获取上次分页的记录总数 *//*
	public long getDataCount() {
		if(pagePlug == null) {
			return -1;
		}
		return pagePlug.getTotal();
	}
	*//** 分页插件 - 结束分页, 返回总条数 （该方法已过时，请调用更加符合语义化的getDataCount() ） *//*
	@Deprecated
	public long endPage() {
		return getDataCount();
	}*/





    // ============================= 工具方法 =============================


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
        List<com.ikang.idata.search.search.common.SoMap> newTreeList = new ArrayList<com.ikang.idata.search.search.common.SoMap>();
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
