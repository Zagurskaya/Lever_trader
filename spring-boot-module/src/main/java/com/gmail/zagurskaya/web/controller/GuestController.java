package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.TraderDTO;
import com.gmail.zagurskaya.web.request.NewTraderWithCommentForm;
import com.gmail.zagurskaya.web.util.MessageUtil;
import com.gmail.zagurskaya.web.validator.CommentValidator;
import com.gmail.zagurskaya.web.validator.NewTraderWithCommentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.gmail.zagurskaya.web.constant.URLConstant.API_GUEST_TRADERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_GUEST_TRADERS_COMMENTS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_GUEST_TRADERS_TOP;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_GUEST_TRADER_COMMENTS;
import static com.gmail.zagurskaya.web.constant.URLConstant.API_GUEST_TRADER_ID;

@RestController
@RequestMapping(API_GUEST_TRADERS)
public class GuestController {
    private static final int TOP_LIMIT = 5;

    private final TraderService traderService;
    private final CommentService commentService;
    private final NewTraderWithCommentFormValidator newTraderWithCommentFormValidator;
    private final CommentValidator commentValidator;

    @Autowired
    public GuestController(TraderService traderService, CommentService commentService, NewTraderWithCommentFormValidator newTraderWithCommentFormValidator, CommentValidator commentValidator) {
        this.traderService = traderService;
        this.commentService = commentService;
        this.newTraderWithCommentFormValidator = newTraderWithCommentFormValidator;
        this.commentValidator = commentValidator;
    }

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<TraderDTO>> getTraders() {
        List<TraderDTO> traderDTOList = traderService.getTraders();
        return new ResponseEntity<>(traderDTOList, HttpStatus.OK);
    }

    @GetMapping(
            value = API_GUEST_TRADERS_TOP,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<TraderDTO>> getTopTraders() {
        List<TraderDTO> traderDTOList = traderService.findTopRatingTraders(TOP_LIMIT);
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
            value = API_GUEST_TRADER_ID,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<TraderDTO> getTrader(@PathVariable("id") Long id) {
        TraderDTO traderDTO = traderService.getTraderById(id);
        return new ResponseEntity<>(traderDTO, HttpStatus.OK);
    }

    @GetMapping(
            value = API_GUEST_TRADER_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<List<CommentDTO>> getCommentsByTraderId(@PathVariable("id") Long id) {
        List<CommentDTO> commentDTOList = commentService.getCommentsByTraderId(id);
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping(
            value = API_GUEST_TRADER_COMMENTS,
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
        commentDTO.setTraderId(id);
        commentService.add(commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(
            value = API_GUEST_TRADERS_COMMENTS,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> saveNewTraderWithComment(@RequestBody @Valid NewTraderWithCommentForm newTraderWithCommentForm, BindingResult result) {

        newTraderWithCommentFormValidator.validate(newTraderWithCommentForm, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(MessageUtil.getValidationErrorMessage(result), HttpStatus.BAD_REQUEST);
        }
        TraderDTO traderDTO = new TraderDTO();
        traderDTO.setName(newTraderWithCommentForm.getName());
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setMessage(newTraderWithCommentForm.getMessage());
        commentDTO.setMark(newTraderWithCommentForm.getMark());
        traderService.add(traderDTO, commentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
