package com.estockmarket.command.domain.service;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estockmarket.command.application.dto.StockDto;
import com.estockmarket.command.domain.entity.Stocks;
import com.estockmarket.command.infrastructure.eventsourcing.KafkaStocksEventSourcing;
import com.estockmarket.command.infrastructure.repository.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StockService {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private KafkaStocksEventSourcing kafkaStocksEventSourcing;

	public StockDto createStock(StockDto stockDto) throws JsonProcessingException {
		Stocks stocks = (Stocks) modelMapper.map(stockDto, Stocks.class);
		Stocks stocksResult = stockRepository.save(stocks);
		kafkaStocksEventSourcing.createStocksEvent(stocksResult);
		LOGGER.info("Stocks saved successfully to db!! {}", stocksResult);
		return (StockDto) modelMapper.map(stocksResult, StockDto.class);
	}

	@SuppressWarnings("unchecked")
	private Object map(Object object, @SuppressWarnings("rawtypes") Class clazz) {
		return object != null ? modelMapper.map(object, clazz) : null;
	}
}
