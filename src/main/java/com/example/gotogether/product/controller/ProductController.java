package com.example.gotogether.product.controller;

import com.example.gotogether.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"상품 서비스"}, description = "상품 키워드 검색, 카테고리로 상품 검색)")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/categories/{categoryId}")
    @ApiOperation(value = "카테고리로 상품 검색", notes = "해당 카테고리와 무한 하위 카테고리까지 관련된 상품 반환.\n" +
            "code: 200 상품 목록 조회 성공, 204 표시할 상품 없음, 400 잘못된 페이지 사이즈 요청, 404 해당 카테고리가 존재하지 않음. 500 서버에러 ")
    public ResponseEntity<?> findProductByCategory(@PathVariable Long categoryId, @RequestParam(required = false, defaultValue = "1") int page) {
        return productService.findProductByCategory(categoryId, page);
    }

    @GetMapping("/search")
    @ApiOperation(value = "키워드로 상품 검색", notes = "해당 키워드와 관련된 상품 반환. 요청시 sort 는 asc(낮은 가격 순), desc(높은 가격 순), recent 또는 안주기(required X)-> 최신순 \n" +
            "code: 200 상품 목록 조회 성공, 204 표시할 상품 없음, 400 잘못된 페이지 사이즈 요청, 500 서버에러 ")
    public ResponseEntity<?> searchProductByKeyword(@RequestParam(required = true) String keyword,@RequestParam(required = false,defaultValue = "recent") String sort,@RequestParam(required = false,defaultValue = "1") int page){
        return productService.findProductByKeyword(keyword,page,sort);
    }

    @GetMapping("/popular")
    @ApiOperation(value = "인기 상품 검색(전체 or 특정 카테고리 가능)", notes = "categoryID 를 받을경우 그 카테고리의 인기순 상품 10개 제공.없을시 전체 상품 중 인기순 10개 제공. \n" +
            "code: 200 상품 목록 조회 성공, 204 표시할 상품 없음, 400 잘못된 카테고리 요청, 500 서버에러 ")
    public ResponseEntity<?> findPopularProducts(@RequestParam(required = false) Long categoryId){
        return productService.findPopularProducts(categoryId);
    }
}
