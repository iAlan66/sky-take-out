package com.sky.service;

import com.sky.dto.DishDTO;

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
}
