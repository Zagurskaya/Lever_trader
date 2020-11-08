package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.model.Trader;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TraderServiceImpl implements TraderService {

    private static final Logger logger = LogManager.getLogger(TraderServiceImpl.class);

    private final TraderConverter traderConverter;
    private final TraderRepository traderRepository;

    @Autowired
    public TraderServiceImpl(TraderConverter traderConverter, TraderRepository traderRepository) {
        this.traderConverter = traderConverter;
        this.traderRepository = traderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraderDTO> getTraders() {
        List<Trader> traders = traderRepository.findAll();
        List<TraderDTO> dtos = traders.stream()
                .map(traderConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public void add(TraderDTO traderDTO) {
        Trader trader = traderConverter.toEntity(traderDTO);
        traderRepository.save(trader);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Trader trader = traderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trader not found with id " + id));
        traderRepository.delete(trader);
    }

    @Override
    @Transactional(readOnly = true)
    public TraderDTO getTraderById(Long id) {
        Trader loaded = traderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trader not found with id " + id));
        return traderConverter.toDTO(loaded);
    }

}
