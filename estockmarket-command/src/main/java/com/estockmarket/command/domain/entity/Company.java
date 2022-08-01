package com.estockmarket.command.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(max = 2000)
    @Column(unique = true)
	private String companyCode;
	@NotNull
	//@Size(max = 100)
    @Column(unique = true)
	private String companyName;
	private String ceo;
	private Double companyTurnover;
	private String companyWebsite;
	private String stockExchangeName;

}
