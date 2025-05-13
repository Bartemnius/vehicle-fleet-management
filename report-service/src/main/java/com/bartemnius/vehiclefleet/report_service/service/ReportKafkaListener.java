package com.bartemnius.vehiclefleet.report_service.service;

import com.bartemnius.vehiclefleet.report_service.dto.ReportEventDto;
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
    public void handleVehicleEvent(ReportEventDto event) {
        log.info("Received event: {}", event);

        Report report = new Report();
        report.setVin(event.getVin());
        report.setUserId(event.getUserId());
        report.setAction(event.getAction());
        report.setTimestamp(event.getTimestamp());

        reportRepository.save(report);
    }
}
