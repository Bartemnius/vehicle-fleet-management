package com.bartemnius.vehiclefleet.report_service.repository;

import com.bartemnius.vehiclefleet.report_service.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReportRepository extends MongoRepository<Report, String> {
}
