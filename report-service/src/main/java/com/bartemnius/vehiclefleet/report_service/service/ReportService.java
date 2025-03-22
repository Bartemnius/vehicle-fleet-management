package com.bartemnius.vehiclefleet.report_service.service;

import com.bartemnius.vehiclefleet.report_service.model.Report;
import com.bartemnius.vehiclefleet.report_service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public void saveReport(String vin, String userId, String action) {
        Report report = new Report(null, vin, userId, action, LocalDateTime.now());
        reportRepository.save(report);
    }

    public List<Report> getReportsByVin(String vin) {
        return reportRepository.findByVin(vin);
    }

    public List<Report> getReportsByUserId(String userId) {
        return reportRepository.findByUserId(userId);
    }
}

