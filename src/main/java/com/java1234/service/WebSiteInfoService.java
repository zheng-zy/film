package com.java1234.service;

import com.java1234.entity.WebSiteInfo;

import java.util.List;

/**
 * 电影动态信息Service接口
 *
 * @author Administrator
 */
public interface WebSiteInfoService {


    /**
     * 添加或者保存电影动态信息
     */
    public void save(WebSiteInfo info);

    /**
     * 根据id删除电影动态信息
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
    public WebSiteInfo findById(Integer id);

    /**
     * 根据条件分页查询电影动态信息
     *
     * @param info
     * @param pageBean
     * @return
     */
    public List<WebSiteInfo> list(WebSiteInfo info, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    public Long getCount(WebSiteInfo info);

    /**
     * 根据电影id查询动态信息
     *
     * @param filmId
     * @return
     */
    public List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网站id查询动态信息
     *
     * @param webSiteId
     * @return
     */
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
