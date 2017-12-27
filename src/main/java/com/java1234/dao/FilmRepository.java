package com.java1234.dao;

import com.java1234.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影Repository接口
 *
 * @author Administrator
 */
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

    /**
     * 随机获取n个电影
     *
     * @param n
     * @return
     */
    @Query(value = "select * from t_film order by RAND() limit ?1", nativeQuery = true)
    public List<Film> randomList(Integer n);

    /**
     * 获取上一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_film where id<?1 order by id desc limit 1", nativeQuery = true)
    public Film getLast(Integer id);

    /**
     * 获取下一个电影
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_film where id>?1 order by id asc limit 1", nativeQuery = true)
    public Film getNext(Integer id);


}
