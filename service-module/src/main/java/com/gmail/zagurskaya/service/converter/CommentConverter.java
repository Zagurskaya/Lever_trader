package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.Comment;
import com.gmail.zagurskaya.service.model.CommentDTO;

public interface CommentConverter {

    CommentDTO toDTO(Comment Comment);

    Comment toEntity(CommentDTO CommentDTO);
}
