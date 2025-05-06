package com.bartemnius.vehiclefleet.report_service.service;

import com.bartemnius.vehiclefleet.report_service.model.Report;
import com.bartemnius.vehiclefleet.report_service.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportKafkaListener {
    private final ReportRepository reportRepository;

    @KafkaListener(topics = "vehicle-events", groupId = "${spring.kafka.consumer.group-id}")
    public void handleVehicleEvent(Map<String, Object> event) {
        log.info("Received event: {}", event);

        Report report = new Report();
        report.setVin((String) event.get("vin"));
        report.setUserId((String) event.get("userId"));
        report.setAction((String) event.get("action"));
        report.setTimestamp(LocalDateTime.parse((String) event.get("timestamp")));

        reportRepository.save(report);
    }
}
