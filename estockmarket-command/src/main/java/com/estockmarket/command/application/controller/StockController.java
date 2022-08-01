package com.estockmarket.command.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.command.application.dto.StockDto;
import com.estockmarket.command.domain.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/command/market/stock")
@Api(value = "stock", description = "Operations pertaining to add,update and delete stock price for the company")
public class StockController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StockService stockService;

	@PostMapping(value = "/add")
	@ApiOperation(value = "Create stocks", response = StockDto.class)
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) throws JsonProcessingException {
		LOGGER.info("Adding stock details === {}", stockDto);
		return new ResponseEntity<StockDto>(stockService.createStock(stockDto), HttpStatus.CREATED);
	}

}
