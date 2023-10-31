package com.site3.ecommerce.services;

import java.util.List;

import com.site3.ecommerce.dto.CategoryDTO;
import com.site3.ecommerce.dto.ProductDTO;
import com.site3.ecommerce.exceptions.CategoryNotFoundException;
import com.site3.ecommerce.exceptions.ProductNotFoundException;

public interface CatalogueService {
	
//------------------------------ Category ----------------------------
	
		CategoryDTO getCategory(Long categoryId) throws CategoryNotFoundException;
		
		List<CategoryDTO> listCategories();

	 	CategoryDTO saveCategory(CategoryDTO categoryDTO);
	 		
	 	CategoryDTO updateCategory(CategoryDTO categoryDTO);
	 	
	 	void deleteCategory(Long categoryId);
	 	
	 	List<CategoryDTO> searchCategories(String keyword);
	 	
//------------------------------ Product --------------------------------
	 	
		ProductDTO getProduct(Long productId) throws ProductNotFoundException;
		
		List<ProductDTO> listProducts();

		ProductDTO saveProduct(ProductDTO productDTO);
	 		
		ProductDTO updateProduct(ProductDTO productDTO);
	 	
	 	void deleteProduct(Long productId);
	 	
	 	List<ProductDTO> searchProducts(String keyword);
	 	
	 
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
