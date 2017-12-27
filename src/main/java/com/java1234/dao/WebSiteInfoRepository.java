package com.java1234.dao;

import com.java1234.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影Repository接口
 *
 * @author Administrator
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Integer>, JpaSpecificationExecutor<WebSiteInfo> {

    /**
     * 根据电影id查询动态信息
     *
     * @param filmId
     * @return
     */
    @Query(value = "select * from t_info where film_id=?1", nativeQuery = true)
    public List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网站id查询动态信息
     *
     * @param webSiteId
     * @return
     */
    @Query(value = "select * from t_info where web_site_id=?1", nativeQuery = true)
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
