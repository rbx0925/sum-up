sql:
--序号5
select
    t2.brm_zone
    t1.pcon_code,
        t1.gcode,
    t1.pcode,
    t1.pname,
    t2.xmksrq,
    t2.xmjsrq,
    t1.cust_code,
    t1.cust_name,
    t1.con_code,
    t1.con_amount,
    t1.cust_class,
    t1.slaer,
    t1.c_pnum,
    t1.h_amount,
    t1.r_pnum,
    t1.pay_famount,
    t1.bak_amount,
    t1.prod_dayss,
    t1.prod_days,
    t3.orgname,
    t3.acbook_name,
    t4.invoicemoney,
    t4.applyAmount,
    t4.noremoney,
    t4.checkmoney,
    t4.noinvoicemoney
from
    (
        select
            o.code gcode, -- 账套编码
            t.project_name pname, -- 项目名称
            t.project_code pcode, -- 项目编码 关联科目余额表
            o.code pcon_code, -- 主签编码
            o.pk_org as pk_org,
            t.def17 as con_code, -- 合同编码
            c.code as cust_code, -- 客户编码
            c.name as cust_name, -- 客户名称
            cc.name cust_class, -- 客户分类（企业性质）
            t.def9 slaer, -- 销售人员姓名
            t.plan_start_date as start_date, -- 项目开始时间
            decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10) as pro_end_date, -- 项目结项时间（如果无值则取项目结束时间）
            mc.checkpsnnum r_pnum, -- 报告人数 (历史报告人数+统计报告人数）
            t.def4 c_pnum, -- 签约人数
            mc.checkmoney h_amount, -- 历史到检金额
            0 r_amount, -- 到检（报告）金额
            t.def5 con_amount, -- 签约金额
            t.def18 con_pay_amount, -- 合同预付总额
            mm.money pay_famount, -- 实际预付 (付款日期<结项日期的客户付款金额之和）
            0 bill_amount, -- 累计开票金额 (无数据源）
            mm.money bak_amount, -- 累计回款金额 (历史回款金额+统计回款金额）
            0 bill_n_amount, -- 开票未回款金额 （累计开票金额-累计回款金额，取正值，负值记0）
--case when (nvl(t.def10,'~') = '~' and nvl(t.plan_finish_date,'~') = '~') then '无账龄'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 0 then '未到期'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 30 then '0-30'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 60 then '31-60'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 90 then '61-90'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 180 then '91-180'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 360 then '180-360'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 720 then '360-720'
--else '2年以上' end as prod_days,
--case when (nvl(t.def10,'~') = '~' and nvl(t.plan_finish_date,'~') = '~') then '09'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 0 then '10'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 30 then '02'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 60 then '03'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 90 then '04'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 180 then '05'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 360 then '06'
--when TO_NUMBER(to_date(substr(parameter('fr003_param1'),0,10),'yyyy-mm-dd') - to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10),'yyyy-mm-dd')) < 720 then '07'
--else '08' end as prod_dayss
            case when (nvl(t.def10,'~') = '~' and nvl(t.plan_finish_date,'~') = '~') then '无账龄'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 0 then '未到期'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 30 then '0-30'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 60 then '31-60'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 90 then '61-90'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 180 then '91-180'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 360 then '180-360'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 720 then '360-720'
                 else '2年以上' end as prod_days,
            case when (nvl(t.def10,'~') = '~' and nvl(t.plan_finish_date,'~') = '~') then '09'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 0 then '10'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 30 then '02'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 60 then '03'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 90 then '04'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 180 then '05'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 360 then '06'
                 when datediff(to_date('2023-07-01') , to_date(substr(decode(nvl(t.def10,'~'),'~',t.plan_finish_date,t.def10),1,10))) < 720 then '07'
                 else '08' end as prod_dayss
        from dw_erp_ods.bd_project t
                 left join dw_erp_ods.org_orgs o on o.pk_org = t.def3 -- MRC主签 def15,0728改成def3
                 left join dw_erp_ods.bd_customer c on t.def2 = c.pk_customer
                 left join (select mct.projectno,sum(mct.checkpsnnum) as checkpsnnum,sum(mct.checkmoney) as checkmoney,sum(mct.productreceivablemoney) as productreceivablemoney from dw_erp_ods.BD_NEW_MIS_CHECK mct group by mct.projectno) mc on mc.projectno = t.project_code
                 left join dw_erp_ods.bd_old_project_money mm on mm.project = t.project_code
                 left join dw_erp_ods.bd_defdoc cc on cc.pk_defdoc = c.def2
    ) t1
        left join dw_dm_salesdept.contract_advancepay_detail t2 on t1.pcode=t2.name
        left join
    (select
         acbook.pk_group pk_group,
         acbook.pk_org pk_org,
         acbook.CODE acbook_code,
         acbook.NAME acbook_name,
         acbook.pk_relorg,
         org.NAME orgname,
         project.project_code project_code,
         project.project_name project_name,
         m.d_amount,
         m.c_amount,
         m.ye_amount
     from (
              SELECT
                  pk_accountingbook,
                  substr ( CASE

                               WHEN fv.typevalue1 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue1
                               WHEN fv.typevalue2 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue2
                               WHEN fv.typevalue3 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue3
                               WHEN fv.typevalue4 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue4
                               WHEN fv.typevalue5 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue5
                               WHEN fv.typevalue6 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue6
                               WHEN fv.typevalue7 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue7
                               WHEN fv.typevalue8 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue8
                               WHEN fv.typevalue9 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue9 ELSE NULL
                               END,
                           21,
                           20
                      ) pk_project,
                  sum( nvl ( detail.localdebitamount, 0 ) ) d_amount,
                  sum( nvl ( detail.localcreditamount, 0 ) ) c_amount,
                  sum( nvl ( detail.localdebitamount, 0 ) - nvl ( detail.localcreditamount, 0 ) ) ye_amount
              FROM
                  dw_erp_ods.gl_detail detail
                      INNER JOIN dw_erp_ods.gl_freevalue fv  ON detail.assid = fv.freevalueid
                      INNER JOIN dw_erp_ods.bd_accasoa accasoa ON accasoa.pk_accasoa = detail.pk_accasoa
                      INNER JOIN dw_erp_ods.bd_account ON bd_account.pk_account = accasoa.pk_account
              WHERE
                      detail.accountcode = '112201'
                AND substr(detail.prepareddatev, 1, 10) <='2023-06-30' --parameter('fr002_param1')
                AND detail.yearv = substr(from_unixtime(unix_timestamp( now() ),'yyyy-MM-dd'),1,4)    --substr (to_char(add_months(sysdate,-3),'yyyy' ), 1, 4 )
                AND detail.dr = 0
              GROUP BY
                  pk_accountingbook,
                  substr ( CASE

                               WHEN fv.typevalue1 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue1
                               WHEN fv.typevalue2 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue2
                               WHEN fv.typevalue3 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue3
                               WHEN fv.typevalue4 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue4
                               WHEN fv.typevalue5 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue5
                               WHEN fv.typevalue6 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue6
                               WHEN fv.typevalue7 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue7
                               WHEN fv.typevalue8 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue8
                               WHEN fv.typevalue9 LIKE '0001Z0100000000005D1%' THEN
                                   typevalue9 ELSE NULL
                               END,
                           21,
                           20
                      )
          ) m
              inner join dw_erp_ods.org_accountingbook acbook ON acbook.pk_accountingbook = m.pk_accountingbook
              INNER JOIN dw_erp_ods.org_orgs org ON org.pk_org = acbook.pk_org
              left outer join dw_erp_ods.bd_project project
                              on m.pk_project=project.pk_project
     where ye_amount <>0
    ) t3 on concat(t1.pk_org,'_',t1.pcode)=concat(t3.pk_relorg,'_',t3.project_code)
        left join
    (
        select
            projectcode,
            projectname,
            customercode,
            customername,
            nominalcode,
            nominalname,
            actualcode,
            actualname,
            invoicemoney,
            applyAmount,
            noremoney,
            checkmoney,
            noinvoicemoney
        from
            (select
                 projectcode,
                 projectname,
                 customercode,
                 customername,
                 nominalcode,
                 nominalname,
                 actualcode,
                 actualname,
                 invoicemoney,
                 applyAmount,
                 noremoney,
                 checkmoney,
                 noinvoicemoney,
                 ROW_NUMBER() over(partition by projectcode order by projectcode desc) as rn
             from
                 (SELECT
                      project.code AS projectcode,
                      project.name AS projectname,
                      customer.code AS customercode,
                      customer.name AS customername,
                      orgs1.code AS nominalcode,
                      orgs1.name AS nominalname,
                      orgs2.code AS actualcode,
                      orgs2.name AS actualname,
                      ifnull(invoiceinfo.invoicemoney, 0) AS invoicemoney,
                      ifnull(brmapply.applyAmount, 0) AS applyAmount,(
                              ifnull(invoiceinfo.invoicemoney, 0) - ifnull(brmapply.applyAmount, 0)
                          ) AS noremoney,
                      ifnull(checkdata.checkmoney, 0) AS checkmoney,(
                              ifnull(checkdata.checkmoney, 0) - ifnull(invoiceinfo.invoicemoney, 0)
                          ) AS noinvoicemoney
                  FROM
                      dw_erp_ods.v_ik_project project
                          LEFT JOIN dw_erp_ods.v_ik_org orgs1 ON project.define3 = orgs1.id
                          LEFT JOIN dw_erp_ods.v_ik_org orgs2 ON project.define15 = orgs2.id
                          LEFT JOIN (
                          SELECT
                              sum(invoicemoney) AS invoicemoney,
                              projectno AS projectno
                          FROM
                              dw_erp_ods.v_ik_brminvoiceinfo
                          WHERE
                                  approvestatus = '审批通过'
                            AND invoicestatus = '已开票'
                            and invoicedate <=  '2023-06-19'  -- 截至日期

                          GROUP BY
                              projectno
                      ) invoiceinfo ON project.code = invoiceinfo.projectno
                          LEFT JOIN (
                          select sum(applyAmount) AS applyAmount,
                                 projectNo AS projectNo,
                                 customerName AS customerName from dw_erp_ods.v_ik_brm_apply where ts <=  '2023-06-19'   -- 截至日期
                          GROUP BY projectNo,customerName
                      ) brmapply ON project.code = brmapply.projectNo
                          LEFT JOIN dw_erp_ods.v_ik_customer customer ON customer.name = brmapply.customerName
                          LEFT JOIN (
                          SELECT
                              sum(
                                      (
                                              ifnull(productenterprise, 0) + ifnull(addenterpise, 0)
                                          )
                                  ) AS checkmoney,
                              projectno AS projectno
                          FROM
                              dw_erp_ods.mis_checkdetail  --dw_erp_ods.v_mis_check
                          WHERE
                                  reportdate <=  '2023-06-19'  -- 截至日期
                          GROUP BY
                              projectno
                      ) checkdata ON project.id = checkdata.projectno

                  WHERE
                      (
                              ifnull(invoiceinfo.invoicemoney, 0) <> 0
                          )
                     OR (ifnull(brmapply.applyAmount, 0) <> 0)
                     OR (ifnull(checkdata.checkmoney, 0) <> 0)
                 ) a
             group by
                 projectcode,
                 projectname,
                 customercode,
                 customername,
                 nominalcode,
                 nominalname,
                 actualcode,
                 actualname,
                 invoicemoney,
                 applyAmount,
                 noremoney,
                 checkmoney,
                 noinvoicemoney
            ) b
        where rn =1
    ) t4 on t1.pcode=t4.projectcode