package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.Trader;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.springframework.stereotype.Component;

@Component
public class TraderConverterImpl implements TraderConverter {

    @Override
    public TraderDTO toDTO(Trader trader) {
        TraderDTO traderDTO = new TraderDTO();
        traderDTO.setId(trader.getId());
        traderDTO.setName(trader.getName());
        traderDTO.setApproved(trader.getApproved());
        return traderDTO;
    }

    @Override
    public Trader toEntity(TraderDTO traderDTO) {
        Trader trader = new Trader();
        trader.setId(traderDTO.getId());
        trader.setName(traderDTO.getName());
        trader.setApproved(traderDTO.getApproved());
        return trader;
    }
}
