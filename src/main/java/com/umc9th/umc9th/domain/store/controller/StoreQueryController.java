package com.umc9th.umc9th.domain.store.controller;


import com.umc9th.umc9th.domain.store.entity.Store;
import com.umc9th.umc9th.domain.store.service.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreQueryController {

    private final StoreQueryService storeQueryService;


    @GetMapping("/search")
    public List<Store> searchStore(
            @RequestParam("location") String location,
            @RequestParam("storeName") String storeName,
            @RequestParam("offset") int offset,
            @RequestParam("size") int size
    ){
        List<Store> stores = storeQueryService.searchStore(location, storeName, offset, size);
        return stores;
    }
}

