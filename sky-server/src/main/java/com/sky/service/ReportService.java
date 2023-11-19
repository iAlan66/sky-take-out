package com.sky.service;

import com.sky.vo.TurnoverReportVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Alan
 * @version 1.0
 * @create 2023/11/18 16:07
 */
@Service
public interface ReportService {

    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);
}
