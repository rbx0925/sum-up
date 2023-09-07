package com.ikang.idata.search.search.support;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.ikang.idata.common.entity.Dname;
import com.ikang.idata.common.entity.ECommerceBillboardDetails;
import com.ikang.idata.common.entity.ResponseImpalaDTO;
import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.entity.vo.DataDetailsQo;
import com.ikang.idata.common.entity.vo.DepartmentRankResultDTO;
import com.ikang.idata.common.entity.vo.DepartmentRankSearchVO;
import com.ikang.idata.common.enums.DepartmentRankFilmEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.DoubleUtils;
import com.ikang.idata.common.utils.IdataSecurityUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserConfigFeignServiceImpl;
import com.ikang.idata.search.search.javaTest.CmsArticleDTO;
import com.ikang.idata.search.search.javaTest.CmsCategoryDTO;
import com.ikang.idata.search.search.javaTest.CommonPage;
import com.ikang.idata.search.search.service.ESHttpClientService;
import com.ikang.idata.search.search.service.ImpalaCheckService;
import com.ikang.idata.search.search.service.MallDashBoardService;
import com.ikang.idata.search.search.service.SatisfiedHospitalService;
import com.ikang.idata.search.search.util.ImpalaParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ikang.idata.common.consts.MagicConst.COMMA_SPLIT;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/12/21
 */

@Slf4j
@Service
public class MonitorTest {
    private final SatisfiedHospitalService satisfiedHospitalService;

    private final AuthorityFeignServiceImpl authorityFeignService;
    private final ESHttpClientService esHttpClientService;
    private final SystemUserConfigFeignServiceImpl systemUserConfigFeignService;
    private final ImpalaCheckService impalaCheckService;

    public MonitorTest(AuthorityFeignServiceImpl authorityFeignService,
                       ESHttpClientService esHttpClientService,
                       SystemUserConfigFeignServiceImpl systemUserConfigFeignService,
                       ImpalaCheckService impalaCheckService,
                       SatisfiedHospitalService satisfiedHospitalService) {
        this.authorityFeignService = authorityFeignService;
        this.esHttpClientService = esHttpClientService;
        this.systemUserConfigFeignService = systemUserConfigFeignService;
        this.impalaCheckService = impalaCheckService;
        this.satisfiedHospitalService = satisfiedHospitalService;
    }


    /**
     * 科室看板实时接口查询
     */
    @Value("${kanban.departmentrank.technicalofficeaggImpalaUrl}")
    private String technicalofficeaggImpalaUrl;



    public List<DepartmentRankResultDTO> find(DepartmentRankSearchVO vo) throws Exception{
        //科室看板的实时数据   时间对比 当天， 实时
        //if (DateUtil.isSameDay(DateUtil.parseDate(vo.getDate()), new Date())) {
        //impalaCheckService.checkInterval();
        return findRealTime(vo);
        // }
        // 准实时
        // return findNearRealTime(vo);
    }


    public List<DepartmentRankResultDTO> findRealTime(DepartmentRankSearchVO vo) {
        Map<String, Object> departmentRankImpala = ImpalaParamUtil.convertImpalaParam(vo);
        // 科室code
        UserConfig userConfig = systemUserConfigFeignService.queryUserConfig(IdataSecurityUtil.getUserId());
        String departmentIds = userConfig.getDepartmentId();
        // 没有关注的科室 直接返回
        if (StringUtil.isEmpty(departmentIds)) {
            throw new BusinessException("该分院没有科室数据,请检查!");
        }
        departmentRankImpala.put(DepartmentRankFilmEnum.DEPARTMENT_CODE.getFields(), departmentIds);
        ResponseImpalaDTO impalaDTO = esHttpClientService.exchange(technicalofficeaggImpalaUrl,
                HttpMethod.POST,
                ResponseImpalaDTO.class,
                departmentRankImpala
        );
        List<DepartmentRankResultDTO> returnList = new ArrayList<>();
        List<DepartmentRankResultDTO> departmentRankImpalaList = Convert.convert(new TypeReference<List<DepartmentRankResultDTO>>() {}, impalaDTO.getData());
        //拿到对应的科室 和 可是对应的数据
        Map<String, List<DepartmentRankResultDTO>> codeToDepartment = departmentRankImpalaList.stream()
                .collect(Collectors.groupingBy(DepartmentRankResultDTO::getDepartmentCode));
        //循环遍历 科室code  如果有重复的科室 就累加  得到对应的数据值
        List<DepartmentRankResultDTO> departmentImpalaList = new ArrayList<>();
        for (String s : codeToDepartment.keySet()) {
            DepartmentRankResultDTO departmentDTO = new DepartmentRankResultDTO();
            for (int i = 0; i <departmentRankImpalaList.size() ; i++) {
                DepartmentRankResultDTO departmentRankResultDTO = departmentRankImpalaList.get(i);
                if (s.equals(departmentRankResultDTO.getDepartmentCode())) {
                    departmentDTO.setDepartmentName(departmentRankResultDTO.getDepartmentName());
                    departmentDTO.setDepartmentCode(departmentRankResultDTO.getDepartmentCode());
                    BigDecimal regNum = departmentDTO.getRegNum();
                    departmentDTO.setRegNum(BigInteger.ZERO.equals(regNum)?departmentRankResultDTO.getRegNum():regNum.add(departmentRankResultDTO.getRegNum()));
                    BigDecimal yuYueNum = departmentDTO.getYuyueNum();
                    departmentDTO.setYuyueNum(BigInteger.ZERO.equals(yuYueNum)?departmentRankResultDTO.getYuyueNum():yuYueNum.add(departmentRankResultDTO.getYuyueNum()));
                    BigDecimal waitNum = departmentDTO.getWaitNum();
                    departmentDTO.setWaitNum(BigInteger.ZERO.equals(waitNum)?departmentRankResultDTO.getWaitNum():waitNum.add(departmentRankResultDTO.getWaitNum()));
                    BigDecimal totalNum = departmentDTO.getTotalNum();
                    departmentDTO.setTotalNum(BigInteger.ZERO.equals(totalNum)?departmentRankResultDTO.getTotalNum():totalNum.add(departmentRankResultDTO.getTotalNum()));
                    BigDecimal finishNum = departmentDTO.getFinishNum();
                    departmentDTO.setFinishNum(BigInteger.ZERO.equals(finishNum)?departmentRankResultDTO.getFinishNum():finishNum.add(departmentRankResultDTO.getFinishNum()));
                    BigDecimal girlTotalNum = departmentDTO.getGirlTotalNum();
                    departmentDTO.setGirlTotalNum(BigInteger.ZERO.equals(girlTotalNum)?departmentRankResultDTO.getGirlTotalNum():girlTotalNum.add(departmentRankResultDTO.getGirlTotalNum()));
                    BigDecimal yuyueGirlNum = departmentDTO.getYuyueGirlNum();
                    departmentDTO.setYuyueGirlNum(BigInteger.ZERO.equals(yuyueGirlNum)?departmentRankResultDTO.getYuyueGirlNum():yuyueGirlNum.add(departmentRankResultDTO.getYuyueGirlNum()));
                    BigDecimal yuyueItemGirlNum = departmentDTO.getYuyueItemGirlNum();
                    departmentDTO.setYuyueItemGirlNum(BigInteger.ZERO.equals(yuyueItemGirlNum)?departmentRankResultDTO.getYuyueItemGirlNum():yuyueItemGirlNum.add(departmentRankResultDTO.getYuyueItemGirlNum()));
                    BigDecimal yuyueItemNum = departmentDTO.getYuyueItemNum();
                    departmentDTO.setYuyueItemNum(BigInteger.ZERO.equals(yuyueItemNum)?departmentRankResultDTO.getYuyueItemNum():yuyueItemNum.add(departmentRankResultDTO.getYuyueItemNum()));
                    BigDecimal regGirlNum = departmentDTO.getRegGirlNum();
                    departmentDTO.setRegGirlNum(BigInteger.ZERO.equals(regGirlNum)?departmentRankResultDTO.getRegGirlNum():regGirlNum.add(departmentRankResultDTO.getRegGirlNum()));
                    BigDecimal abandonNum = departmentDTO.getAbandonNum();
                    departmentDTO.setAbandonNum(BigInteger.ZERO.equals(abandonNum)?departmentRankResultDTO.getAbandonNum():abandonNum.add(departmentRankResultDTO.getAbandonNum()));
                }
            }
            departmentImpalaList.add(departmentDTO);
        }
        //最后在放在需要计算出结果的值
        for (int i = 0; i <departmentImpalaList.size() ; i++) {
            DepartmentRankResultDTO departmentRankResultDTO = departmentImpalaList.get(i);
            //regNum
            BigDecimal regNum = departmentRankResultDTO.getRegNum();
            //yuYueNum
            BigDecimal yuyueNum = departmentRankResultDTO.getYuyueNum();
            //waitNum
            BigDecimal waitNum = departmentRankResultDTO.getWaitNum();
            //totalNum
            BigDecimal totalNum = departmentRankResultDTO.getTotalNum();
            //finishNum
            BigDecimal finishNum = departmentRankResultDTO.getFinishNum();
            //abandonNum
            BigDecimal abandonNum = departmentRankResultDTO.getAbandonNum();
            //检项数量-待检查项目数量-已完成项目数量-放弃/延期项目数量 = 检查中 4.18号新加
            //待检查项目数量+已完成项目数量 = waitAndFinishAdd
            BigDecimal waitAndFinishAdd = DoubleUtils.add(String.valueOf(waitNum), String.valueOf(finishNum));
            //waitAndFinishAdd+放弃/延期项目数量 = AbandonAndWaitAdd
            BigDecimal abandonAndWaitAdd = DoubleUtils.add(String.valueOf(waitAndFinishAdd), String.valueOf(abandonNum));
            //检项数量-AbandonAndWaitAdd
            BigDecimal examinationBigDecimal = DoubleUtils.subtract4(String.valueOf(totalNum), String.valueOf(abandonAndWaitAdd));
            departmentRankResultDTO.setExamination(examinationBigDecimal);
            //检查中占比   检查中/总需完成项目
            BigDecimal examinationRate = DoubleUtils.divide4(String.valueOf(examinationBigDecimal), String.valueOf(totalNum));
            departmentRankResultDTO.setExaminationRate(examinationRate);


            // 预约到检率 已登记人数/预约人数
            BigDecimal arrivalRateBigDecimal = DoubleUtils.divide4(String.valueOf(yuyueNum), String.valueOf(regNum));
            departmentRankResultDTO.setArrivalRate(arrivalRateBigDecimal);
            // 待检查项目/总需完成项目
            BigDecimal waitNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(waitNum), String.valueOf(totalNum));
            departmentRankResultDTO.setWaitNumRate(waitNumRateBigDecimal);
            // 已完成项目/总需完成项目
            BigDecimal finishNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(finishNum), String.valueOf(totalNum));
            departmentRankResultDTO.setFinishNumRate(finishNumRateBigDecimal);
            // 放弃\延期项目/总需完成项目
            BigDecimal abandonNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(abandonNum), String.valueOf(totalNum));
            departmentRankResultDTO.setAbandonNumRate(abandonNumRateBigDecimal);
        }
        Map<String, String> idAndName = satisfiedHospitalService.showDname()
                .stream()
                .collect(Collectors.toMap(Dname::getDcode, Dname::getDname));
        List<String> departmentCodes = departmentImpalaList.stream()
                .map(DepartmentRankResultDTO::getDepartmentCode).filter(StringUtil::isNotEmpty)
                .collect(Collectors.toList());
        departmentImpalaList.addAll(Stream.of(userConfig.getDepartmentId()
                .split(COMMA_SPLIT))
                .filter(StringUtil::isNotEmpty)
                .filter(s -> !departmentCodes.contains(s))
                .map(s -> new DepartmentRankResultDTO(s, idAndName.get(s)))
                .collect(Collectors.toList()));

        //页面科室位置的固定
        List<String> collect = Stream.of(userConfig.getDepartmentId()
                .split(COMMA_SPLIT))
                .collect(Collectors.toList());
        for (String s : collect) {
            for (DepartmentRankResultDTO depart: departmentImpalaList) {
                if (s.equals(depart.getDepartmentCode())) {
                    returnList.add(depart);
                }
            }
        }
        return returnList;
    }
    @Test
    void testGetStringResult() {
       // assertThat(mallDashBoardServiceUnderTest.getStringResult(new BigDecimal("0.00"),
               // new BigDecimal("0.00"))).isEqualTo("result");
    }

    @Test
    void testGetDetails() throws Exception {
        // Setup
        final DataDetailsQo qo = new DataDetailsQo();
        qo.setChannel("channel");
        qo.setStartTime("startTime");
        qo.setEndTime("endTime");
        qo.setType("type");

        final ECommerceBillboardDetails expectedResult = new ECommerceBillboardDetails();
        expectedResult.setIndexName("件单价");
        expectedResult.setIndexUnit("个");
        final ECommerceBillboardDetails.Detail detail = new ECommerceBillboardDetails.Detail();
        detail.setTime("time");
        detail.setValue("value");
        detail.setValueStr("valueStr");
        detail.setUnit("个");
        detail.setYearOnYear("yearOnYear");
        detail.setYearOnYearType("yearOnYearType");
        detail.setChainRatio("chainRatio");
        detail.setChainRatioType("chainRatioType");
        expectedResult.setDetailList(Arrays.asList(detail));

        // Run the test
      //  final ECommerceBillboardDetails result = mallDashBoardServiceUnderTest.getDetails(qo);

        // Verify the results
        //assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDetails_ThrowsParseException() {
        // Setup
        final DataDetailsQo qo = new DataDetailsQo();
        qo.setChannel("channel");
        qo.setStartTime("startTime");
        qo.setEndTime("endTime");
        qo.setType("type");

        // Run the test
       // assertThatThrownBy(() -> mallDashBoardServiceUnderTest.getDetails(qo)).isInstanceOf(ParseException.class);
    }

    @Test
    void testSynchronousCalculation() {
      //  assertThat(mallDashBoardServiceUnderTest.synchronousCalculation("v1", "v2")).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testGetNumberWanTwo() {
        assertThat(MallDashBoardService.getNumberWanTwo("v2", "unit")).isEqualTo(new String[]{"result"});
        assertThat(MallDashBoardService.getNumberWanTwo("v2", "unit")).isEqualTo(new String[]{});
    }

    @Test
    void testGetNumberDeal() {
        assertThat(MallDashBoardService.getNumberDeal("v2", "unit")).isEqualTo(new String[]{"result"});
        assertThat(MallDashBoardService.getNumberDeal("v2", "unit")).isEqualTo(new String[]{});
    }

    @Test
    void testGetComma() {
        assertThat(MallDashBoardService.getComma("value")).isEqualTo("result");
    }

    @Test
    void testIsZero() {
        assertThat(MallDashBoardService.isZero("num")).isFalse();
    }

    private CmsArticleDTO cmsArticleDTOUnderTest;

    @BeforeEach
    void setUp() {
        cmsArticleDTOUnderTest = new CmsArticleDTO();
    }

    private CmsCategoryDTO cmsCategoryDTOUnderTest;

    @BeforeEach
    void setDown() {
        cmsCategoryDTOUnderTest = new CmsCategoryDTO();
    }

    private CommonPage<String> commonPageUnderTest;



    @Test
    void testRestPage1() {
        // Run the test
        final CommonPage<String> result = CommonPage.restPage(Arrays.asList("value"));
        assertThat(result.getPageNum()).isEqualTo(0);
        assertThat(result.getPageSize()).isEqualTo(0);
        assertThat(result.getTotalPage()).isEqualTo(0);
        assertThat(result.getList()).isEqualTo(Arrays.asList("value"));
        assertThat(result.getTotal()).isEqualTo(0L);
    }

    @Test
    void testRestPage2() {
        // Setup
        final Page<String> pageInfo = new PageImpl<>(Arrays.asList("value"));

        // Run the test
        final CommonPage<String> result = CommonPage.restPage(pageInfo);
        assertThat(result.getPageNum()).isEqualTo(0);
        assertThat(result.getPageSize()).isEqualTo(0);
        assertThat(result.getTotalPage()).isEqualTo(0);
        assertThat(result.getList()).isEqualTo(Arrays.asList("value"));
        assertThat(result.getTotal()).isEqualTo(0L);
    }
}