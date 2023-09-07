-- auto-generated definition
CREATE TABLE domains
(
    id               STRING COMMENT '主键 登记日期和检线ID',
    areaid           STRING COMMENT '到检区域id',
    areaname         STRING COMMENT '到检区域',
    locid            bigint COMMENT '到检分院id',
    locname          STRING COMMENT '到检分院',
    hospid           STRING COMMENT '到检检线ID',
    hospname         STRING COMMENT '到检检线名称',
    examday          STRING COMMENT '登记日期',
    totalcnt         bigint COMMENT '总登记人数',
    regcheckcnt      bigint COMMENT '预约登记人数',
    noregcheckcnt    bigint COMMENT '未预约登记人数',
    delayedarrivecnt bigint COMMENT '补检登记人数',
    groupcnt         bigint COMMENT '团检登记人数',
    nogroupcnt       bigint COMMENT '散客登记人数',
    highvaluecnt     bigint COMMENT '高净值客户登记人数',
    femalecnt        bigint COMMENT '女性登记人数',
    malecnt          bigint COMMENT '男性登记人数',
    ages1cnt         bigint COMMENT '30岁以下（含）登记人数',
    ages2cnt         bigint COMMENT '30~50岁（含50岁）登记人数',
    ages3cnt         bigint COMMENT '50岁以上登记人数',
    hospnewcnt       bigint COMMENT '门店新客户人数',
    hospoldcnt       bigint COMMENT '门店老客户人数',
    groupnewcnt      bigint COMMENT '爱康新客户人数',
    groupoldcnt      bigint COMMENT '爱康老客户人数',
    complaintcnt     bigint COMMENT '客诉人数'
);
CREATE TABLE test22
(

    reporttype int COMMENT '报告类型 1.延期报告 2.入职套餐 3.加急报告 4. 正常报告. 2,3,4 为非延期 ',
    isovertime int COMMENT '报告是否超时  1 是,0 否 a、入职报告超过24小时未出具，和超过24小时出具；b、加急报告超过3天未出具，和超过3天出具；c、正常报告超过7天未出具，和超过7天出具',
    overdays   double COMMENT '报告超时时长 单位 天  在满足超出条件下，报告出具时间-标准应出时间 '
)


CREATE TABLE test
(
    workno               STRING COMMENT '健检号 可做主键',
    areaid               STRING COMMENT '到检区域id',
    areaname             STRING COMMENT '到检区域',
    locid                STRING COMMENT '到检分院id',
    locname              STRING COMMENT '到检分院',
    hospid               STRING COMMENT '到检检线ID',
    hospname             STRING COMMENT '到检检线名称',
    cardid               STRING COMMENT '卡号',
    tjy_workno           STRING COMMENT '体检云健检号',
    examuser             STRING COMMENT '客户姓名',
    mobile               STRING COMMENT '客户手机号',
    gender               int COMMENT '客户性别 0 女 1 男',
    examage              STRING COMMENT '客户到检时年龄',
    examagegroup         STRING COMMENT '客户到检时年龄分组',
    birthday             STRING COMMENT '客户出生日期',
    idnumber             STRING COMMENT '客户身份证',
    projectid            STRING COMMENT '项目号',
    organid              STRING COMMENT '公司id',
    organname            STRING COMMENT '公司名称',
    sourcecode           STRING COMMENT '套餐code',
    sourcename           STRING COMMENT '套餐',
    sellerid             STRING COMMENT '销售id',
    seller               STRING COMMENT '销售姓名',
    sellermobile         STRING COMMENT '销售手机号',
    examday              STRING COMMENT '登记日期 yyyy-mm-dd',
    examdate             STRING COMMENT '登记时间 即到检时间',
    bookingid            STRING COMMENT '预约单id',
    bookingtime          STRING COMMENT '下预约单时间',
    regdate              STRING COMMENT '预约到检时间',
    isreg                int COMMENT '是否预约登记 1 是，0 否',
    isnoreg              int COMMENT '是否未预约登记 1 是，0 否',
    isdelayedarrive      int COMMENT '是否补检登记 1 是，0 否',
    delayedarrivedate    STRING COMMENT '补检登记时间',
    isgroup              int COMMENT '是否团检登记1 是，0 否（即散客登记）',
    ishospnew            int COMMENT '是否门店新客户 1 是，0 否。 用于判断是否门店新老客户',
    lastloctime          STRING COMMENT '最新一次到检门店日期',
    isgroupnew           int COMMENT '是否集团新客户 1 是，0 否。 用于判断是否集团新老客户',
    lastgrouptime        STRING COMMENT '最新一次到检爱康日期',
    realreceive          double COMMENT '到检实收金额，用于判断高净值客户',
    ishighvalue          int COMMENT '是否高净值客户 1 是 默认是到检实收金额>=4000，0 否',
    iscomplaint          int COMMENT '是否客诉 1 是，0 否',
    lastevaluate         STRING COMMENT '最新一次短信不满意评价（不满意）',
    lastevaluatecontens  STRING COMMENT '最新一次短信不满意评价原始内容',
    lastevaluatetime     STRING COMMENT '最新一次短信不满意评价时间',
    lastevaluatehospid   STRING COMMENT '最新一次短信不满意评价检线id',
    lastevaluatehospname STRING COMMENT '最新一次短信不满意评价检线',
    lastevaluatelocid    STRING COMMENT '最新一次短信不满意评价分院id',
    lastevaluatelocname  STRING COMMENT '最新一次短信不满意评价分院'
);


CREATE TABLE `ikang_es.reader`
(
    type                string COMMENT '0-阅片中心已阅,1-影像管理阅片已阅,2-阅片中心未阅,3-影像管理未阅,4-所有检查项目,5-影像管理审核,6-拍片,7-心电采集,8-心电审核',
    reader_locationname string COMMENT '阅片分院',
    reader_reader_area  string COMMENT '阅片区域',
    doctor_ncc          string COMMENT '医生ncc账号',
    doctor_name         string COMMENT '医生名称',
    doctor_id           string COMMENT '医生id',
    doctor_loginid      string COMMENT '医生id',
    doctor_account_type string COMMENT '账户类型:1.全职,2.兼职',
    exam_date           string COMMENT '顾客登记时间,yyyy-MM-dd',
    examine_date        string COMMENT '医生检查日期,yyyy-MM-dd',
    institute_id        string COMMENT '检线id',
    hospname_name       string COMMENT '检线名称',
    areaname_name       string COMMENT '区域名称',
    areaname_id         string COMMENT '医院所属管辖城市id',
    locid_id            bigint COMMENT '分院id',
    locationname_name   string COMMENT '分院名称',
    item_code           string COMMENT '项目编码',
    item_name           string COMMENT '项目名称',
    item_status         string COMMENT '项目状态',
    department_name     string COMMENT '科室名称',
    department_code     string COMMENT '科室code',
    department_id       string COMMENT '科室id',
    ct                  bigint COMMENT '数量'
)
    STORED BY
        'org.elasticsearch.hadoop.hive.EsStorageHandler'