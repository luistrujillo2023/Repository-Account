package com.luistrujillo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")
public class Account {

	 //identificador
      @Id
      @Field(name = "identificador")
	  private String id ;
      
      @Field(name = "nombre de cuenta")
      private String username;
      
      @Field(name = "numero de cuenta")
      private String accountNumber;
      
      @Field(name = "cantidad disponible")
      private Double amountAvailable;
      
      @Field(name = "saldo")
      private Double balance;
      
      @Field(name = "tipo de cuenta")
      private String accounttype;

     
	public Account() {
		
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmountAvailable() {
		return amountAvailable;
	}

	public void setAmountAvailable(Double amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	} 
      
      
      
	
}
