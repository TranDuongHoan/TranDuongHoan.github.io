package com.example.foodorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopopeningtimes")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ShopOpeningTime extends BaseEntity{

    LocalDate fromDay;

    LocalDate toDay;

    LocalTime fromTime;

    LocalTime toTime;
}
