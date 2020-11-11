package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.CommentRepository;
import com.gmail.zagurskaya.repository.model.Comment;
import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.converter.CommentConverter;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Long GUEST_ID = 2L;

    private final CommentConverter commentConverter;
    private final CommentRepository commentRepository;
    private final UserUtil userUtil;


    @Autowired
    public CommentServiceImpl(CommentConverter commentConverter, CommentRepository commentRepository, UserUtil userUtil) {
        this.commentConverter = commentConverter;
        this.commentRepository = commentRepository;
        this.userUtil = userUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByTraderId(Long traderId) {
        List<Comment> commentList = commentRepository.findAllByTraderIdAndApproved(traderId, true);
        return commentList.stream()
                .map(commentConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + id));
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void add(CommentDTO commentDTO) {
        Long userId = userUtil.getActualUserId();
        commentDTO.setUserId(userId != 0 ? userId : GUEST_ID);
        Comment comment = commentConverter.toEntity(commentDTO);

        comment.setCreatedDate(LocalDate.now());
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getNewComments() {
        List<Comment> commentList = commentRepository.findAllByApproved(false);
        return commentList.stream()
                .map(commentConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDTO getCommentById(Long id) {
        Comment comment = commentRepository.findAllById(id);
        return commentConverter.toDTO(comment);
    }

    @Override
    @Transactional
    public void approveComment(Long id) {
        Comment comment = commentRepository.findAllById(id);
        comment.setApproved(true);
        commentRepository.save(comment);
    }

}
