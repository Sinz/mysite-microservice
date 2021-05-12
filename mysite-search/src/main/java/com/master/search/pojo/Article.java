package com.master.search.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.io.Serializable;

/**
 * @Classname Article
 * @Description  是否索引，表示该字段能否搜索；是狗分词，表示匹配是单词匹配或整体匹配； 是否存储，表示是否页面显示
 * @Date 2021/5/6 16:08
 * @Created by Zhao.J
 */

@ApiModel("文章")
@Data
@Document(indexName="articleindex",type="article")
public class Article implements Serializable {


    @Id
    @ApiModelProperty("ID")
    private String id;

    @ApiModelProperty("标题")
    @Field(type = FieldType.text, index= true, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    @ApiModelProperty("正文")
    @Field(type = FieldType.keyword, index= false, analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    private String state;


}
