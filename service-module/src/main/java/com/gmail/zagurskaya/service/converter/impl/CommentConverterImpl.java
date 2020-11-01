package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.Comment;
import com.gmail.zagurskaya.service.converter.CommentConverter;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverterImpl implements CommentConverter {

    private final UserConverter userConverter;
    private final TraderConverter traderConverter;

    @Autowired
    public CommentConverterImpl(UserConverter userConverter, TraderConverter traderConverter) {
        this.userConverter = userConverter;
        this.traderConverter = traderConverter;
    }

    @Override
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setUser(userConverter.toDTO(comment.getUser()));
        commentDTO.setTrader(traderConverter.toDTO(comment.getTrader()));
        commentDTO.setApproved(comment.getApproved());
        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setMessage(commentDTO.getMessage());
        comment.setCreatedDate(commentDTO.getCreatedDate());
        comment.setUser(userConverter.toEntity(commentDTO.getUser()));
        comment.setTrader(traderConverter.toEntity(commentDTO.getTrader()));
        comment.setApproved(commentDTO.getApproved());
        return comment;
    }
}
