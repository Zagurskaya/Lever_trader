package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsByTraderId(Long traderId);

    void delete(Long id);

    void add(CommentDTO commentDTO);

    void update(CommentDTO commentDTO);

    List<CommentDTO> getNewComments();

    CommentDTO getCommentById(Long id);

    List<CommentDTO> getCommentByUserId(Long userId);

    void approveComment(Long id);
}
