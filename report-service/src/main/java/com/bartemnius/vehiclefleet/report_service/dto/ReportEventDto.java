package com.bartemnius.vehiclefleet.report_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEventDto {
    private String vin;
    private String userId;
    private String action;
    private LocalDateTime timestamp;
}
