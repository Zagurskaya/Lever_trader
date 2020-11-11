package com.gmail.zagurskaya.repository;

import com.gmail.zagurskaya.repository.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByTraderIdAndApproved(Long traderId, boolean i);

    List<Comment> findAllByApproved(boolean i);

    Comment findAllById(Long id);

}
