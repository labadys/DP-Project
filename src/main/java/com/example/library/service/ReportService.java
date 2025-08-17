package com.example.library.service;

import java.time.LocalDate;

public interface ReportService {
    String generateBorrowingReport(LocalDate startDate, LocalDate endDate);
    String generateUserActivityReport(Long userId);
}