package com.bartemnius.vehiclefleet.vehicleservice.entity;

import com.bartemnius.vehiclefleet.vehicleservice.utils.VehicleStatus;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String vin;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private String model;

  @Column(nullable = false)
  private int year;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private VehicleStatus status;

  @Column(nullable = true)
  private UUID userId;
}
