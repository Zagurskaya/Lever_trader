package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.Trader;
import com.gmail.zagurskaya.service.model.TraderDTO;

public interface TraderConverter {

    TraderDTO toDTO(Trader trader);

    Trader toEntity(TraderDTO traderDTO);

}
