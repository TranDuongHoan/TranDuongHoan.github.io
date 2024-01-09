package com.example.foodorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userfavorites")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserFavorite extends BaseEntity{

}
