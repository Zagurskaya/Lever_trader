package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

//    private static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
//
//    private final UserService userService;
//    private final RateNBService rateNBService;
//    private final RateCBService rateCBService;
//    private final CurrencyService currencyService;
//
//    @Autowired
//    public RestApiController(UserService userService, RateNBService rateNBService, RateCBService rateCBService, CurrencyService currencyService) {
//        this.userService = userService;
//        this.rateNBService = rateNBService;
//        this.rateCBService = rateCBService;
//        this.currencyService = currencyService;
//    }
//
//    @GetMapping(
//            value = "/currency",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<List<CurrencyDTO>> getCurrencies() {
//        List<CurrencyDTO> currencies = currencyService.getCurrencies();
//        return new ResponseEntity<>(currencies, HttpStatus.OK);
//    }
//
//    @GetMapping(
//            value = "/ratenb",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<List<RateNBDTO>> getRatesNB() {
//        List<RateNBDTO> ratesNB = rateNBService.getRatesNB();
//        return new ResponseEntity<>(ratesNB, HttpStatus.OK);
//    }
//
//    @GetMapping(
//            value = "/ratecb",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<List<RateCBDTO>> getRatesCB() {
//        List<RateCBDTO> ratesCB = rateCBService.getRatesCB();
//        return new ResponseEntity<>(ratesCB, HttpStatus.OK);
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity saveUser(@RequestBody @Valid UserDTO user) {
//        user.setIsNotActive(false);
//        userService.add(user);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping("/currency")
//    public ResponseEntity saveCurrency(@RequestBody @Valid CurrencyDTO currencyDTO) {
//        currencyService.add(currencyDTO);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping("/ratenb")
//    public ResponseEntity saveRateNB(@RequestBody @Valid List<RateNBDTO> rateNBDTOList) {
//        rateNBService.add(rateNBDTOList);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @PostMapping("/ratecb")
//    public ResponseEntity saveRateCB(@RequestBody @Valid List<RateCBDTO> rateCBDTOList) {
//        rateCBService.add(rateCBDTOList);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping(
//            value = "/currency/{id}",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<CurrencyDTO> getCurrency(@PathVariable("id") Long id) {
//        CurrencyDTO currencyDTO = currencyService.getCurrency(id);
//        return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(
//            value = "/currency/{id}"
//    )
//    public ResponseEntity deleteCurrency(@PathVariable("id") Long id) {
//        currencyService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(
//            value = "/ratenb/{id}",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<RateNBDTO> getRateNB(@PathVariable("id") Long id) {
//        RateNBDTO rateNBDTO = rateNBService.getRateNB(id);
//        return new ResponseEntity<>(rateNBDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(
//            value = "/ratenb/{id}"
//    )
//    public ResponseEntity deleteRateNB(@PathVariable("id") Long id) {
//        rateNBService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping(
//            value = "/ratecb/{id}",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    public ResponseEntity<RateCBDTO> getRateCB(@PathVariable("id") Long id) {
//        RateCBDTO rateCBDTO = rateCBService.getRateCB(id);
//        return new ResponseEntity<>(rateCBDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(
//            value = "/ratecb/{id}"
//    )
//    public ResponseEntity deleteRateCB(@PathVariable("id") Long id) {
//        rateCBService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
