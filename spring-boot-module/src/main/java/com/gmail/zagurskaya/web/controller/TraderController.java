package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.TraderDTO;
import com.gmail.zagurskaya.web.validator.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.gmail.zagurskaya.web.constant.URLConstant.API_TRADER;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_TRADER_COMMENTS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_TRADER_ID;

@RestController
@RequestMapping(API_TRADER)
public class TraderController {

    private final TraderService traderService;
    private final CommentService commentService;

    @Autowired
    public TraderController(TraderService traderService, CommentService commentService) {
        this.traderService = traderService;
        this.commentService = commentService;
    }

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<TraderDTO>> getTraders() {
        List<TraderDTO> traderDTOList = traderService.getTraders();
        return new ResponseEntity<>(traderDTOList, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity saveTrader(@RequestBody @Valid TraderDTO traderDTO) {
        traderService.add(traderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = API_TRADER_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<TraderDTO> getTrader(@PathVariable("id") Long id) {
        TraderDTO traderDTO = traderService.getTraderById(id);
        return new ResponseEntity<>(traderDTO, HttpStatus.OK);
    }

    @GetMapping(
            value = API_TRADER_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<CommentDTO>> getCommentsByTraderId(@PathVariable("id") Long id) {
        List<CommentDTO> commentDTOList = commentService.getCommentsByTraderId(id);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping(
            value = API_TRADER_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> saveTraderComment(@PathVariable("id") Long id, @RequestBody @Valid CommentDTO commentDTO) {
        if (!DateValidator.isMarkValid(commentDTO.getMark())) {
            return new ResponseEntity<>("Invalid mark form", HttpStatus.BAD_REQUEST);
        }
        commentDTO.setTraderId(id);
        commentService.add(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
