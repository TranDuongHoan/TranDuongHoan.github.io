package com.example.foodorder.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerDetailResponse {

    private Long id;

    private String shopName;

    private String address;

    private String image;

    private String rating;

    private Long totalRecord;
}
