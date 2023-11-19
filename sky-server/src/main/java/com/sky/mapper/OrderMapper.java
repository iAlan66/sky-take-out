package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/18 23:16
 */
@Mapper
public interface OrderMapper {
    Double sumByMap(Map map);
}
