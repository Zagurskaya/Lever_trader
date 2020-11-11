package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.CommentRepository;
import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.model.Trader;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TraderServiceImpl implements TraderService {
    private final TraderConverter traderConverter;
    private final TraderRepository traderRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public TraderServiceImpl(TraderConverter traderConverter, TraderRepository traderRepository, CommentRepository commentRepository) {
        this.traderConverter = traderConverter;
        this.traderRepository = traderRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraderDTO> getTraders() {
        List<Object[]> list = traderRepository.findAllRatingByApprovedTraders();
        Map<Long, BigDecimal> mapTraderIdAndRating = listObjectToMap(list);
        return getTraderDTOListByMapTraderIdAndRating(mapTraderIdAndRating);
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
        TraderDTO traderDTO = traderConverter.toDTO(loaded);
        Optional<BigDecimal> rating = traderRepository.findRatingByTraderId(traderDTO.getId());
        traderDTO.setRating(rating.orElse(new BigDecimal(0)));
        return traderDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraderDTO> findTopRatingTraders(int topLimit) {
        List<Object[]> topRatingTraders = traderRepository.findTopRatingTraders(topLimit);
        Map<Long, BigDecimal> mapTopTraderIdAndRating = listObjectToMap(topRatingTraders);
        return getTraderDTOListByMapTraderIdAndRating(mapTopTraderIdAndRating);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TraderDTO> getNewTraders() {
        List<Trader> traders = traderRepository.findAllByApproved(false);
        List<TraderDTO> dtos = traders.stream()
                .map(traderConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void approveTraderById(Long id) {
        Trader trader = traderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trader not found with id " + id));
        trader.setApproved(true);
        traderRepository.save(trader);
    }

    private List<TraderDTO> getTraderDTOListByMapTraderIdAndRating(Map<Long, BigDecimal> mapTraderIdAndRating) {
        Set<Long> keyList = mapTraderIdAndRating.keySet();
        List<Trader> traders = traderRepository.findByTraderIds(keyList);
        List<TraderDTO> dtos = traders.stream()
                .map(traderConverter::toDTO)
                .collect(Collectors.toList());
        dtos.forEach(traderDTO -> traderDTO.setRating(mapTraderIdAndRating.get(traderDTO.getId())));
        return dtos;
    }

    private Map<Long, BigDecimal> listObjectToMap(List<Object[]> list) {
        Map<Long, BigDecimal> map = new HashMap<>();
        for (Object[] ob : list) {
            Long key = ((BigInteger) ob[0]).longValue();
            BigDecimal value = (BigDecimal) ob[1];
            map.put(key, value);
        }
        return map;
    }
}
