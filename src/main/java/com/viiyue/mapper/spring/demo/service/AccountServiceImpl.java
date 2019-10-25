package com.viiyue.mapper.spring.demo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.viiyue.mapper.spring.demo.bean.AccountDTO;
import com.viiyue.mapper.spring.demo.mapper.AccountMapper;

@Service
public class AccountServiceImpl {

	@Autowired
	private AccountMapper accountMapper;
	
	public AccountDTO loginByName( String loginName, String password ) {
		List<AccountDTO> results = accountMapper.selectByLoginName( loginName );
		Assert.notEmpty( results, "User " + loginName + " dose not exist");
		Assert.isTrue( results.size() == 1, "Error, find multiple " + loginName + " accounts with the same name");
		AccountDTO targetAccount = results.get( 0 );
		Assert.isTrue( Objects.equals( targetAccount.getPassword(), password ), "Your password does not match" );
		return targetAccount;
	}
	
}
