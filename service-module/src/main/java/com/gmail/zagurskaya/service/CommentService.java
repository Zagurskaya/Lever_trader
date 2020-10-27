package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComment();

    void delete(Long id);

    void deleteCommentList(List<Long> ids);
}
