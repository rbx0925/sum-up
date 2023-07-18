package com.ikang.idata.common.utils;

import cn.hutool.core.util.StrUtil;
import com.ikang.idata.common.consts.MagicConst;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * SurnameUtil
 * 复姓
 *
 * @author <a href="mailto:haigang.jia@ikang.com">Jia haiGang</a>
 * @date 2019年11月27日
 */
public class SurnameUtil {

    private static Map<String, String> SURNAME_MAP = new HashMap<>();
    private static Map<Integer, String> SEX_MAP = new HashMap<>(2, 1);

    static {
        SURNAME_MAP.put("欧阳", "欧阳");
        SURNAME_MAP.put("太史", "太史");
        SURNAME_MAP.put("端木", "端木");
        SURNAME_MAP.put("上官", "上官");
        SURNAME_MAP.put("司马", "司马");
        SURNAME_MAP.put("东方", "东方");
        SURNAME_MAP.put("独孤", "独孤");
        SURNAME_MAP.put("南宫", "南宫");
        SURNAME_MAP.put("万俟", "万俟");
        SURNAME_MAP.put("闻人", "闻人");
        SURNAME_MAP.put("夏侯", "夏侯");
        SURNAME_MAP.put("诸葛", "诸葛");
        SURNAME_MAP.put("尉迟", "尉迟");
        SURNAME_MAP.put("公羊", "公羊");
        SURNAME_MAP.put("赫连", "赫连");
        SURNAME_MAP.put("澹台", "澹台");
        SURNAME_MAP.put("皇甫", "皇甫");
        SURNAME_MAP.put("宗政", "宗政");
        SURNAME_MAP.put("濮阳", "濮阳");
        SURNAME_MAP.put("公冶", "公冶");
        SURNAME_MAP.put("太叔", "太叔");
        SURNAME_MAP.put("申屠", "申屠");
        SURNAME_MAP.put("公孙", "公孙");
        SURNAME_MAP.put("慕容", "慕容");
        SURNAME_MAP.put("仲孙", "仲孙");
        SURNAME_MAP.put("钟离", "钟离");
        SURNAME_MAP.put("长孙", "长孙");
        SURNAME_MAP.put("宇文", "宇文");
        SURNAME_MAP.put("司徒", "司徒");
        SURNAME_MAP.put("鲜于", "鲜于");
        SURNAME_MAP.put("司空", "司空");
        SURNAME_MAP.put("闾丘", "闾丘");
        SURNAME_MAP.put("子车", "子车");
        SURNAME_MAP.put("亓官", "亓官");
        SURNAME_MAP.put("司寇", "司寇");
        SURNAME_MAP.put("巫马", "巫马");
        SURNAME_MAP.put("公西", "公西");
        SURNAME_MAP.put("颛孙", "颛孙");
        SURNAME_MAP.put("壤驷", "壤驷");
        SURNAME_MAP.put("公良", "公良");
        SURNAME_MAP.put("漆雕", "漆雕");
        SURNAME_MAP.put("乐正", "乐正");
        SURNAME_MAP.put("宰父", "宰父");
        SURNAME_MAP.put("谷梁", "谷梁");
        SURNAME_MAP.put("拓跋", "拓跋");
        SURNAME_MAP.put("夹谷", "夹谷");
        SURNAME_MAP.put("轩辕", "轩辕");
        SURNAME_MAP.put("令狐", "令狐");
        SURNAME_MAP.put("段干", "段干");
        SURNAME_MAP.put("百里", "百里");
        SURNAME_MAP.put("呼延", "呼延");
        SURNAME_MAP.put("东郭", "东郭");
        SURNAME_MAP.put("南门", "南门");
        SURNAME_MAP.put("羊舌", "羊舌");
        SURNAME_MAP.put("微生", "微生");
        SURNAME_MAP.put("公户", "公户");
        SURNAME_MAP.put("公玉", "公玉");
        SURNAME_MAP.put("公仪", "公仪");
        SURNAME_MAP.put("梁丘", "梁丘");
        SURNAME_MAP.put("公仲", "公仲");
        SURNAME_MAP.put("公上", "公上");
        SURNAME_MAP.put("公门", "公门");
        SURNAME_MAP.put("公山", "公山");
        SURNAME_MAP.put("公坚", "公坚");
        SURNAME_MAP.put("左丘", "左丘");
        SURNAME_MAP.put("公伯", "公伯");
        SURNAME_MAP.put("西门", "西门");
        SURNAME_MAP.put("公祖", "公祖");
        SURNAME_MAP.put("第五", "第五");
        SURNAME_MAP.put("公乘", "公乘");
        SURNAME_MAP.put("贯丘", "贯丘");
        SURNAME_MAP.put("公皙", "公皙");
        SURNAME_MAP.put("南荣", "南荣");
        SURNAME_MAP.put("东里", "东里");
        SURNAME_MAP.put("东宫", "东宫");
        SURNAME_MAP.put("仲长", "仲长");
        SURNAME_MAP.put("子书", "子书");
        SURNAME_MAP.put("子桑", "子桑");
        SURNAME_MAP.put("即墨", "即墨");
        SURNAME_MAP.put("淳于", "淳于");
        SURNAME_MAP.put("达奚", "达奚");
        SURNAME_MAP.put("褚师", "褚师");
        SURNAME_MAP.put("吴铭", "吴铭");
        SURNAME_MAP.put("纳兰", "纳兰");
        SURNAME_MAP.put("归海", "归海");
        SEX_MAP.put(0, "女士");
        SEX_MAP.put(1, "先生");
    }


    public static String getSurName(String name) {
        if (name.length() <= MagicConst.INT_2) {
            return name.substring(0, 1);
        }
        String surname = SURNAME_MAP.get(name.substring(MagicConst.INT_0, MagicConst.INT_2));
        if (Objects.isNull(surname)) {
            return name.substring(0, 1);
        } else {
            return surname;
        }
    }

    public static String getAppellation(Integer sex) {
        String appellation = SEX_MAP.get(sex);
        if (StrUtil.isBlank(appellation)) {
            return SEX_MAP.get(0);
        }
        return appellation;
    }
}
