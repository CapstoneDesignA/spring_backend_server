package com.example.backend.store.presentation;

import com.example.backend.store.dto.StoreResponse;
import com.example.backend.store.repository.StoreRepository;
import com.example.backend.store.vo.Store;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreRepository repository;

    public StoreController(final StoreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<StoreResponse> getStoreInfos() {
        return repository.findStores()
                .stream()
                .map(Store::toStoreResponse)
                .toList();
    }

}
