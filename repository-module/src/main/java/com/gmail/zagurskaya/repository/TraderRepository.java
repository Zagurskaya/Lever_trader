package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {

    @Query(
            value = "SELECT `trader`.`id` as id , `trader`.`name` as name, round(AVG(`comment`.`mark`),2) as rating FROM `trader` " +
                    "LEFT JOIN `comment` ON `trader`.`id`= `comment`.`trader_id` " +
                    "GROUP BY `trader`.`id` " +
                    "ORDER BY rating " +
                    "DESC LIMIT ?1",
            nativeQuery = true)
    List<Trader> findTopRatingTraders(int i);
}
