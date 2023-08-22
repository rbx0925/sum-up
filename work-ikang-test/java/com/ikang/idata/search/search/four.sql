select
    t2.brm_zone,
    t1.projectcode,
    t1.projectname,
    t1.customercode,
    t1.customername,
    t1.nominalcode,
    t1.nominalname,
    t1.actualcode,
    t1.actualname,
    t2.staff_name,
    t2.ygskqytjlx,
    case when t2.xmje='0' then 1 else cast(t2.yfkje as double)/cast(t2.xmje as double) end as yfkbl,
    t2.yfkje,
    t2.yfkdzsj,
    t2.xmksrq,
    t2.xmjsrq,
    t1.invoicemoney,
    t1.applyAmount,
    t1.noremoney,
    t1.checkmoney,
    t1.noinvoicemoney
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
    ) t1
        left join dw_dm_salesdept.contract_advancepay_detail t2 on t1.projectcode=t2.name