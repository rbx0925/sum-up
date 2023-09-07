package com.ikang.idata.search.search;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/3/21
 */

@Data
public class Student implements Comparable<Student> {

    int no ;
    String name;
    String sex;
    float height;

    public Student(int no, String name, String sex, float height) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.height = height;
    }

    public Student() {
    }

    @Override
    public int compareTo(Student o) {
        // 例如按照no进行排序
        return this.no - o.no;
    }


    public int compareTo1(Student o) {
        // 拿学生学号和学生对象学号进行排序
        if(this.no < o.no){
            return -1;
        }else  if(this.no == o.no){
            return 0;
        }else {
            return 1;
        }
    }

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @paramjsonDatajson数据
     * @paramclazz对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @paramjsonData
     * @parambeanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @org.junit.Test
    public void test() throws JsonProcessingException {
        //jackson
        HashMap<String, String> map = MapUtil.of("xiaobin", "大佬");
        System.out.println(objectToJson(map));
        HashMap map1 = jsonToPojo(objectToJson(map), map.getClass());
        System.out.println(map1);
        Map<String, Object> map2 = MAPPER.readValue(objectToJson(map), new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map2);

        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(false);
        componentProvider.addIncludeFilter(new AnnotationTypeFilter(TestSpring.class));
        Set<BeanDefinition> components = componentProvider.findCandidateComponents("*");
        components.forEach(beanDefinition -> System.out.println(beanDefinition.getBeanClassName()));
    }


    @org.junit.Test
    public void test1() {
        String str = "[\n" +
                "{\n" +
                "\"locid_id\":853,\n" +
                "\"locationname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"institute_id\":\"071\",\n" +
                "\"hospname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"work_no\":\"2832203170001\",\n" +
                "\"card_id\":null,\n" +
                "\"customer_name\":null,\n" +
                "\"mobile\":null,\n" +
                "\"company_name\":null,\n" +
                "\"sex\":\"其他\",\n" +
                "\"age\":null,\n" +
                "\"department_name\":\"放射科\",\n" +
                "\"department_code\":\"3.1\",\n" +
                "\"item_name\":\"胸部正位\",\n" +
                "\"item_code\":\"3.1.1.41\",\n" +
                "\"item_status\":\"4\"\n" +
                "},\n" +
                "{\n" +
                "\"locid_id\":853,\n" +
                "\"locationname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"institute_id\":\"071\",\n" +
                "\"hospname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"work_no\":\"2832203170001\",\n" +
                "\"card_id\":null,\n" +
                "\"customer_name\":null,\n" +
                "\"mobile\":null,\n" +
                "\"company_name\":null,\n" +
                "\"sex\":\"其他\",\n" +
                "\"age\":null,\n" +
                "\"department_name\":\"放射科\",\n" +
                "\"department_code\":\"3.1\",\n" +
                "\"item_name\":\"胸部正位\",\n" +
                "\"item_code\":\"3.1.1.41\",\n" +
                "\"item_status\":\"4\"\n" +
                "}\n" +
                "\n" +
                "]";

        JSONArray objects = JSONArray.parseArray(str);
        System.out.println(objects.size());
    }

    @ToString
    public static class App implements Serializable {
        private static final long serialVersionUID = -6526705747295296106L;
        private String name;
        @TestSpring
        private String type;

        public App() {
        }
    }


    @org.junit.Test
    public void testzone() {
        TestJson.App app = new TestJson.App();
        DirectFieldAccessor accessor = new DirectFieldAccessor(app);
        Stream.of(accessor.getWrappedClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(TestSpring.class))
                .forEach(field -> accessor.setPropertyValue(field.getName(),TestSpring.class.getSimpleName()));
        System.out.println(app);

    }
    @org.junit.Test
    public void test2() {
        System.out.println(NumberUtil.parseNumber("40"));
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.toBigDecimal("40.22").setScale(2));

    }

}
