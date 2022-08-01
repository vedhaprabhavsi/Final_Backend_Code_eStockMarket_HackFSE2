package com.estockmarket.query.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.query.application.dto.CompanyDto;
import com.estockmarket.query.application.dto.CompanyStockDto;
import com.estockmarket.query.domain.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/query/market/company")
@Api(value = "company", description = "Operations fetch the company details")
public class CompanyController {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyService companyService;

	@GetMapping(value = "/getall")
	@ApiOperation(value = "Fetch companies", notes="Header is not required", response = List.class)
	public ResponseEntity<List<CompanyDto>> getCompany() {
		LOGGER.info("Getting details of all the companies ");
		return new ResponseEntity<List<CompanyDto>>(companyService.getCompany(), HttpStatus.OK);
	}

	@GetMapping(value = "/info/{code}")
	@ApiOperation(value = "Fetch company through code", notes="Header is not required", response = CompanyDto.class)
	public ResponseEntity<CompanyDto> getCompanyId(@PathVariable String code) {
		LOGGER.info("Fetching company detail based on its code {}", code);
		return new ResponseEntity<CompanyDto>(companyService.getCompanyById(code), HttpStatus.OK);
	}

	@GetMapping(value = "/view/{code}")
	@ApiOperation(value = "View company latest stockprice by code", notes="Header is not required", response = CompanyStockDto.class)
	public ResponseEntity<CompanyStockDto> viewCompany(@PathVariable String code) {
		LOGGER.info("Fetching company latest stock price based on code {}", code);
		return new ResponseEntity<CompanyStockDto>(companyService.viewCompanyLatestPrice(code), HttpStatus.OK);
	}

}