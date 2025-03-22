package com.bartemnius.vehiclefleet.report_service.controller;

import com.bartemnius.vehiclefleet.report_service.model.Report;
import com.bartemnius.vehiclefleet.report_service.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/vehicle/{vin}")
    public ResponseEntity<List<Report>> getReportsByVehicle(@PathVariable String vin) {
        return ResponseEntity.ok(reportService.getReportsByVin(vin));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(reportService.getReportsByUserId(userId));
    }
}

