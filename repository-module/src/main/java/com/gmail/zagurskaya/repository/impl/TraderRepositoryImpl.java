package com.gmail.zagurskaya.repository.impl;

import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.model.Trader;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TraderRepositoryImpl extends GenericRepositoryImpl<Long, Trader> implements TraderRepository {

}
