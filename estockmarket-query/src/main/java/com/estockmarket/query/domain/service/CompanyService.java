package com.estockmarket.query.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estockmarket.query.application.dto.CompanyDto;
import com.estockmarket.query.application.dto.CompanyStockDto;
import com.estockmarket.query.domain.exception.InvalidCompanyCodeException;
import com.estockmarket.query.domain.exception.NoStocksExistsException;
import com.estockmarket.query.domain.model.Company;
import com.estockmarket.query.domain.model.Stocks;
import com.estockmarket.query.infrastructure.repository.Companyrepository;
import com.estockmarket.query.infrastructure.repository.StockRepository;

@Service
public class CompanyService {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Companyrepository companyrepository;

	@Autowired
	private StockRepository stockRepository;

	public List<CompanyDto> getCompany() {
		LOGGER.info("Fetching all the company details {}");
		List<Company> comList = companyrepository.findAll();
		List<CompanyDto> companyDtos = new ArrayList<>();
		comList.forEach(company -> {
			CompanyDto companyDto = convertToCompanyDto(company);
			companyDtos.add(companyDto);
		});

		return companyDtos;

	}

	private CompanyDto convertToCompanyDto(Company company) {
		LOGGER.info("Converting to Company dto {}", company);
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(company.getId());
		companyDto.setCompanyCode(company.getCompanyCode());
		companyDto.setCeo(company.getCeo());
		companyDto.setCompanyName(company.getCompanyName());
		companyDto.setCompanyTurnover(company.getCompanyTurnover());
		companyDto.setCompanyWebsite(company.getCompanyWebsite());
		companyDto.setStockExchangeName(company.getStockExchangeName());
		return companyDto;
	}

	public CompanyDto getCompanyById(String code) {
		LOGGER.info("Fetching company based on its code {}", code);
		Optional<Company> company = companyrepository.findByCompanyCode(code);
		CompanyDto companyDto = new CompanyDto();
		if (company.isPresent()) {
			companyDto = convertToCompanyDto(company.get());
			return companyDto;
		}
		throw new InvalidCompanyCodeException();

	}

	public CompanyStockDto viewCompanyLatestPrice(String code) {
		LOGGER.info("Fetching company's latest stock price based on its code {}", code);
		CompanyStockDto companyStockDto = new CompanyStockDto();
		Optional<Company> company = companyrepository.findByCompanyCode(code);
		if (company.isPresent()) {
			Optional<Stocks> stock = stockRepository.findFirstByCompanyCodeOrderByUpdatedOnDesc(code);
			if(stock.isPresent()) {
				converToCompanyStockDto(company.get(), stock.get(), companyStockDto);
			} else {
				throw new NoStocksExistsException();
			}
		}

		return companyStockDto;
	}

	private void converToCompanyStockDto(Company company, Stocks stock, CompanyStockDto companyStockDto) {
		LOGGER.info("Converting to Company Stock dto company,stock,companystockDto{}", company,stock,companyStockDto);
		companyStockDto.setId(company.getId());
		companyStockDto.setCompanyCode(company.getCompanyCode());
		companyStockDto.setCeo(company.getCeo());
		companyStockDto.setCompanyName(company.getCompanyName());
		companyStockDto.setCompanyTurnover(company.getCompanyTurnover());
		companyStockDto.setCompanyWebsite(company.getCompanyWebsite());
		companyStockDto.setStockExchangeName(company.getStockExchangeName());
		companyStockDto.setLatestStockPrice(stock.getPrice());
	}

	public void createCompany(Company company) {
		LOGGER.info("Creating company === {}", company);
		companyrepository.save(company);
	}

	public void removeCompany(Long id) {
		LOGGER.info("Removing company with the ID passed === {}", id);
		companyrepository.deleteById(id);
	}

}