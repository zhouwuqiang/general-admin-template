package com.java.business.wordbook.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "wordbook")
public class Wordbook {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 字典编码
     */
    @Column(name = "wordbook_code")
    private String wordbookCode;

    /**
     * 字典名称
     */
    @Column(name = "wordbook_name")
    private String wordbookName;

    /**
     * 字典状态(00-正常 01-禁用)
     */
    @Column(name = "wordbook_status")
    private String wordbookStatus;

    /**
     * 备注
     */
    @Column(name = "memo")
    private String memo;

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