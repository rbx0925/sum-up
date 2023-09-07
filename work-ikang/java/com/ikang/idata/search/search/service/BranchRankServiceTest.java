package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.BranchRankVo;
import com.ikang.idata.common.entity.vo.HospitalRankFilmVo;
import com.ikang.idata.common.entity.vo.RankingChartOfBranches;
import com.ikang.idata.common.entity.vo.SMSSatisfactionVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchRankServiceTest {

    @Mock
    private HospitalRankService mockHospitalRankService;
    @Mock
    private SatisfiedHospitalService mockSatisfiedHospitalService;

    @InjectMocks
    private BranchRankService branchRankServiceUnderTest;

    @Test
    void testGetElectronicReportRank() {
        // Setup
        final BranchRankVo rankVo = new BranchRankVo();
        rankVo.setAreaId("areaId");
        rankVo.setBranchId("branchId");
        rankVo.setYears("fiscalYear");

        final Map<String, RankingChartOfBranches.ReportRank> expectedResult = new HashMap<>();

        // Configure HospitalRankService.find(...).
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
        when(mockHospitalRankService.find(any(HospitalRankFilmVo.class))).thenReturn(baseSearch);

        // Run the test
        final Map<String, RankingChartOfBranches.ReportRank> result = branchRankServiceUnderTest.getElectronicReportRank(
                rankVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSMSSatisfactionRank() {
        // Setup
        final BranchRankVo rankVo = new BranchRankVo();
        rankVo.setAreaId("areaId");
        rankVo.setBranchId("branchId");
        rankVo.setYears("fiscalYear");

        final Map<String, RankingChartOfBranches.ReportRank> expectedResult = new HashMap<>();

        // Configure SatisfiedHospitalService.querySMSSatisfactionList(...).
        final SMSSatisfactionVo smsSatisfactionVo = new SMSSatisfactionVo();
        smsSatisfactionVo.setRegmonth("regmonth");
        smsSatisfactionVo.setRegcnyear("regcnyear");
        smsSatisfactionVo.setLocid("locid");
        smsSatisfactionVo.setLocname("locname");
        smsSatisfactionVo.setAreaid("areaid");
        smsSatisfactionVo.setAreaname("areaname");
        smsSatisfactionVo.setTcnt("nscnt");
        smsSatisfactionVo.setVscnt("nscnt");
        smsSatisfactionVo.setScnt("nscnt");
        smsSatisfactionVo.setNscnt("nscnt");
        smsSatisfactionVo.setNsrate("nsrate");
        smsSatisfactionVo.setType("type");
        smsSatisfactionVo.setRn("nscnt");
        final List<SMSSatisfactionVo> smsSatisfactionVos = Arrays.asList(smsSatisfactionVo);
        when(mockSatisfiedHospitalService.querySMSSatisfactionList(new BranchRankVo())).thenReturn(smsSatisfactionVos);

        // Run the test
        final Map<String, RankingChartOfBranches.ReportRank> result = branchRankServiceUnderTest.getSMSSatisfactionRank(
                rankVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSMSSatisfactionRank_SatisfiedHospitalServiceReturnsNoItems() {
        // Setup
        final BranchRankVo rankVo = new BranchRankVo();
        rankVo.setAreaId("areaId");
        rankVo.setBranchId("branchId");
        rankVo.setYears("fiscalYear");

        final Map<String, RankingChartOfBranches.ReportRank> expectedResult = new HashMap<>();
        when(mockSatisfiedHospitalService.querySMSSatisfactionList(new BranchRankVo()))
                .thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, RankingChartOfBranches.ReportRank> result = branchRankServiceUnderTest.getSMSSatisfactionRank(
                rankVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
