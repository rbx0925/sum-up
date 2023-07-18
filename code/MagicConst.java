package com.ikang.idata.common;

import com.ikang.idata.common.utils.ShiroMd5Util;

import java.util.regex.Pattern;

/**
 * MagicConst
 * 常量
 *
 * @author haigang.jia@ikang.com
 * @date 2019-05-22  下午 4:43
 */
public interface MagicConst {

    /**
     * 缓存命名空间
     */
    String CACHE_NAME_SPACE = "idata.common";

    /**
     * IpUtil
     */
    String IP_255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    /**
     * IpUtil
     */
    Pattern PATTERN = Pattern.compile("^(?:" + IP_255 + "\\.){3}" + IP_255 + "$");
    /**
     * IpUtil
     */
    String IP = "10.0.0.0";
    /**
     * IpUtil
     */
    String X_FORWARDED_FOR = "x-forwarded-for";
    /**
     * IpUtil
     */
    String IP1 = "10.255.255.255";
    /**
     * IpUtil
     */
    String IP2 = "172.16.0.0";
    /**
     * IpUtil
     */
    String IP3 = "172.31.255.255";
    /**
     * IpUtil
     */
    String IP4 = "192.168.0.0";
    /**
     * IpUtil
     */
    String IP5 = "192.168.255.255";
    /**
     * IpUtil
     */
    String LOCAL_IP = "127.0.0.1";
    /**
     * IpUtil
     */
    String UNKNOWN = "unknown";
    /**
     * IpUtil
     */
    String PROXY_CLIENT_IP = "Proxy-Client-IP";
    /**
     * IpUtil
     */
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    /**
     * IpUtil
     */
    String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    /**
     * IpUtil
     */
    String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    /**
     * 测试账号 userName=admin@test.com,id=4 令牌
     * 用于测试以及Swagger UI
     */
    String TEST_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0IiwiaXNzIjoiaWthbmciLCJleHAiOjI2NDUzNTM5NDgwMywidXNlck5hbWUiOiJhZG1pbkB0ZXN0LmNvbSIsImlhdCI6MTU2MDc1MTYwM30.EjipiTSmOuPPCmfaw8zWKZOe9HhBWTyifhv5OuUQEXs";

    /**
     * 用户密码校验规则,同时具有字母和数字且长度在6至20位
     */
    String PASWD_REGEXP = "^[A-Za-z0-9]{32}$";
    String PASWD_UN_PATTERN_MESSAGE = "密码必须是字母和数字组合，且长度在32位";
    String EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+(;)*(( )*(;)+( )*[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+(;)*)*$";
    Pattern PASSWORD_PATTERN = Pattern.compile(PASWD_REGEXP);
    Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
    String EMAIL_UN_PATTERN_MESSAGE = "请输入正确邮箱地址";

    /**
     * 手机号过滤
     */
    String MOBILE_REGEXP = "^(((13[0-9])|(14[0-9])|(15([0-3]|[5-9]))|(17[0-9])|(18[0-9])|(19[0-9])|(16[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})*$";
    /**
     * 手机号过滤(手机号必填)
     */
    String MOBILE_REGEXP_MUST = "^(((13[0-9])|(14[0-9])|(15([0-3]|[5-9]))|(17[0-9])|(18[0-9])|(19[0-9])|(16[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})+$";

    /**
     * url 规则进行校验
     */
    String URL_REG = "((https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|])*";

    /**
     * 活动草稿状态校验
     */
    String DRAFT_REG = "^[0|1|2]$";

    /**
     * 业务线状态
     */
    String BUSINESS_STATUS = "^[0|1|2|3]*$";

    /**
     * 活动的追踪类型  跟踪类型的规则，随着枚举类型
     */
    String TRACINGTYPE_PATTERN = "^[0|1|2|3|4|5|6]*$";

    /**
     * description : 姓名正则表达式
     * ---------------------------------
     *
     * @Param:
     * @Return:
     * @Author: zhiyuan.xu@ikang.com
     * @Date: 2019/8/23 10:47
     */
    String CHINA_NAME = "^([\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,20})*$";
    /**
     * description : 必填 汉字（姓名）校验
     * ---------------------------------
     *
     * @Param:
     * @Return:
     * @Author: zhiyuan.xu@ikang.com
     * @Date: 2019/9/5 18:40
     */
    String CHINA_NAME_MUST = "^([\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,20})+$";

    /**
     * 数字类型校验
     */
    String NUMBER_PATTERN = "^\\d*$";
    /**
     * 数字类型校验
     */
    String NUMBER_PATTERN_MUST = "^\\d+$";

    /**
     * 日期格式校验
     */
    String DATE_REG = "((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)*";


    /**
     * 身份证校验规则
     */
    String ID_CARD = "^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X))*$";

    String TOKEN_PREFIX = "token:uid:";

    String SMS_LEVEL = "16";


    /**
     * 手机验证码的前缀
     */
    String SMSNUMBER_KEY_PREFIX = "smsnumber:key:";

    /**
     * 手机号码有效期的验证
     *
     * @Author: zhiyuan.xu@ikang.com
     * @Date: 2019/8/22 20:56
     */
    String SMS_CODE_EXPIRE_KEY = SMSNUMBER_KEY_PREFIX + "expire";

    String SMS_TEMPLATE_ID = "SMS_0";

    int SMS_CODE_EXPIRE_SECOND = 60;
    int SMS_VALUE_SECOND = 300;

    String STR_EMPTY = "";


    /**
     * 用户ID记录令牌缓存Key
     *
     * @param uid 用户ID
     * @return 缓存key
     */
    static String tokenUid(Long uid) {
        return TOKEN_PREFIX + uid;
    }


    /**
     * 标志用户在操作
     *
     * @param uid 用户ID
     * @return 缓存key
     */
    static String tokenExists(Long uid) {
        return TOKEN_PREFIX + uid + ":exists";
    }

    /**
     * 用于随机选的数字
     */
    String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的字符
     */
    String BASE_CHAR_LW = "abcdefghijklmnopqrstuvwxyz";
    String BASE_CHAR_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 用于随机选的字符和数字
     */
    String BASE_CHAR_NUMBER = BASE_CHAR_LW + BASE_CHAR_UP + BASE_NUMBER;

    /**
     * 用于随机选的字符和数字,去除容易混乱的字符,1--i,l-1,0-o
     */
    String BASE_CHAR_NUMBER_SIMPLE = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * Token 在请求头中的标识
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * 与Token绑定的token前缀
     */
    String TOKEN_JWT_PREFIX = "token:jwt:";

    /**
     * 与UID绑定的Token前缀
     */
    String TOKEN_UID_PREFIX = "token:uid:";


    String AUTH_CACHE_AUTH = "auth::authUser_";

    String AUTH_CACHE_USER = "auth::userVo_";

    String AUTH_CACHE_ROLE = "auth::authRole_";

    String AUTH_CACHE_RESOURCE = "auth::authResource_";


    String DATA_SOURCE_1 = "datasource1";
    String DATA_SOURCE_2 = "datasource2";

    String SMS_APPID_PREFIX = "idata.common_";

    String PEND_ACTIVITY_BATCH_ID = CACHE_NAME_SPACE + ":PEND_ACTIVITY_BATCH_ID:";
    String PEND_ACTIVITY_PULL_USER = CACHE_NAME_SPACE + ":PEND_ACTIVITY_PULL_USER:";
    String PUSH_ACTIVITY_ID = CACHE_NAME_SPACE + ":PUSH_ACTIVITY_ID:";
    String CLEAR_HISTORY = CACHE_NAME_SPACE + ":CLEAR_HISTORY:";

    /**
     * 短信发送管道
     */
    String BEANSTALKD_SMS_TUBE = "sms_yx";

    /**
     * 短信流水的kafka话题、分组、消费工厂
     */
    String TOPIC_OP2C_ACT_MESSAGES = "idata_act_messages";
    String GROUP_OP2C_ACT_MESSAGES = "group_idata_act_messages";
    String FACTORY_OP2C_ACT_MESSAGES = "idataActUserMsgListenerContainerFactory";


    /**
     * 用户画像的kafka话题、分组、消费工厂
     */
    String TOPIC_TAG_USER = "idata_tag_user";
    String GROUP_TAG_USER = "group_idata_tag_user";
    String FACTORY_TAG_USER = "idataTagUserListenerContainerFactory";


    /**
     * 活动卡的kafka话题、分组、消费工厂
     */
    String TOPIC_CARD_USER = "idata_card_user";
    String GROUP_CARD_USER = "group_idata_card_user";
    String FACTORY_CARD_USER = "idataCardUserListenerContainerFactory";

    String TOPIC_SAVE_CARD_DB="idata_topic_save_card_db";
    String GROUP_CARD_DB = "group_idata_card_db";
    String FACTORY_CARD_DB = "idataCardDBUserListenerContainerFactory";


    /**
     * 电商活动 kafka话题、分组、消费工厂
     */
    String TOPIC_EB_TAG_USER = "idata_EB_tag_user";
    String GROUP_EB_TAG_USER = "group_EB_idata_tag_user";
    String FACTORY_EB_TAG_USER = "idataKafkaListenerContainerFactory4";


    /**
     * 促销的kafka话题、分组、消费工厂
     */
    String TOPIC_PROMOTION_USER = "idata_promotion_user";

    String GROUP_PROMOTION_USER = "group_idata_promotion_user";

    String FACTORY_PROMOTION_USER = "idataPromotionUserListenerContainerFactory";

    /**
     * push topic
     */
    String IKANG_APP_PUSH_MESSAGE = "ikang_app_push_message";
    String DATA_CLOUD_TELEMARKETING_TOPIC = "ikang_data_cloud_teleMarketing_topic";

    /**
     * token 加密秘钥
     */
    String SECRET = ShiroMd5Util.md5("idata.common", "ikang");

    /**
     * token 颁发者
     */
    String ISSUER = "ikang";


    /**
     * 短链接图片二维码
     */
    String IMAGESCONTENT_KEY_PREFIX = "imagecontent:key:";
    /**
     * 长链接地址保存
     */
    String LONGURL_KEY_PREFIX = "longurl:key:";


    /**
     * 短链接图片二维码有效期,不过期
     */
    Integer EXIST_PERMANENT = -1;

    /**
     * 验证码KEY前缀
     */
    String CAPTCHA_KEY_PREFIX = "captcha:key:";
    /**
     * 短信验证码 key 前缀
     */
    String SMS_CAPTCHA_KEY_PREFIX = "sms:idata:captcha:key";
    String ACTIVITY_LOGIC_ID = "activity:page:";

    String ACTIVITY_PAGE_ID_MAP = "activity:page:logicId:";

    /**
     * 地域服务省份Key
     * location:privince:省份code
     */
    String LOCATION_PROVINCE_KEY_PREFIX = "location:privince:";

    /**
     * 地域服务城市Key
     * location:privince:省份code:key:城市code
     */
    String LOCATION_CITY_KEY_PREFIX = "location:city:";

    /**
     * 全部省份查询服务（省份code 、省份名称）
     * location:privince:省份code
     */
    String LOCATION_LIST_PROVINCE_KEY_PREFIX = "location:list:privince";

    /**
     * 根据省份查询城市服务（城市code 、城市名称）
     * location:privince:省份code:key:城市code
     */
    String LOCATION_LIST_CITY_KEY_PREFIX = "location:list:city:";

    /**
     * 验证码有效期,单位秒
     */
    Integer CAPTCHA_AGE = 600;

    /**
     * 验证码验证成功之后等待有效期,单位秒
     */
    Integer CAPTCHA_SUCCESS_AGE = 60;

    /**
     * Token 有效时长
     */
    int TOKEN_EXPIRE_SECOND = 7 * 24 * 60 * 60;

    /**
     * Token 操作缓存时长，每次操作均会重置该时长，标志用户在一直操作
     */
    int TOKEN_CACHE_SECOND = 7 * 24 * 60 * 60;

    /**
     * token 刷新间隔,[TOKEN_EXPIRE_SECOND - TOKEN_REFRESH_SECOND,TOKEN_EXPIRE_SECOND]之间会刷新Token
     */
    long TOKEN_REFRESH_SECOND = 3600;

    /**
     * 管理员角色CODE
     */
    String ADMIN_ROLE_CODE = "admin";

    /**
     * 基础角色CODE
     */
    String BASIC_ROLE_CODE = "basic";

    /**
     * 系统操作员角色CODE，只有一个
     */
    String SYS_OPERATOR_ROLE_CODE = "sys:operator";

    /**
     * 常用参数名称
     */
    String PARAM_USER_NAME = "userName";

    String PARAM_USER_ID = "userId";

    String PARAM_LOGIN_ID = "loginId";

    String PARAM_LOGIC_ID = "logicId";

    /**
     * 常用参数名称
     */
    String PARAM_SEARCH_BY = "searchBy";

    /**
     * 常用魔法值
     */
    int INT_0 = 0;

    /**
     * 常用魔法值
     */
    int INT_1 = 1;

    int INT_3 = 3;

    int INT_4 = 4;
    int INT_5 = 4;

    int INT_6 = 6;


    int INT_10 = 10;

    /**
     * 百度响应状态码
     */
    int NEGATIVE_1 = -1;
    /**
     * 百度响应状态码
     */
    int NEGATIVE_2 = -2;
    /**
     * 百度响应状态码
     */
    int NEGATIVE_3 = -3;
    /**
     * 百度响应状态码
     */
    int NEGATIVE_4 = -4;
    /**
     * 百度响应状态码
     */
    int NEGATIVE_5 = -5;

    /**
     * 常用魔法值
     */
    int INT_2 = 2;


    long LONG_0=0L;

    /**
     * 常用魔法值
     */
    String STR_0 = "0";

    /**
     * 常用魔法值
     * 活动类型分区
     */
    String STR_1 = "1";

    /**
     * 常用魔法值
     * 活动类型区分
     */
    String STR_2 = "2";


    /**
     * 启用、可用、正常
     */
    Integer ENABLE = INT_0;

    /**
     * 禁用、不可用、已删除
     */
    Integer UN_ENABLE = INT_1;

    /**
     * MD5算法
     */
    String ALGORITHM_MD5 = "md5";


    String SAVE_ERROR = "保存失败！";
    String EDIT_ERROR = "编辑失败！";
    String UPDATE_ERROR = "修改失败！";
    String OPERA_ERROR = "操作失败！";

    String CODE = "code";
    String UNDER_LINE = "_";
    String COMMA = ",";


    String NEXT = "next";

    String SAME = "same";

    String MONTH = "month";

    String PARAM_CARD_ONE = "cardOne";
    String PARAM_CARD_MANY = "cardMany";

    String ACTIVITY_ID= "activityId";
    String BUILDER_QSL = "idataBuildQSL";
    
    //匹配所有数字，包括带小数点的数字
    String NUMBER_REGEX = "([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])";
    //千分位
    String THOUSANDS_REGEX = "[\\d\\.\\,]+";
    //匹配所有数字，仅包括数字
    String NUMBER = "([0-9]\\d*$)";
    //导出中间表
    String EXPORT_TABLE = "role_return_data_export";
    //列表中间表
    String LIST_TABLE = "role_return_data";

    public static final String SOURCE = "_source";
    public static final String HITS = "hits";
    public static final String TOTAL = "total";
}
