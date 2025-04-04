package com.bartemnius.vehiclefleet.report_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    private String id;
    private String vin;
    private String userId;
    private String action;
    private LocalDateTime timestamp;
}
