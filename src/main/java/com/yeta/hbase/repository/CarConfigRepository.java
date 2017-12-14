/*
package com.yeta.hbase.repository;

import com.yeta.hbase.model.CarConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

*/
/**
 * Created by Administrator on 2017-12-11.
 *//*

public interface CarConfigRepository extends JpaRepository<CarConfig, Integer> {

    List<CarConfig> findAllByPriceLike(String like);

    List<CarConfig> findAllByCarSeatsLike(String like);

    @Query("SELECT brand FROM CarConfig GROUP BY brand")
    List<String> findAllBrandGroupByBrand();

    @Query("SELECT price FROM CarConfig GROUP BY price")
    List<String> findAllPriceGroupByPrice();

    @Query("SELECT carSeats FROM CarConfig GROUP BY carSeats")
    List<String> findAllCarSeatsGroupByCarSeats();

}
*/
