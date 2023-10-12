package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/10/12 19:57
 */
@Mapper
public interface DishFlavorMapper {

    void inserBatch(List<DishFlavor> flavors);
}
