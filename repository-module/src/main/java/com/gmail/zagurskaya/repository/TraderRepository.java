package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
    @Query(
            value = "SELECT round(AVG(mark),2) as rating FROM comment WHERE comment.trader_id = ?1",
            nativeQuery = true)
    Optional<BigDecimal> findRatingByTraderId(Long id);

    @Query(
            value = "SELECT trader.id, round(AVG(comment.mark),2) as rating FROM trader " +
                    "LEFT JOIN comment ON trader.id = comment.trader_id " +
                    "WHERE trader.approved = 1 " +
                    "GROUP BY trader.id",
            nativeQuery = true)
    List<Object[]> findAllRatingByApprovedTraders();

    @Query(
            value = "SELECT * FROM " +
                    "(SELECT trader.id as id, round(AVG(comment.mark),2) as rating " +
                    "FROM trader LEFT JOIN comment ON trader.id = comment.trader_id " +
                    "WHERE trader.approved = 1 " +
                    "GROUP BY trader.id " +
                    "ORDER BY `rating` DESC) traders " +
                    "LIMIT ?1",
            nativeQuery = true)
    List<Object[]> findTopRatingTraders(int i);

    List<Trader> findAllByApproved(boolean approved);

    @Query("select t from Trader t where t.id in :ids")
    List<Trader> findByTraderIds(@Param("ids") Iterable<Long> ids);

}
