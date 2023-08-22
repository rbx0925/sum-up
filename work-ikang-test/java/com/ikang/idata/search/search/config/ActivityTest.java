package com.ikang.idata.search.search.config;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import jodd.util.function.Consumers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <p>
 * 活动主表
 * </p>
 *
 * @author jiangfeng
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityTest implements Serializable {

    private static final long serialVersionUID = -3459298323366247323L;

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "所属业务线id")
    private Long businessLineId;

    @ApiModelProperty(value = "活动状态0：草稿；1：待审核；2：审核通过待发送；3：发送中；4已发送")
    private Integer activityStatus;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动备注")
    private String activityRemark;

    @ApiModelProperty(value = "活动区域")
    private String activityArea;

    @ApiModelProperty(value = "活动品牌")
    private String activityBrand;

    @ApiModelProperty(value = "活动范围")
    private String activityScope;

    @ApiModelProperty(value = "目标用户群json数据")
    private String activityUseTags;

    @ApiModelProperty(value = "用户画像返回的汇总结果")
    private String activityUseInfo;

    @ApiModelProperty(value = "活动短连接")
    private String activityShortUrl;

    @ApiModelProperty(value = "发送方式 SMS;短信方式 APP_PUSH:爱康APP TJB_PUSH:体检宝APP")
    private String sendType;

    @ApiModelProperty(value = "发送时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "活动短信内容")
    private String activityMessageSms;

    @ApiModelProperty(value = "活动APP PUSH内容")
    private String activityMessagePush;

    @ApiModelProperty(value = "活动push打开方式：1：H5 ；2：原生")
    private String pushOpenType;

    @ApiModelProperty(value = "打开方式为1是为url；打开方式为2是为原生跳转码；如商品code")
    private String pushOpenUrl;

    @ApiModelProperty(value = "目标用户总数")
    private Long userTotalNum;

    @ApiModelProperty(value = "黑名单用户数")
    private Long  blackNum;

    @ApiModelProperty(value = "可接受短信用户总数")
    private Long canSmsNum;

    @ApiModelProperty(value = "可PUSH用户总数")
    private Long canPushNum;

    @ApiModelProperty(value = "可APP推送用户总数")
    private Long canPushAppNum;

    @ApiModelProperty(value = "可体检宝推送用数")
    private Long canPushTjbNum;

    @ApiModelProperty(value = "活动选取的目标用户总数")
    private Long actUserNum;

    @ApiModelProperty(value = "活动实际发送短信人数")
    private Long actSmsNum;

    @ApiModelProperty(value = "活动实际PUSH人数")
    private Long actPushNum;

    @ApiModelProperty(value = "触达的短信人数")
    private Long touchSmsNum;

    @ApiModelProperty(value = "触达的PUSH人数")
    private Long touchPushNum;

    @ApiModelProperty(value = "PUSH打开人数")
    private Long openPushNum;

    @ApiModelProperty(value = "活动push通知标题")
    private String pushTitle;

    @ApiModelProperty(value = "审核人")
    private Long auditor;

    @ApiModelProperty(value = "审核时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "活动审核邮箱")
    private String actAuditEmail;

    @ApiModelProperty(value = "活动步数")
    private Integer actStep;

    @ApiModelProperty(value = "查询大数据用的json格式的QueryDSL")
    private String queryDsl;

    @ApiModelProperty(value = "kafka数据批次号actId+_+待审核提交次数")
    private String batchId;

    @ApiModelProperty(value = "kafka回调数据操作结果 1:成功；0：失败")
    private String kafkaPutStatus;

    @ApiModelProperty(value = "kafka回调数据操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime kafkaPutTime;

    private Long createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "活动任务类型：FIXED_TIME：固定时间(默认)，FIXED_RATE_STANDARD：固定周期")
    private String jobType;

    @ApiModelProperty(value = "活动类型：0：活动（默认），1：模板")
    private Integer activityType;

    @ApiModelProperty(value = "模板名称：当活动为模板时的名称")
    private String templateName;

    @ApiModelProperty(value = "发送时间类型：BY_DAY：每日，BY_WEEK：每周，BY_MONTH：每月")
    private String sendTimeType;

    @ApiModelProperty(value = "发送时间区间：每日为：空，每周为：1-7，每月为：1-31，逗号分隔")
    private String sendTimeInterval;

    @ApiModelProperty(value = "发送时间：开始年月,格式 YYYY-MM")
    private String sendTimeBegin;

    @ApiModelProperty(value = "发送时间：结束年月,格式 YYYY-MM")
    private String sendTimeEnd;

    @ApiModelProperty(value = "用户人群ID")
    private Long userGroupId;

    @ApiModelProperty(value = "发送时间：具体时间点：格式 HH:mm")
    private String sendTimePoint;

    @ApiModelProperty(value = "拉取活动用户时间，根据活动规则拉取用户")
    private Date pullUserTime;

    @ApiModelProperty(value = "发送状态：0：用户拉取完毕（默认），1：待拉取用户，2：周期发送中，3：周期活动发送完成 ，4： 用户数据清理完毕，5：该批次待审核 区别活动状态")
    private Integer sendStatus;

    @ApiModelProperty(value = "删除状态：0：正常（默认），1：已删除，")
    private Integer delFlag;

    @ApiModelProperty(value = "活动选取的目标用户百分比，默认100")
    private Integer actUserNumRate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessLineId(Long businessLineId) {
        this.businessLineId = businessLineId;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public void setActivityBrand(String activityBrand) {
        this.activityBrand = activityBrand;
    }

    public void setActivityScope(String activityScope) {
        this.activityScope = activityScope;
    }

    public void setActivityUseTags(String activityUseTags) {
        this.activityUseTags = activityUseTags;
    }

    public void setActivityUseInfo(String activityUseInfo) {
        this.activityUseInfo = activityUseInfo;
    }

    public void setActivityShortUrl(String activityShortUrl) {
        this.activityShortUrl = activityShortUrl;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public void setActivityMessageSms(String activityMessageSms) {
        this.activityMessageSms = activityMessageSms;
    }

    public void setActivityMessagePush(String activityMessagePush) {
        this.activityMessagePush = activityMessagePush;
    }

    public void setPushOpenType(String pushOpenType) {
        this.pushOpenType = pushOpenType;
    }

    public void setPushOpenUrl(String pushOpenUrl) {
        this.pushOpenUrl = pushOpenUrl;
    }

    public void setUserTotalNum(Long userTotalNum) {
        this.userTotalNum = userTotalNum;
    }

    public void setBlackNum(Long blackNum) {
        this.blackNum = blackNum;
    }

    public void setCanSmsNum(Long canSmsNum) {
        this.canSmsNum = canSmsNum;
    }

    public void setCanPushNum(Long canPushNum) {
        this.canPushNum = canPushNum;
    }

    public void setCanPushAppNum(Long canPushAppNum) {
        this.canPushAppNum = canPushAppNum;
    }

    public void setCanPushTjbNum(Long canPushTjbNum) {
        this.canPushTjbNum = canPushTjbNum;
    }

    public void setActUserNum(Long actUserNum) {
        this.actUserNum = actUserNum;
    }

    public void setActSmsNum(Long actSmsNum) {
        this.actSmsNum = actSmsNum;
    }

    public void setActPushNum(Long actPushNum) {
        this.actPushNum = actPushNum;
    }

    public void setTouchSmsNum(Long touchSmsNum) {
        this.touchSmsNum = touchSmsNum;
    }

    public void setTouchPushNum(Long touchPushNum) {
        this.touchPushNum = touchPushNum;
    }

    public void setOpenPushNum(Long openPushNum) {
        this.openPushNum = openPushNum;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public void setAuditor(Long auditor) {
        this.auditor = auditor;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public void setActAuditEmail(String actAuditEmail) {
        this.actAuditEmail = actAuditEmail;
    }

    public void setActStep(Integer actStep) {
        this.actStep = actStep;
    }

    public void setQueryDsl(String queryDsl) {
        this.queryDsl = queryDsl;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setKafkaPutStatus(String kafkaPutStatus) {
        this.kafkaPutStatus = kafkaPutStatus;
    }

    public void setKafkaPutTime(LocalDateTime kafkaPutTime) {
        this.kafkaPutTime = kafkaPutTime;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }



    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @org.junit.jupiter.api.Test
    public void ofTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.of("加油");
        System.out.println(optional);

//        //传入null值  则会报 NullPointerException
//        Optional optional1= Optional.of(null);
//        System.out.println(optional1);

    }


    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @org.junit.jupiter.api.Test
    public void ofNullAbleTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println(optional1);
    }

    //对象方法 isPresent()
    //如果值存在则方法会返回 true，否则返回 false。
    @org.junit.jupiter.api.Test
    public void isPresentTest(){
        //传入正常值 正常返回一个Optional对象  并使用isPresent方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.isPresent());
        //结果  true

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.isPresent());
        //结果  false
    }


    //对象方法 get()
    //如果 Optional 有值则将其返回，否则抛出 NoSuchElementException 异常。
    @org.junit.jupiter.api.Test
    public void getTest(){
        //传入正常值 正常返回一个Optional对象  并使用get方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.get());
        //结果  传入正常值嘿呦

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.get());
        //结果  java.util.NoSuchElementException: No value present
    }

    //对象方法 ifPresent()
    // 如果值存在则使用该值调用 consumer , 否则不做任何事情。
    //该方法 ifPresent(Consumer<? super T> consumer) 中参数接收的是 Consumer 类，它包含一个接口方法 accept()，该方法能够对传入的值进行处理，但不会返回结果。这里传入参数可以传入 Lamdda 表达式或 Consumer 对象及实现 Consumer 接口的类的对象。
    @org.junit.jupiter.api.Test
    public void ifPresentTest(){
        //创建Optional 对象 然后调用Optional 对象的ifPresent 方法   传入Lambda 表达式
        Optional optional= Optional.ofNullable("向目标前进");
        optional.ifPresent((value) -> System.out.println("optional 的值为" + value));
        //结果  向目标前进

        //创建Optional 对象 调用Optional对象的ifPresent方法 传入实现Consumer匿名
        Optional optional1= Optional.ofNullable("加油呀");
        Consumer<String> consumer = new Consumers(){
            @Override
            public  void accept(Object value){
                System.out.println("Optional 的值为" + value);
            }
        };
        optional1.ifPresent(consumer);
        //结果  加油呀
    }

    //对象方法 orElse()
    //如果该值存在就直接返回， 否则返回指定的其它值。
    // orElse 方法实现很简单，就是使用三目表达式对传入的参数值进行 null 验证，即 value != null ? value : other; 如果为 null 则返回 true，否则返回 false。
    @org.junit.jupiter.api.Test
    public void orElseTest(){
        //传入正常的参数 获取一个Optional 对象  并使用orElse方法设置默认值
        Optional optional = Optional.ofNullable("三生三世");
        Object deFaultValue = optional.orElse("默认值");
        System.out.println("如果值不为空" + deFaultValue);
        //结果   三生三世


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object deFaultValue1 = optional1.orElse("默认值");
        System.out.println("如果值为空" + deFaultValue1);
        //结果  默认值

    }

//    https://blog.csdn.net/weixin_36380516/article/details/113361959?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164887835416780357237329%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164887835416780357237329&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-113361959.142^v5^pc_search_result_cache,157^v4^control&utm_term=Optional.ofNullable&spm=1018.2226.3001.4187

    //orElseGet()
    //orElseGet 方法和 orElse 方法类似，都是在 Optional 值为空时，返回一个默认操作，只不过 orElse 返回的是默认值，而 orElseGet 是执行 lambda 表达式，然后返回 lambda 表达式执行后的结果。
    @org.junit.jupiter.api.Test
    public  void orElseGetTest(){
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional optional = Optional.ofNullable("新的一周");
        Object object = optional.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return  defaultVal;
        });
        System.out.println("输出值为" + object);


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object object1 = optional1.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return defaultVal;
        });
        System.out.println("输出值为" + object1);

        /**
         * 打印结果
         * object  新的一周
         * object1 执行逻辑和生成默认值
         */
    }

    //orElseThrow()
    //orElseThrow 方法其实就是判断创建 Optional 时传入的参数是否为 null，如果是非 null 则返回传入的值，否则抛出 异常。
    @org.junit.jupiter.api.Test
    public void orElseThrowTest(){
        //传入正常参数 获取一个Optional对象   并使用orElseThrow方法
        Optional optional = Optional.ofNullable("加油加油");
        try {
            Object elseThrow = optional.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });
            System.out.println("输出值为 " + elseThrow);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        //传入null参数  获取一个Optional 对象  并使用orElseThrow 方法
        Optional optional1 = Optional.ofNullable(null);
        try {
            Object elseThrow1 = optional1.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });

            System.out.println("输出值为 " + elseThrow1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        /**
         * 打印结果
         * elseThrow  加油加油
         * elseThrow1  抛出异常并报错
         */
    }


    // 创建一个用户类，使用 Optional 操作用户对象，获取其 name 参数，结合 Optional 的 map 方法获取值，进行观察：
    class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //使用 Optional 的 map 方法对值处理：
//        @org.junit.jupiter.api.Test
//        public void orElseTest1() {
//            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
//            Test.User user = new Test.User("测试名称");
//            Test.User user1 = new Test.User(null);
//
//            //使用Optional存储User对象
//            Optional<Test.User> optionalUser = Optional.ofNullable(user);
//            Optional<Test.User> optionalUser1 = Optional.ofNullable(user1);
//
//            //获取对象的name属性值
//            String name = optionalUser.map(Test.User::getName).orElse("未填写");
//            String name1 = optionalUser1.map(Test.User::getName).orElse("未填写");
//
//            //输出结果
//            System.out.println("获取的名称 " + name);
//            System.out.println("获取的名称 " + name1);
//
//            /**
//             * 打印结果
//             * name 测试名称
//             * name1 未填写
//             */
//            //通过上面两个示例观察到，通过 Optional 对象的 map 方法能够获取映射对象中的属，创建 Optional 对象，并以此属性充当 Optional 的值，结合 orElse 方法，如果获取的属性的值为空，则设置个默认值。
//        }

        //对象方法 flatMap()
        // flatMap 方法和 map 方法类似，唯一的不同点就是 map 方法会对返回的值进行 Optional 封装，而 flatMap 不会，它需要手动执行 Optional.of 或 Optional.ofNullable 方法对 Optional 值进行封装。
        @org.junit.jupiter.api.Test
        public void flatMapTest() {
            //创建一个map 对象
            Map<String, String> userMap = new HashMap<>();
            userMap.put("name", "jiayou");
            userMap.put("sex", "nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String, String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap(value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }
    }
}
