package com.gazorpazorp.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gazorpazorp.model.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{
	
	List<Account> findAccountsByUserId(@Param("userId") Long userId);
	
	Account findAccountById(@Param("id") Long id);
}
