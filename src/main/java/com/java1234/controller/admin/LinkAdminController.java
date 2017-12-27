package com.java1234.controller.admin;

import com.java1234.entity.Link;
import com.java1234.init.InitSystem;
import com.java1234.service.LinkService;
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
 * 友情链接Controller类
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    @Resource
    private InitSystem initSystem;

    /**
     * 分页查询友情链接
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<Link> linkList = linkService.list(page - 1, rows);
        Long total = linkService.getCount();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", linkList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加或者修改友情链接
     *
     * @param link
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> save(Link link, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        linkService.save(link);
        initSystem.loadData(request.getServletContext());
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除友情链接信息
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
        for (int i = 0; i < idsStr.length; i++) {
            linkService.delete(Integer.parseInt(idsStr[i]));
        }
        initSystem.loadData(request.getServletContext());
        resultMap.put("success", true);
        return resultMap;
    }

}
