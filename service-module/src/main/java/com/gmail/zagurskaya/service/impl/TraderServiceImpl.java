package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.model.Trader;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TraderServiceImpl implements TraderService {

    private static final Logger logger = LogManager.getLogger(TraderServiceImpl.class);

    private final TraderConverter traderConverter;
    private final TraderRepository traderRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TraderServiceImpl(TraderConverter traderConverter, TraderRepository traderRepository, PasswordEncoder passwordEncoder) {
        this.traderConverter = traderConverter;
        this.traderRepository = traderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraderDTO> getTraders() {
        List<Trader> traders = traderRepository.findAll(0, Integer.MAX_VALUE);
        List<TraderDTO> dtos = traders.stream()
                .map(traderConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public void add(TraderDTO traderDTO) {
        Trader trader = traderConverter.toEntity(traderDTO);
        traderRepository.persist(trader);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Trader trader = traderRepository.findById(id);
        trader.setIsNotActive(true);
        traderRepository.merge(trader);
    }

    @Override
    @Transactional
    public void update(TraderDTO traderDTO) {
        Trader trader = traderConverter.toEntity(traderDTO);
        traderRepository.merge(trader);
    }

    @Override
    @Transactional(readOnly = true)
    public TraderDTO getTraderById(Long id) {
        Trader loaded = (Trader) traderRepository.findById(id);
        TraderDTO traderDTO = traderConverter.toDTO(loaded);
        return traderDTO;
    }

}
