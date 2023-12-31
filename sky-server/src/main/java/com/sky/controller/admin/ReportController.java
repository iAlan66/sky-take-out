package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/15 18:50
 */
@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(@DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                                              @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end){
        log.info("营业额统计：{}-{}", begin, end);
        return Result.success(reportService.getTurnoverStatistics(begin, end));
    }

    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(@DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                                                       @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end){
        log.info("用户统计：{}-{}", begin, end);
        return Result.success(reportService.getUserStatistics(begin, end));
    }

    @GetMapping("/orderStatistics")
    @ApiOperation("订单统计")
    public Result<OrderReportVO> orderStatistics(@DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                                                 @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end){
        log.info("订单统计：{}-{}", begin, end);
        return Result.success(reportService.getOrderStatistics(begin, end));
    }

    @GetMapping("/top10")
    public Result<SalesTop10ReportVO> test(@DateTimeFormat(pattern = "yy-MM-dd") LocalDate begin,
                                           @DateTimeFormat(pattern = "yy-MM-dd") LocalDate end){
        log.info("top10：{}-{}", begin, end);
        SalesTop10ReportVO reportServiceSalesTop10 = reportService.getSalesTop10(begin, end);
        return Result.success(reportServiceSalesTop10);
    }

    @GetMapping("/export")
    @ApiOperation("导出30运营数据报表")
    public void export(HttpServletResponse res) {
        reportService.exportBussinessDate(res);
    }
}
