package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(TraderController.class);
    private static final Long GUEST_ID = 2L;
    private final TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;
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
        List<CommentDTO> commentDTOList = traderService.getCommentsByTraderId(id);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}
