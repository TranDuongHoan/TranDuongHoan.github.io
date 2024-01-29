package com.example.foodorder.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequest {
    Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Name must be less than 255 characters")
    String shopName;

    @NotNull(message = "Address is required")
    @Length(max = 255, message = "Address must be less than 255 characters")
    String address;

    @NotEmpty(message = "Image is required")
    String image;

    @NotEmpty(message = "Rating is required")
    String rating;


}
