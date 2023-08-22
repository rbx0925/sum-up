package com.ikang.idata.search.search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static com.ikang.idata.common.entity.ReportsWay.KEY;

/**
 * @author rbx
 * @title
 * @Create 2023-06-25 14:11
 * @Description
 */
public class TestStream {
    public static void main(String[] args) {
        JSONObject job = JSONObject.parseObject("{\n" +
                "  \"took\" : 12,\n" +
                "  \"timed_out\" : false,\n" +
                "  \"_shards\" : {\n" +
                "    \"total\" : 5,\n" +
                "    \"successful\" : 5,\n" +
                "    \"skipped\" : 0,\n" +
                "    \"failed\" : 0\n" +
                "  },\n" +
                "  \"hits\" : {\n" +
                "    \"total\" : 9,\n" +
                "    \"max_score\" : 1.0,\n" +
                "    \"hits\" : [\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"wgx63IgBL3GpU7q1BPMS\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小白兔\",\n" +
                "          \"price\" : 100,\n" +
                "          \"type\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"MLMBV4gBL3GpU7q1m-_F\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小黑兔\",\n" +
                "          \"price\" : 200,\n" +
                "          \"type\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"lLMBV4gBL3GpU7q15vBE\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小黑兔\",\n" +
                "          \"price\" : 400,\n" +
                "          \"type\" : \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"4rMBV4gBL3GpU7q11-_-\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小黑兔\",\n" +
                "          \"price\" : 300,\n" +
                "          \"type\" : \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"dAx63IgBL3GpU7q1HvRw\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小白兔\",\n" +
                "          \"price\" : 300,\n" +
                "          \"type\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"nvpW2IgBL3GpU7q1YlbC\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小黑兔\",\n" +
                "          \"price\" : 400,\n" +
                "          \"type\" : \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"1PpX2IgBL3GpU7q1wlwC\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小白兔\",\n" +
                "          \"price\" : 100,\n" +
                "          \"type\" : 1\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"wwx63IgBL3GpU7q1E_PD\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小白兔\",\n" +
                "          \"price\" : 200,\n" +
                "          \"type\" : null\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"_index\" : \"rabbit1\",\n" +
                "        \"_type\" : \"_doc\",\n" +
                "        \"_id\" : \"4bMBV4gBL3GpU7q1uO8j\",\n" +
                "        \"_score\" : 1.0,\n" +
                "        \"_source\" : {\n" +
                "          \"name\" : \"小黑兔\",\n" +
                "          \"price\" : 100,\n" +
                "          \"type\" : null\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"aggregations\" : {\n" +
                "    \"NAME1\" : {\n" +
                "      \"doc_count\" : 5,\n" +
                "      \"NAME2\" : {\n" +
                "        \"doc_count_error_upper_bound\" : 0,\n" +
                "        \"sum_other_doc_count\" : 0,\n" +
                "        \"buckets\" : [\n" +
                "          {\n" +
                "            \"key\" : \"小白兔\",\n" +
                "            \"doc_count\" : 3,\n" +
                "            \"NAME3\" : {\n" +
                "              \"doc_count_error_upper_bound\" : 0,\n" +
                "              \"sum_other_doc_count\" : 0,\n" +
                "              \"buckets\" : [\n" +
                "                {\n" +
                "                  \"key\" : 100,\n" +
                "                  \"doc_count\" : 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"key\" : 200,\n" +
                "                  \"doc_count\" : 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"key\" : 300,\n" +
                "                  \"doc_count\" : 1\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          },\n" +
                "          {\n" +
                "            \"key\" : \"小黑兔\",\n" +
                "            \"doc_count\" : 2,\n" +
                "            \"NAME3\" : {\n" +
                "              \"doc_count_error_upper_bound\" : 0,\n" +
                "              \"sum_other_doc_count\" : 0,\n" +
                "              \"buckets\" : [\n" +
                "                {\n" +
                "                  \"key\" : 100,\n" +
                "                  \"doc_count\" : 1\n" +
                "                },\n" +
                "                {\n" +
                "                  \"key\" : 200,\n" +
                "                  \"doc_count\" : 1\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n");
        JSONObject jsonObject = job.getJSONObject(AGGREGATIONS);
        JSONObject name1 = jsonObject.getJSONObject("NAME1");
        JSONObject name2 = name1.getJSONObject("NAME2");
        JSONArray jsonArray = name2.getJSONArray(BUCKETS);
        Map<String, BigDecimal> collect = jsonArray.stream()
                .map(JSON::toJSON)
                .map(JSONObject.class::cast)
                .filter(jsonObject1 -> "小白兔".equals(jsonObject1.getString(KEY)))
                .flatMap(job1 -> job1.getJSONObject("NAME3").getJSONArray(BUCKETS).stream())
                .map(JSONObject.class::cast)
                .collect(Collectors.toMap(json -> json.getString(KEY), json -> json.getBigDecimal(DOC_COUNT), BigDecimal::add));
        System.out.println("collect = " + collect);

    }
}
