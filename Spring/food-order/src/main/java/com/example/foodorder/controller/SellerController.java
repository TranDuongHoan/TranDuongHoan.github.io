package com.example.foodorder.controller;

import com.example.foodorder.exception.SellerNotFoundException;
import com.example.foodorder.model.request.SearchSellerRequest;
import com.example.foodorder.model.request.SellerRequest;
import com.example.foodorder.model.response.SellerDetailResponse;
import com.example.foodorder.model.response.SellerResponse;
import com.example.foodorder.service.SellerService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class SellerController {
    private final SellerService sellerService;
    private final Gson gson;

    @GetMapping
    public String getSeller(Model model, SearchSellerRequest request) {
        SellerResponse sellerResponse = sellerService.searchSeller(request);

        model.addAttribute("sellers", sellerResponse.getSellers());
        model.addAttribute("requestSearch", request);
        model.addAttribute("currentPage", sellerResponse.getCurrentPage());
        model.addAttribute("totalPage", sellerResponse.getTotalPage());
        model.addAttribute("totalElement", sellerResponse.getTotalElement());
        model.addAttribute("pageSize", sellerResponse.getPageSize());
        return "seller/sellers";
    }

    @GetMapping("/sellers/{id}")
    public ResponseEntity<?> getSellerDetails(@PathVariable Long id) throws SellerNotFoundException {
        SellerDetailResponse seller = sellerService.getSellerDetails(id);
        return ResponseEntity.ok(seller);
    }

    @PostMapping("/sellers")
    public ResponseEntity<?> create(@RequestPart("sellerRequest") String sellerRequest,
                                    @RequestPart("image") MultipartFile image) {
        SellerRequest request = gson.fromJson(sellerRequest, SellerRequest.class);
        sellerService.create(request, image);
        return ResponseEntity.ok(null);
    }


    @PutMapping("/sellers")
    public ResponseEntity<?> update(@RequestBody SellerRequest request) {
        sellerService.save(request);
        return ResponseEntity.ok(null);
    }

}
