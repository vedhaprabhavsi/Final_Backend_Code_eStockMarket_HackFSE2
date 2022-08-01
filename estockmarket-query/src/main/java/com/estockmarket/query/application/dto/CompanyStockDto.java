package com.estockmarket.query.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStockDto {

	private Long id;
	private String companyCode;
	private String companyName;
	private String ceo;
	private Double companyTurnover;
	private String companyWebsite;
	private String stockExchangeName;
	private Double latestStockPrice;

}
