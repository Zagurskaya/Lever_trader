package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByTraderId(Long traderId);

    void add(CommentDTO commentDTO);

}
