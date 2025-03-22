package com.bartemnius.vehiclefleet.report_service.repository;

import com.bartemnius.vehiclefleet.report_service.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByVin(String vin);
    List<Report> findByUserId(String userId);
}
