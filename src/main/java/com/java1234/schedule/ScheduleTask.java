package com.java1234.schedule;

import com.java1234.dao.FilmRepository;
import com.java1234.entity.Film;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/1/5.
 */
@EnableScheduling
@Component
@Slf4j
public class ScheduleTask {

    private final static long MINUTE = 60 * 1000;
    @Resource
    private FilmRepository filmRepository;

    private String getHttpUrl(int id) {
        return String.format("http://47.74.147.207:8080/film/%d", id);
    }

    @Scheduled(fixedDelay = MINUTE)
    public void fixedDelayJob() {
        log.info("fixedDelayJob>>>>>>>>>>>>");
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void cronJob() {
        log.info("提交链接到百度");

        List<Film> filmList = filmRepository.findAll();
        List<String> urlList = new ArrayList<>(filmList.size());
        for (Film film : filmList) {
            urlList.add(getHttpUrl(film.getId()));
        }
        String urlJoin = StringUtils.join(urlList.toArray(), "\r\n");

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, urlJoin);
        Request request = new Request.Builder()
                .url("http://data.zz.baidu.com/urls?site=www.yilianci.com&token=JU7aGgou7yDwtdwX")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                log.info("提交链接到百度成功，{}", response.body().string());
            }
        } catch (IOException e) {
            log.error("提交链接到百度失败: {}", e.getMessage());
        }
    }
}
