package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gmail.zagurskaya.web.constant.URLConstant.API_ADMIN;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_ADMIN_NEW_COMMENTS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_ADMIN_NEW_COMMENT_ID;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_ADMIN_NEW_TRADERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_ADMIN_NEW_TRADER_ID;

@RestController
@RequestMapping(API_ADMIN)
public class AdministratorController {

    private final TraderService traderService;
    private final CommentService commentService;

    @Autowired
    public AdministratorController(TraderService traderService, CommentService commentService) {
        this.traderService = traderService;
        this.commentService = commentService;
    }


    @GetMapping(
            value = API_ADMIN_NEW_TRADERS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<TraderDTO>> getNewTraders() {
        List<TraderDTO> traderDTOList = traderService.getNewTraders();
        return new ResponseEntity<>(traderDTOList, HttpStatus.OK);
    }

    @GetMapping(
            value = API_ADMIN_NEW_TRADER_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<TraderDTO> getTrader(@PathVariable("id") Long id) {
        TraderDTO traderDTO = traderService.getTraderById(id);
        if (traderDTO.getApproved()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(traderDTO, HttpStatus.OK);
    }

    @PostMapping(
            value = API_ADMIN_NEW_TRADER_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity approveTrader(@PathVariable("id") Long id) {
        traderService.approveTraderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(
            value = API_ADMIN_NEW_TRADER_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity deleteTrader(@PathVariable("id") Long id) {
        traderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = API_ADMIN_NEW_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<CommentDTO>> getNewComments() {
        List<CommentDTO> commentDTOList = commentService.getNewComments();
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @GetMapping(
            value = API_ADMIN_NEW_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id) {
        CommentDTO commentDTO = commentService.getCommentById(id);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping(
            value = API_ADMIN_NEW_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity approveComment(@PathVariable("id") Long id) {
        commentService.approveComment(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(
            value = API_ADMIN_NEW_COMMENT_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity deleteComment(@PathVariable("id") Long id) {
        commentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
