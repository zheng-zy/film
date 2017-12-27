package com.java1234.controller.admin;

import com.java1234.entity.Film;
import com.java1234.init.InitSystem;
import com.java1234.service.FilmService;
import com.java1234.service.WebSiteInfoService;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 电影Controller类
 *
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/admin/film")
public class FilmAdminController {

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Resource
    private InitSystem initSystem;

    /**
     * 分页查询电影
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(Film film, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<Film> filmList = filmService.list(film, page, rows);
        Long total = filmService.getCount(film);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", filmList);
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
    public List<Film> comboList(String q) throws Exception {
        if (StringUtil.isEmpty(q)) {
            return null;
        }
        Film film = new Film();
        film.setName(q);
        return filmService.list(film, 1, 30); // 最多查询30条记录
    }

    /**
     * 添加或者修改电影
     *
     * @param film
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> save(Film film, @RequestParam("imageFile") MultipartFile file, HttpServletRequest request) throws Exception {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));
            film.setImageName(newFileName);
        }
        film.setPublishDate(new Date());
        Map<String, Object> resultMap = new HashMap<>();
        filmService.save(film);
        initSystem.loadData(request.getServletContext());
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除电影信息
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
            Integer filmId = Integer.parseInt(idsStr[i]);
            if (webSiteInfoService.getByFilmId(filmId).size() > 0) {
                flag = false;
            } else {
                filmService.delete(filmId);
            }
        }
        initSystem.loadData(request.getServletContext());
        if (flag) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "电影动态信息中存在电影信息，不能删除！");
        }
        return resultMap;
    }

    /**
     * 根据id查询实体
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Film findById(@RequestParam(value = "id") Integer id) throws Exception {
        Film film = filmService.findById(id);
        return film;
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum) throws Exception {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = DateUtil.getCurrentDateStr() + suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/static/filmImage/" + newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }


}
