package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/10/12 16:53
 */
public interface DishService {
    /**
     * 保存菜品和口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
