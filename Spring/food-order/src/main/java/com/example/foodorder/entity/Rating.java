package com.example.foodorder.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ratings")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Rating extends BaseEntity{

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    User user;

    @Column
    String comment;

    @Column
    Integer ratedStar;


}
