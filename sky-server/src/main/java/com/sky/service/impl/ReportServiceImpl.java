package com.sky.service.impl;

import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/18 16:08
 */
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper ordermapepr;

    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        // 计算begin到end的所有日期，并存到dateList中
        for (LocalDate date = begin; date.isBefore(end); date = date.plusDays(1)) {
            dateList.add(date);
        }
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            // 查询date这一天的营业额
            // select sum(amount) from orders where order_time > ? and order_tim < ? and status = 5;
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", 5);
            Double turnover = ordermapepr.sumByMap(map);
            turnover = turnover == null ? 0.0 : turnover;
            // 将营业额存到turnoverList中
            turnoverList.add(turnover);
        }
        return TurnoverReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }
}
