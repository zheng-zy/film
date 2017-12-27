package com.java1234.service;

import com.java1234.entity.WebSite;

import java.util.List;

/**
 * 电影网址Service接口
 *
 * @author Administrator
 */
public interface WebSiteService {

    /**
     * 添加或者保存电影网址
     */
    public void save(WebSite webSite);

    /**
     * 根据id删除电影网址
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查询实体
     *
     * @param id
     * @return
     */
    public WebSite findById(Integer id);

    /**
     * 根据条件分页查询电影网址信息
     *
     * @param webSite
     * @param pageBean
     * @return
     */
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    public Long getCount(WebSite webSite);
}
