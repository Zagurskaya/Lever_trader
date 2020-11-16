package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.TraderDTO;

import java.util.List;

public interface TraderService {

    List<TraderDTO> getTraders();

    Long add(TraderDTO trader);

    Long add(TraderDTO trader, CommentDTO commentDTO);

    void delete(Long id);

    TraderDTO getTraderById(Long id);

    List<TraderDTO> findTopRatingTraders(int topLimit);


    List<TraderDTO> getNewTraders();

    void approveTraderById(Long id);
}
