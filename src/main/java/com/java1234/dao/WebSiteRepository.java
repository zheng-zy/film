package com.java1234.dao;

import com.java1234.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 电影Repository接口
 *
 * @author Administrator
 */
public interface WebSiteRepository extends JpaRepository<WebSite, Integer>, JpaSpecificationExecutor<WebSite> {


}
