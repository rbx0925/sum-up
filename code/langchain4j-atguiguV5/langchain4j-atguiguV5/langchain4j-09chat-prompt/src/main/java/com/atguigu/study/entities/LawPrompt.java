package com.atguigu.study.entities;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * @auther zzyybs@126.com
 * @Date 2025-05-30 21:30
 * @Description: TODO
 */
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LawPrompt
{
    private String legal;
    private String question;
}
