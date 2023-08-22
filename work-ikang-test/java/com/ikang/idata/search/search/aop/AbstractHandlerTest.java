package com.ikang.idata.search.search.aop;

import com.ikang.idata.search.search.entity.vo.IndustryComparisonVo;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.reflect.FieldUtils;

class AbstractHandlerTest {

//    private AbstractHandler abstractHandlerUnderTest;
//
//    @BeforeEach
//    void setUp() {
//        abstractHandlerUnderTest = new AbstractHandler() {
//            @Override
//            public void handleRequest(UserChannelAndHospital allAuthData, Object arg, Field field, DataAuthType annotation, Result<UserThird> userThird) {
//
//            }
//        };
//    }
public static void main(String[] args) {
    FieldUtils.getFieldsListWithAnnotation(IndustryComparisonVo.class, ApiModelProperty.class).stream()
            .forEach(field -> System.out.println(field.getType().getName()));
}
}
