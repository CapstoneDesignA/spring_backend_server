package com.example.backend.store.repository;

import com.example.backend.store.vo.RequestPoint;
import com.example.backend.store.vo.Store;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class StoreInfoRepository implements StoreRepository {

    private final Map<Long, Store> names = new ConcurrentHashMap<>();
    private AtomicLong idMaker = new AtomicLong();

    public StoreInfoRepository() throws MalformedURLException {
        /**
         * 1. 카페 할아버지공장 : 성동구 성수2가제1동
         * 2. 안녕과자점 : 관악구 낙성대동
         * 3. 포옹남 : 성동구 송정동
         */
        names.put(
                idMaker.incrementAndGet(),
                new Store(1L, "카페 할아버지공장",
                        37.5367027777777, 127.0562,
                        new URL("https://pcmap.place.naver.com/restaurant/1425989301/home"),
                        new RequestPoint(61, 126)));
        names.put(
                idMaker.incrementAndGet(),
                new Store(2L, "안녕과자점",
                        37.4735, 126.960488888888,
                        new URL("https://pcmap.place.naver.com/restaurant/1709687194/home"),
                        new RequestPoint(60, 125)));
        names.put(
                idMaker.incrementAndGet(),
                new Store(3L, "포옹남 성수송정점",
                        37.5517277777777, 127.071708333333,
                        new URL("https://pcmap.place.naver.com/restaurant/1982400005/home"),
                        new RequestPoint(61, 126)));
    }

    @Override
    public Optional<Store> findStoreById(final Long id) {
        return Optional.of(names.get(id));
    }

    @Override
    public List<Store> findStores() {
        return names.values()
                .stream()
                .toList();
    }

}
