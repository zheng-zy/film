package com.java1234.service;

import com.java1234.entity.Film;

import java.util.List;

/**
 * 电影Service接口
 *
 * @author Administrator
 */
public interface FilmService {


    /**
     * 添加或者保存电影
     */
    public void save(Film film);

    /**
     * 根据id删除电影
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
    public Film findById(Integer id);

    /**
     * 根据条件分页查询电影信息
     *
     * @param film
     * @param pageBean
     * @return
     */
    public List<Film> list(Film film, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    public Long getCount(Film film);

    /**
     * 获取上一个电影
     *
     * @param id
     * @return
     */
    public Film getLast(Integer id);

    /**
     * 获取下一个电影
     *
     * @param id
     * @return
     */
    public Film getNext(Integer id);

    /**
     * 随机查询n个电影
     *
     * @param n
     * @return
     */
    public List<Film> randomList(Integer n);

}
