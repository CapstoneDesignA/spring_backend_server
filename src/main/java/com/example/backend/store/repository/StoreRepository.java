package com.example.backend.store.repository;

import com.example.backend.store.vo.Store;
import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    Optional<Store> findStoreById(Long id);

    List<Store> findStores();

}
