package com.java1234.service.impl;

import com.java1234.dao.WebSiteInfoRepository;
import com.java1234.entity.WebSiteInfo;
import com.java1234.service.WebSiteInfoService;
import com.java1234.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 电影动态信息Service实现类
 *
 * @author Administrator
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository infoRepository;

    @Override
    public void save(WebSiteInfo info) {
        infoRepository.save(info);
    }

    @Override
    public void delete(Integer id) {
        infoRepository.delete(id);
    }

    @Override
    public WebSiteInfo findById(Integer id) {
        return infoRepository.findOne(id);
    }

    @Override
    public List<WebSiteInfo> list(WebSiteInfo info, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page - 1, pageSize, Sort.Direction.DESC, "publishDate");
        Page<WebSiteInfo> pageInfo = infoRepository.findAll(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (info != null) {
                    if (StringUtil.isNotEmpty(info.getInfo())) {
                        predicate.getExpressions().add(cb.like(root.get("info"), "%" + info.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return pageInfo.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo info) {
        Long count = infoRepository.count(new Specification<WebSiteInfo>() {

            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (info != null) {
                    if (StringUtil.isNotEmpty(info.getInfo())) {
                        predicate.getExpressions().add(cb.like(root.get("info"), "%" + info.getInfo().trim() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return infoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return infoRepository.getByWebSiteId(webSiteId);
    }

}
