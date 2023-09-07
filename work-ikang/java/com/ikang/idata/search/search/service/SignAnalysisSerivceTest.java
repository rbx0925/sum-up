package com.ikang.idata.search.search.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.SignResultTableVo;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.ikang.idata.common.consts.MagicConst.VALUE;
import static java.math.BigDecimal.ZERO;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description
 * @date 2022/5/31 10:08
 */
public class SignAnalysisSerivceTest {


    @Test
    public void test() {


        JSONObject object = JSONObject.parseObject("{\n" +
                "    \"took\": 68,\n" +
                "    \"timed_out\": false,\n" +
                "    \"_shards\": {\n" +
                "        \"total\": 5,\n" +
                "        \"successful\": 5,\n" +
                "        \"skipped\": 0,\n" +
                "        \"failed\": 0\n" +
                "    },\n" +
                "    \"hits\": {\n" +
                "        \"total\": 7131,\n" +
                "        \"max_score\": 0,\n" +
                "        \"hits\": []\n" +
                "    },\n" +
                "    \"aggregations\": {\n" +
                "        \"sign_date\": {\n" +
                "            \"buckets\": [\n" +
                "                {\n" +
                "                    \"key\": \"同期\",\n" +
                "                    \"from\": 1617235200000,\n" +
                "                    \"from_as_string\": \"2021-04-01 00:00:00\",\n" +
                "                    \"to\": 1622160000000,\n" +
                "                    \"to_as_string\": \"2021-05-28 00:00:00\",\n" +
                "                    \"doc_count\": 231,\n" +
                "                    \"brm_department\": {\n" +
                "                        \"doc_count_error_upper_bound\": 0,\n" +
                "                        \"sum_other_doc_count\": 0,\n" +
                "                        \"buckets\": [\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区大客户一部\",\n" +
                "                                \"doc_count\": 119,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 69,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 3697337\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 69\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 50,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 11258782\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 50\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 14956119\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 119\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区银行部\",\n" +
                "                                \"doc_count\": 35,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 18,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 1531310\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 18\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 17,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 2214334\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 17\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 3745644\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 35\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区渠道部\",\n" +
                "                                \"doc_count\": 30,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 17,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 1547634\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 17\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 13,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 1513054\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 13\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 3060688\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 30\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区保险部\",\n" +
                "                                \"doc_count\": 27,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 15,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 548333\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 15\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 12,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 684379\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 12\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 1232712\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 27\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区分院\",\n" +
                "                                \"doc_count\": 19,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 17,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 522247.0966796875\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 17\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 2,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 30019\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 2\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 552266.0966796875\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 19\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区离职人员\",\n" +
                "                                \"doc_count\": 1,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 1,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 3000\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 1\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 3000\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 1\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"key\": \"当期\",\n" +
                "                    \"from\": 1648771200000,\n" +
                "                    \"from_as_string\": \"2022-04-01 00:00:00\",\n" +
                "                    \"to\": 1653696000000,\n" +
                "                    \"to_as_string\": \"2022-05-28 00:00:00\",\n" +
                "                    \"doc_count\": 203,\n" +
                "                    \"brm_department\": {\n" +
                "                        \"doc_count_error_upper_bound\": 0,\n" +
                "                        \"sum_other_doc_count\": 0,\n" +
                "                        \"buckets\": [\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区大客户一部\",\n" +
                "                                \"doc_count\": 80,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 54,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 16002438\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 54\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 26,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 3797443\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 26\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 19799881\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 80\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区银行部\",\n" +
                "                                \"doc_count\": 43,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 32,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 4540564.59375\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 32\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 11,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 2573558\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 11\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 7114122.59375\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 43\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区保险部\",\n" +
                "                                \"doc_count\": 29,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 15,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 3083739.546875\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 15\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 14,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 1483516.08984375\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 14\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 4567255.63671875\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 29\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区渠道部\",\n" +
                "                                \"doc_count\": 27,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 17,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 4748620\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 17\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 10,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 1318706\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 10\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 6067326\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 27\n" +
                "                                }\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"key\": \"成都区,成都区分院\",\n" +
                "                                \"doc_count\": 24,\n" +
                "                                \"newoldtype\": {\n" +
                "                                    \"doc_count_error_upper_bound\": 0,\n" +
                "                                    \"sum_other_doc_count\": 0,\n" +
                "                                    \"buckets\": [\n" +
                "                                        {\n" +
                "                                            \"key\": \"新项目\",\n" +
                "                                            \"doc_count\": 14,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 170961\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 14\n" +
                "                                            }\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"key\": \"老项目\",\n" +
                "                                            \"doc_count\": 10,\n" +
                "                                            \"sign_money\": {\n" +
                "                                                \"value\": 954658\n" +
                "                                            },\n" +
                "                                            \"sign_project_id\": {\n" +
                "                                                \"value\": 10\n" +
                "                                            }\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                \"sign_money\": {\n" +
                "                                    \"value\": 1125619\n" +
                "                                },\n" +
                "                                \"sign_project_id\": {\n" +
                "                                    \"value\": 24\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}");
        JSONArray currentList = (JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]." + "brm_department" + ".buckets"));
        JSONArray correspondingList = (JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]." +  "brm_department"+ ".buckets"));

        List<String> currentKeys = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]." +  "brm_department"+ ".buckets[*].key")).toList(String.class);

        List<String> correspondingKeys = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]." +  "brm_department" + ".buckets[*].key")).toList(String.class);

        List<String> union = (List) CollectionUtil.union(currentKeys, correspondingKeys);

        List<SignResultTableVo> list = new ArrayList<>();
        for (int i = 0; i < union.size(); i++) {
            String str = union.get(i);
            SignResultTableVo result = new SignResultTableVo();
            setBrmInfo("brm_department", result, str);
            JSONObject currentJson = new JSONObject();
            JSONObject correspondingJson = new JSONObject();
            Optional<Object> key = currentList.stream().filter(Objects::nonNull)
                    .map(JSON::toJSON)
                    .filter(e -> str.equals( ((JSONObject) e).get("key").toString())).findFirst();
            if (key.isPresent()) {
                currentJson = (JSONObject)JSON.toJSON(key.get());
            }
            Optional<Object> key2 = correspondingList.stream()
                    .filter(Objects::nonNull)
                    .map(JSON::toJSON)
                    .filter(e -> str.equals( ((JSONObject) e).get("key").toString())).findFirst();
            if (key2.isPresent()) {
                correspondingJson = (JSONObject)JSON.toJSON(key2.get());
            }
            // 签单个数
            BigDecimal project = ZERO;
            JSONObject currentSignProject = currentJson.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            if (null != currentSignProject) {
                project = currentSignProject.getBigDecimal(VALUE);
            }
            result.setSignProjectIdCount(project);

            // 签单金额
            BigDecimal money = ZERO;
            JSONObject currentSignMoney = currentJson.getJSONObject(OrderInfoEnum.sign_money.getFields());
            if (null != currentSignMoney) {
                money = currentSignMoney.getBigDecimal(VALUE);
            }
            result.setSignMoneySum(money);


            JSONObject jsonObject = currentJson.getJSONObject(OrderInfoEnum.newoldtype.getFields());
            JSONObject newType = new JSONObject();
            JSONObject oldType = new JSONObject();

            JSONArray newObjects = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObject, "$.buckets[*][?(@.key=='新项目')]"));
            JSONArray oldObjects = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObject, "$.buckets[*][?(@.key=='老项目')]"));
            if (null != newObjects && newObjects.size() > 0) {
                newType = newObjects.getJSONObject(0);
            }
            if (null != oldObjects && oldObjects.size() > 0) {
                oldType = oldObjects.getJSONObject(0);
            }

            JSONObject jsonNewProject = newType.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonNewMoney = newType.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal newProject = ZERO;
            BigDecimal newMoney = ZERO;
            if (null != jsonNewProject) {
                newProject = jsonNewProject.getBigDecimal(VALUE);
            }
            result.setNewSignProjectIdCount(newProject);
            if (null != jsonNewMoney) {
                newMoney = jsonNewMoney.getBigDecimal(VALUE);
            }
            result.setNewSignMoneySum(newMoney);
            JSONObject jsonOldProject = oldType.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonOldMoney = oldType.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal oldProject = ZERO;
            BigDecimal oldMoney = ZERO;
            if (null != jsonOldProject) {
                oldProject = jsonOldProject.getBigDecimal(VALUE);
            }
            result.setOldSignProjectIdCount(oldProject);
            if (null != jsonOldMoney) {
                oldMoney = jsonOldMoney.getBigDecimal(VALUE);
            }
            result.setOldSignMoneySum(oldMoney);
            // 同期签单个数
            BigDecimal projectBefore = ZERO;
            JSONObject correspondingSignProject = correspondingJson.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            if (null != correspondingSignProject) {
                projectBefore = correspondingSignProject.getBigDecimal(VALUE);
            }
            result.setSignProjectIdCountLastYear(projectBefore);

            // 同期签单金额
            BigDecimal moneyBefore = ZERO;
            JSONObject correspondingSignMoney = correspondingJson.getJSONObject(OrderInfoEnum.sign_money.getFields());
            if (null != correspondingSignMoney) {
                moneyBefore = correspondingSignMoney.getBigDecimal(VALUE);
            }
            result.setSignMoneySumLastYear(moneyBefore);

            JSONObject jsonObjectCorr = correspondingJson.getJSONObject(OrderInfoEnum.newoldtype.getFields());
            JSONObject newTypeCorr = new JSONObject();
            JSONObject oldTypeCorr = new JSONObject();

            JSONArray newObjectsCorr = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObjectCorr, "$.buckets[*][?(@.key=='新项目')]"));
            JSONArray oldObjectsCorr = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObjectCorr, "$.buckets[*][?(@.key=='老项目')]"));
            if (null != newObjectsCorr && newObjectsCorr.size() > 0) {
                newTypeCorr = newObjectsCorr.getJSONObject(0);
            }
            if (null != oldObjectsCorr && oldObjectsCorr.size() > 0) {
                oldTypeCorr = oldObjectsCorr.getJSONObject(0);
            }

            JSONObject jsonNewProjectCorr = newTypeCorr.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonNewMoneyCorr = newTypeCorr.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal newProjectCorr = ZERO;
            BigDecimal newMoneyCorr = ZERO;
            if (null != jsonNewProjectCorr) {
                newProjectCorr = jsonNewProjectCorr.getBigDecimal(VALUE);
            }
            result.setNewSignProjectIdCountLastYear(newProjectCorr);
            if (null != jsonNewMoneyCorr) {
                newMoneyCorr = jsonNewMoneyCorr.getBigDecimal(VALUE);
            }
            result.setNewSignMoneySumLastYear(newMoneyCorr);
            JSONObject jsonOldProjectCorr = oldTypeCorr.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonOldMoneyCorr = oldTypeCorr.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal oldProjectCorr = ZERO;
            BigDecimal oldMoneyCorr = ZERO;
            if (null != jsonOldProjectCorr) {
                oldProjectCorr = jsonOldProjectCorr.getBigDecimal(VALUE);
            }
            result.setOldSignProjectIdCountLastYear(oldProjectCorr);
            if (null != jsonOldMoneyCorr) {
                oldMoneyCorr = jsonOldMoneyCorr.getBigDecimal(VALUE);
            }
            result.setOldSignMoneySumLastYear(oldMoneyCorr);

            list.add(result);
        }
        System.out.println(JSONObject.toJSONString(list));
        System.out.println("====================================================");
        System.out.println(list);
    }


    private void setBrmInfo(String groupBy, SignResultTableVo result, String info) {
        result.setBrmZone(info.split(MagicConst.COMMA_SPLIT)[0]);
        if ("brm_department".equals(groupBy)) {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
        }else if ("brm_group".equals(groupBy)) {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
            result.setBrmGroup(info.split(MagicConst.COMMA_SPLIT)[2]);
        }else {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
            result.setBrmGroup(info.split(MagicConst.COMMA_SPLIT)[2]);
            result.setOwnerName(info.split(MagicConst.COMMA_SPLIT)[3]);
        }
    }
}
