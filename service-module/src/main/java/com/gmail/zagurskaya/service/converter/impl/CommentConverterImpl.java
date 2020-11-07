package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.Comment;
import com.gmail.zagurskaya.service.converter.CommentConverter;
import com.gmail.zagurskaya.service.converter.TraderConverter;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class CommentConverterImpl implements CommentConverter {

    private final UserConverter userConverter;
    private final TraderConverter traderConverter;
    private final UserRepository userRepository;
    private final TraderRepository traderRepository;

    @Autowired
    public CommentConverterImpl(UserConverter userConverter, TraderConverter traderConverter, UserRepository userRepository, TraderRepository traderRepository) {
        this.userConverter = userConverter;
        this.traderConverter = traderConverter;
        this.userRepository = userRepository;
        this.traderRepository = traderRepository;
    }

    @Override
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setCreatedDate(comment.getCreatedDate());
        commentDTO.setUser(userConverter.toDTO(comment.getUser()));
        commentDTO.setTrader(traderConverter.toDTO(comment.getTrader()));
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setTraderId(comment.getTrader().getId());
        commentDTO.setMark(comment.getMark());
        commentDTO.setApproved(comment.getApproved());
        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setMessage(commentDTO.getMessage());
        comment.setCreatedDate(commentDTO.getCreatedDate());
        comment.setUser(userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        comment.setTrader(traderRepository.findById(commentDTO.getTraderId())
                .orElseThrow(() -> new EntityNotFoundException("Trader not found ")));
        comment.setMark(commentDTO.getMark());
        comment.setApproved(commentDTO.getApproved());
        return comment;
    }
}
