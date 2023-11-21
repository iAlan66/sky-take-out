package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/18 23:16
 */
@Mapper
public interface OrderMapper {
    Double sumByMap(Map map);

    Integer countByMap(Map map);

    List<GoodsSalesDTO> getSaleTop10(LocalDateTime begin, LocalDateTime end);
}
