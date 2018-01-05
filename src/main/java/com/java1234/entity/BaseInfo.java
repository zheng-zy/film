package com.java1234.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/1/5.
 */
@Entity
@Table(name = "t_base_info")
@Getter@Setter
public class BaseInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String picture;
    private String director;
    private String screenwriter;
    private String star;
    private String type;
    private String country;
    private String language;
    private String date;
    private String length;
    private String aka;
    private String url;
    private Integer score;
    private Integer people;
    private String introduction;
    private Date createDate;
    private Date updateDate;
    private Integer status;

}
