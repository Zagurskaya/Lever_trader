package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.CommentRepository;
import com.gmail.zagurskaya.repository.model.Comment;
import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.converter.CommentConverter;
import com.gmail.zagurskaya.service.model.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

//    private final CommentConverter CommentConverter;
//    private final CommentRepository CommentRepository;
//
//    @Autowired
//    public CommentServiceImpl(CommentConverter CommentConverter, CommentRepository CommentRepository) {
//        this.CommentConverter = CommentConverter;
//        this.CommentRepository = CommentRepository;
//    }

    @Override
    @Transactional
    public List<CommentDTO> getComment() {
//        List<Comment> Comments = CommentRepository.findAll();
//        List<CommentDTO> CommentsDTO = Comments.stream()
//                .map(CommentConverter::toDTO)
//                .collect(Collectors.toList());
//        return CommentsDTO;
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

//        CommentRepository.remove(CommentRepository.findById(id));
    }

    @Override
    public void deleteCommentList(List<Long> ids) {
        ids.stream().forEach(id -> {
            delete(id);
            logger.info("deleted comment with id = " + id);
        });
    }
}
