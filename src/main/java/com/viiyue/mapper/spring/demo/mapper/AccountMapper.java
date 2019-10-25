package com.viiyue.mapper.spring.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.viiyue.mapper.spring.demo.bean.AccountDTO;
import com.viiyue.mapper.spring.demo.model.Account;
import com.viiyue.plugins.mybatis.annotation.mark.EnableResultMap;
import com.viiyue.plugins.mybatis.mapper.Mapper;

// mybatis 自带了一个@Mapper注解可以用来标识Mapper接口
// 但是Mapper接口已被用作mybatis-mapper的基础接口，再用的话会出现重复需要导入全类路径，所以这里建议受用spring提供的@Repository注解。
@Repository
public interface AccountMapper extends Mapper<Account, AccountDTO, Long> {
	
	@EnableResultMap
	List<AccountDTO> selectByLoginName( String loginName );
	
}
