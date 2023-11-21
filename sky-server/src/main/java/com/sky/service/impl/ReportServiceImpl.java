package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/18 16:08
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper ordermapepr;
    @Autowired
    private UserMapper userMapper;

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

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        // 计算begin到end的所有日期，并存到dateList中
        for (LocalDate date = begin; date.isBefore(end); date = date.plusDays(1)) {
            dateList.add(date);
        }
        List<Integer> totalUserList = new ArrayList<>();
        List<Integer> newUserList = new ArrayList<>();
        for (LocalDate date : dateList) {
            // 查询date这一天的用户数
            // select count(distinct user_id) from orders where order_time > ? and order_tim < ? and status = 5;
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map totalMap = new HashMap();
            Map newMap = new HashMap();
            totalMap.put("end", endTime);
            newMap.put("begin", beginTime);
            newMap.put("end", endTime);
            Integer totalUserCount = userMapper.countByMap(totalMap);
            Integer newUserCount = userMapper.countByMap(newMap);
            totalUserCount = totalUserCount == null ? 0 : totalUserCount;
            newUserCount = newUserCount == null ? 0 : newUserCount;
            // 将用户数存到userList中
            totalUserList.add(totalUserCount);
            newUserList.add(newUserCount);
        }
        return UserReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .build();
    }

    @Override
    public OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        // 计算begin到end的所有日期，并存到dateList中
        for (LocalDate date = begin; date.isBefore(end); date = date.plusDays(1)) {
            dateList.add(date);
        }
        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        for (LocalDate date : dateList) {
            // 查询date这一天的订单数
            // select count(id) from orders where order_time > ? and order_tim < ?;
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer orderCount = ordermapepr.countByMap(map);
            orderCount = orderCount == null ? 0 : orderCount;
            // 查询date这一天的有效订单数
            // select count(id) from orders where order_time > ? and order_tim < ? and status = 5;
            map.put("status", 5);
            Integer validOrderCount = ordermapepr.countByMap(map);
            validOrderCount = validOrderCount == null ? 0 : validOrderCount;
            // 将订单数存到orderCountList中
            orderCountList.add(orderCount);
            validOrderCountList.add(validOrderCount);
        }

        Integer orderCount = orderCountList.stream().mapToInt(Integer::intValue).sum();
        Integer validCount = validOrderCountList.stream().mapToInt(Integer::intValue).sum();

        Double orderCompletionRate = 0.0;
        if (orderCount != 0) {
            orderCompletionRate = validCount * 1.0 / orderCount;
        }
        return OrderReportVO.builder()
                .dateList(StringUtils.join(dateList, ","))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .totalOrderCount(orderCount)
                .validOrderCount(validCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }

    @Override
    public SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);
        List<GoodsSalesDTO> goodsSalesDTOList = ordermapepr.getSaleTop10(beginTime, endTime);
        List<String> nameList = goodsSalesDTOList.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        List<Integer> numberList = goodsSalesDTOList.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        return SalesTop10ReportVO.builder()
                .nameList(StringUtils.join(nameList, ","))
                .numberList(StringUtils.join(numberList, ","))
                .build();
    }

}
