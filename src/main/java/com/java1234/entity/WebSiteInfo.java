package com.java1234.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * 网站更新电影信息实体
 *
 * @author user
 */
@Entity
@Table(name = "t_info")
public class WebSiteInfo {

    @Id
    @GeneratedValue
    private Integer id; // 编号

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film; // 电影

    @ManyToOne
    @JoinColumn(name = "webSiteId")
    private WebSite webSite; // 网站

    @Column(length = 1000)
    private String info; // 信息

    @Column(length = 500)
    private String url; // 具体网址

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate; // 发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


}
