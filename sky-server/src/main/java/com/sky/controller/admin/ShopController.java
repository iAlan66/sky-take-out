package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/10/17 9:42
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("设置店铺状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺状态：{}", status == 1 ? "营业中" : "休息中");
        redisTemplate.opsForValue().set("SHOP_STATUS", status);
        return Result.success();
    }


    @GetMapping("/status")
    @ApiOperation("获取店铺状态")
    public Result getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取的店铺状态为{}", status == 1 ? "营业中" : "休息中");
        return Result.success(status);
    }
}
