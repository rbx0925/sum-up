select
    t3.brm_zone,      --所属大区
    t1.project_code,  --项目编码
    t1.project_name,  --项目名称
    t1.def2,          --客商名称
    t2.code,          --主签编码
    t1.def3,          --主签名称
    t3.xmje,          --签单金额
    t1.def9,          --销售人
    t4.discount,      --折扣（综合落单折扣）
    t3.xmksrq,        --开始日期
    t3.xmjsrq,        --结束时间
    q.status,         --报价单状态（一个项目对应一个报价单）
    t3.xmlx           --项目类型（是否为渠道项目，银行、保险、公关、渠道）
from dw_erp_ods.bd_project t1
         left join dw_erp_ods.org_orgs t2 on t1.def3=t2.pk_org
         left join dw_dm_salesdept.contract_advancepay_detail t3 on t1.project_code=t3.name
         left join kudu_pdw.f_project t4 on t1.project_code=t4.project_no
         left join kudu_pdw.mis_organ_project op on t1.project_code=op.projectid
         left join
     kudu_pdw.mis_project_quotation q on cast(q.presaleid as string)=op.p_id