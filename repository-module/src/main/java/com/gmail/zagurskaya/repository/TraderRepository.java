package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> {
}
