package com.java.business.wordbook.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "wordbook_attribute")
public class WordbookAttribute {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 属性编码
     */
    @Column(name = "attribute_code")
    private String attributeCode;

    /**
     * 属性值
     */
    @Column(name = "attribute_value")
    private String attributeValue;

    /**
     * 属性名称
     */
    @Column(name = "attribute_name")
    private String attributeName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新用户编号
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建用户编号
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 删除标识(00-正常 01-删除)
     */
    @Column(name = "delete_flag")
    private String deleteFlag;
}