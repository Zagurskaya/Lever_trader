package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.TraderRepository;
import com.gmail.zagurskaya.repository.UserRepository;
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
    public CommentDTO toDTO(Comment Comment) {
        CommentDTO CommentDTO = new CommentDTO();
        CommentDTO.setId(Comment.getId());
        CommentDTO.setMessage(Comment.getMessage());
        CommentDTO.setCreatedDate(Comment.getCreatedDate());
        CommentDTO.setUser(userConverter.toDTO(Comment.getUser()));
        CommentDTO.setTrader(traderConverter.toDTO(Comment.getTrader()));
        CommentDTO.setApproved(Comment.getApproved());
        return CommentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO CommentDTO) {
        Comment Comment = new Comment();
        Comment.setId(CommentDTO.getId());
        Comment.setMessage(CommentDTO.getMessage());
        Comment.setCreatedDate(CommentDTO.getCreatedDate());
        Comment.setUser(userRepository.findById(CommentDTO.getUser().getId()));
        Comment.setTrader(traderRepository.findById(CommentDTO.getTrader().getId()));
        Comment.setApproved(CommentDTO.getApproved());
        return Comment;
    }
}
