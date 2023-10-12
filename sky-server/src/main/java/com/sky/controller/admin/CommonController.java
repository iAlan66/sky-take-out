package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/9/17 20:24
 */
@RestController
@RequestMapping("/admin/common")
@Api("通用接口")
@Slf4j
public class CommonController {


    @RequestMapping("/upload")
    public Result<String > upload(MultipartFile file) {
        log.info("上传文件");
        return null;
    }
}
