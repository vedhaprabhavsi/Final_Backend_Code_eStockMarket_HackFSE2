package com.estockmarket.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockAggregateDTO {
	private String companyCode;
	private Double minPrice;
	private Double maxPrice;
	private Double avgPrice;

}
