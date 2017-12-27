package com.java1234.controller.admin;

import com.java1234.entity.WebSite;
import com.java1234.init.InitSystem;
import com.java1234.service.WebSiteInfoService;
import com.java1234.service.WebSiteService;
import com.java1234.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影网站Controller类
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/admin/webSite")
public class WebSiteAdminController {

    @Resource
    private WebSiteService webSiteService;

    @Resource
    private WebSiteInfoService webSiteInfoService;


    @Resource
    private InitSystem initSystem;

    /**
     * 分页查询电影网站信息
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(WebSite webSite, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<WebSite> webSiteList = webSiteService.list(webSite, page, rows);
        Long total = webSiteService.getCount(webSite);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", webSiteList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 下拉框模糊查询
     *
     * @param name
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/comboList")
    public List<WebSite> comboList(String q) throws Exception {
        if (StringUtil.isEmpty(q)) {
            return null;
        }
        WebSite webSite = new WebSite();
        webSite.setUrl(q);
        return webSiteService.list(webSite, 1, 30); // 最多查询30条
    }


    /**
     * 添加或者修改电影网站信息
     *
     * @param link
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> save(WebSite webSite, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        webSiteService.save(webSite);
        initSystem.loadData(request.getServletContext());
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除友情电影网站信息
     *
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids, HttpServletRequest request) throws Exception {
        String[] idsStr = ids.split(",");
        Map<String, Object> resultMap = new HashMap<>();
        boolean flag = true;
        for (int i = 0; i < idsStr.length; i++) {
            Integer webSiteId = Integer.parseInt(idsStr[i]);
            if (webSiteInfoService.getByWebSiteId(webSiteId).size() > 0) {
                flag = false;
            } else {
                webSiteService.delete(Integer.parseInt(idsStr[i]));
            }
        }
        initSystem.loadData(request.getServletContext());
        if (flag) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "电影动态信息中存在电影网站信息，不能删除！");
        }
        return resultMap;
    }

}
