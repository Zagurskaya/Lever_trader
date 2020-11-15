package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.service.util.UserUtil;
import com.gmail.zagurskaya.web.util.MessageUtil;
import com.gmail.zagurskaya.web.validator.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.gmail.zagurskaya.web.constant.URLConstant.API_USER;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_USER_COMMENTS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_USER_COMMENT_ID;

@RestController
@RequestMapping(API_USER)
public class UserController {

    private final CommentService commentService;
    private final UserUtil userUtil;
    private final CommentValidator commentValidator;

    @Autowired
    public UserController(CommentService commentService, UserUtil userUtil, CommentValidator commentValidator) {
        this.commentService = commentService;
        this.userUtil = userUtil;
        this.commentValidator = commentValidator;
    }

    @GetMapping(
            value = API_USER_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<CommentDTO>> getCommentsByUser(@PathVariable("id") Long id) {
        List<CommentDTO> commentDTOList = commentService.getCommentByUserId(id);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping(
            value = API_USER_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> saveTraderComment(@PathVariable("id") Long id,
                                                    @RequestBody @Valid CommentDTO commentDTO,
                                                    BindingResult result) {
        commentValidator.validate(commentDTO, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(MessageUtil.getValidationErrorMessage(result), HttpStatus.BAD_REQUEST);
        }

        UserDTO user = userUtil.getActualUser();
        if (user.getId() != id) {
            return new ResponseEntity<>("Incorrect access", HttpStatus.BAD_REQUEST);
        }

        commentDTO.setUserId(id);
        commentService.add(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = API_USER_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("idc") Long commentId, @PathVariable("id") Long userId) {
        CommentDTO comment = commentService.getCommentById(commentId);
        if (comment.getUserId() != userId) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping(
            value = API_USER_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> deleteComment(@PathVariable("idc") Long commentId, @PathVariable("id") Long userId) {
        CommentDTO comment = commentService.getCommentById(commentId);
        if (comment.getUserId() != userId) {
            return new ResponseEntity<>("The comment does not belong to the user", HttpStatus.BAD_REQUEST);
        }
        commentService.delete(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(
            value = API_USER_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> updateComment(@PathVariable("idc") Long commentId,
                                                @PathVariable("id") Long userId,
                                                @RequestBody @Valid CommentDTO commentDTO) {
        UserDTO user = userUtil.getActualUser();
        if (user.getId() != userId) {
            return new ResponseEntity<>("Incorrect access", HttpStatus.BAD_REQUEST);
        }
        commentDTO.setUserId(userId);
        commentDTO.setId(commentId);
        commentService.update(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
