package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTraderIdAndApproved(Long traderId, boolean i);

    @Query(
            value = "SELECT round(AVG(`mark`),2) as rating FROM `comment` WHERE `comment`.`trader_id` = ?1",
//            mail = "SELECT round(AVG(c.mark),2) FROM Comment c WHERE c.trader.id = ?1",
            nativeQuery = true)
    Optional<Double> findRatingByTraderId(Long id);
}
