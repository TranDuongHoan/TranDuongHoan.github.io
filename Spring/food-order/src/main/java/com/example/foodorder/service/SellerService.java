package com.example.foodorder.service;

import com.example.foodorder.entity.Seller;
import com.example.foodorder.exception.SellerNotFoundException;
import com.example.foodorder.model.request.SearchSellerRequest;
import com.example.foodorder.model.request.SellerRequest;
import com.example.foodorder.model.response.SellerDetailResponse;
import com.example.foodorder.model.response.SellerResponse;
import com.example.foodorder.repository.SellerJpaRepository;
import com.example.foodorder.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class SellerService {
    private final SellerJpaRepository sellerJpaRepository;
    private final SellerRepository sellerRepository;


    public List<SellerDetailResponse> getAll() {
        List<Seller> sellers = sellerJpaRepository.findAll();
        return sellers.stream().map(p ->
                SellerDetailResponse.builder()
                        .id(p.getId())
                        .shopName(p.getShopName())
                        .address(p.getAddress())
                        .image(p.getImage())
                        .rating(p.getRating())
                        .build()
        ).collect(Collectors.toList());
    }

    public SellerDetailResponse getSellerDetails(Long id) throws SellerNotFoundException {
        return sellerJpaRepository.findById(id).map(
                product -> SellerDetailResponse.builder()
                        .id(product.getId())
                        .shopName(product.getShopName())
                        .address(product.getAddress())
                        .image(product.getImage())
                        .rating(product.getRating())
                        .build()
        ).orElseThrow(() -> new SellerNotFoundException("Seller with id " + id + " could not be found"));
    }

    public void save(SellerRequest request) {
        Seller seller = Seller.builder()
                .shopName(request.getShopName())
                .address(request.getAddress())
                .image(request.getImage())
                .rating(request.getRating())
                .build();
        if (!ObjectUtils.isEmpty(request.getId())) {
            Optional<Seller> studentOptional = sellerJpaRepository.findById(request.getId());
            Seller sellerNeedUpdate = studentOptional.get();
            sellerNeedUpdate.setShopName(request.getShopName());
            sellerNeedUpdate.setAddress(request.getAddress());

            sellerNeedUpdate.setImage(request.getImage());
            sellerNeedUpdate.setRating(request.getRating());
            sellerJpaRepository.save(sellerNeedUpdate);
            return;
        }
        sellerJpaRepository.save(seller);
    }

    public void create(SellerRequest request, MultipartFile image) {
        //lưu ảnh
        String filePath = "seller_images" + File.separator + image.getOriginalFilename();
        try {
            Files.copy(image.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Seller seller = Seller.builder()
                .shopName(request.getShopName())
                .address(request.getAddress())
                .image(image.getOriginalFilename())
                .rating(request.getRating())
                .build();
        sellerJpaRepository.save(seller);
    }

    public SellerResponse searchSeller(SearchSellerRequest request) {
        List<SellerDetailResponse> data = sellerRepository.searchSeller(request);
        Long totalElement = 0L;
        if (!CollectionUtils.isEmpty(data)) {
            totalElement = data.get(0).getTotalRecord();
        }

        double totalPageTemp = (double) totalElement / request.getPageSize();
        if (totalPageTemp > totalElement / request.getPageSize()) {
            totalPageTemp++;
        }
        return SellerResponse.builder()
                .sellers(data)
                .totalElement(totalElement)
                .totalPage(Double.valueOf(totalPageTemp).intValue())
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();

    }



}
