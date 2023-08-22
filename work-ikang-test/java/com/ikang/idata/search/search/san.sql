查询sql:select
            t1.projectno2,
            t1.customercode,   --客商编码
            t1.mainarea2name,  --主签区域
            t3.xmksrq,         --项目开始日期
            t3.xmjsrq,         --项目结束日期
            t1.salername,      --销售员
            t1.self_check_num,--自有到检人数
            t1.self_check_money,  --自有到检金额
            t1.enterpise_total,  --应收企业合计
            t1.persional_total,   --应收个人合计
            t1.productpayable_total,   --套餐应收合计
            t1.addpayable_total,  --加项应收合计
            t1.productsaleprice_total,--套餐市场价合计
            t1.productpersional_total,--套餐应收个人
            t1.productenterprise_total,--套餐应收企业
            t1.addsaleprice_total,--加项市场价合计
            t1.addpersional_total,--加项应收个人
            t1.addenterpise_total, --加项应收企业
            t2.third_check_num,   --第三方到检人数
            t2.third_check_money,  --第三方到检金额
            (nvl(t1.self_check_num,0)+nvl(t2.third_check_num,0)) as check_num_total,   --到检人数合计（含第三方）
            (nvl(t1.self_check_money,0)+nvl(t2.third_check_money,0)) as check_money_total  --到检金额合计（含第三方）
        from
            (select
                 projectno2,customercode,mainarea2name,salername,
                 count(workno) as self_check_num,   --自有到检人数
                 sum(payabletotal) as self_check_money,  --自有到检金额
                 sum(productenterprise+addenterpise) as enterpise_total,  --应收企业合计
                 sum(productpersional+addpersional) as persional_total,   --应收个人合计
                 sum(productpayable) as productpayable_total,   --套餐应收合计
                 sum(addpayable) as addpayable_total,  --加项应收合计
                 sum(productsaleprice) as productsaleprice_total,--套餐市场价合计
                 sum(productpersional) as productpersional_total,--套餐应收个人
                 sum(productenterprise) as productenterprise_total,--套餐应收企业
                 sum(addsaleprice) as addsaleprice_total,--加项市场价合计
                 sum(addpersional) as addpersional_total,--加项应收个人
                 sum(addenterpise) as addenterpise_total --加项应收企业
             from
                 dw_erp_ods.mis_checkdetail
             group by projectno2,customercode,mainarea2name,salername
            ) t1
                left join
            (select
                 project_id,
                 count(card_id) as third_check_num,    	--第三方到检人数
                 sum(iksettle_price) as third_check_money    --第三方到检金额
             from
                 dw_erp_ods.mis_ik_thirdparty_report
             group by project_id) t2 on t1.projectno2=t2.project_id
                left join dw_dm_salesdept.contract_advancepay_detail t3 on t1.projectno2=t3.name


