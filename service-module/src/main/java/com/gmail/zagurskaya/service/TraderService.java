package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.TraderDTO;

import java.util.List;

public interface TraderService {

    List<TraderDTO> getTraders();

    void add(TraderDTO trader);

    void delete(Long id);

    void update(TraderDTO trader);

    TraderDTO getTraderById(Long id);
}
