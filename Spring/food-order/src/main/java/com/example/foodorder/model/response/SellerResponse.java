package com.example.foodorder.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse {

    private List<SellerDetailResponse> sellers;

    private Long totalElement;

    private int totalPage;

    private int currentPage;

    private int pageSize;



}
