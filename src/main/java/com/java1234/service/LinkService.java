package com.java1234.service;

import com.java1234.entity.Link;

import java.util.List;

/**
 * 友情链接Service接口
 *
 * @author Administrator
 */
public interface LinkService {

    /**
     * 分页查询友情链接
     *
     * @return
     */
    public List<Link> list(Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    public Long getCount();

    /**
     * 添加或者保存友情链接
     */
    public void save(Link link);

    /**
     * 根据id删除友情链接
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询所有友情链接
     *
     * @return
     */
    public List<Link> listAll();
}
