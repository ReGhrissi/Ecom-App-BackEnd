package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.CategoryDto;
import com.site3.ecommerce.dto.ProductDto;
import com.site3.ecommerce.exceptions.CategoryNotFoundException;
import com.site3.ecommerce.exceptions.ProductNotFoundException;

public interface CatalogueService {
	
//------------------------------ Category ----------------------------
	
		CategoryDto getCategory(Long categoryId) throws CategoryNotFoundException;
		
		List<CategoryDto> listCategories();

	 	CategoryDto saveCategory(CategoryDto categoryDTO);
	 		
	 	CategoryDto updateCategory(CategoryDto categoryDTO);
	 	
	 	void deleteCategory(Long categoryId);
	 	
	 	List<CategoryDto> searchCategories(String keyword);
	 	
//------------------------------ Product --------------------------------
	 	
		ProductDto getProduct(Long productId) throws ProductNotFoundException;
		
		List<ProductDto> listProducts();

		ProductDto saveProduct(ProductDto productDTO);
	 		
		ProductDto updateProduct(ProductDto productDTO);
	 	
	 	void deleteProduct(Long productId);
	 	
	 	List<ProductDto> searchProducts(String keyword);
	 	
	 
	   // CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
	  //  SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
	    
	    
	    
	   // BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
	    
	   // void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
	  //  void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
	   // void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

	 //   List<BankAccountDTO> bankAccountList();

	    

	    

	    

	 //   List<AccountOperationDTO> accountHistory(String accountId);

	  //  AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

	    
	
}
