package com.test.repository;

import com.test.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {
}
