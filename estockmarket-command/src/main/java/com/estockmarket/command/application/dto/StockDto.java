package com.estockmarket.command.application.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
	private long id;
	private String companyCode;
	private Double price;
	private Date createdOn;
	private Date updatedOn;

}
