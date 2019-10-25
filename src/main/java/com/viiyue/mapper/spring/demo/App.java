package com.viiyue.mapper.spring.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viiyue.mapper.spring.demo.enums.Gender;
import com.viiyue.mapper.spring.demo.mapper.AccountMapper;
import com.viiyue.mapper.spring.demo.model.Account;
import com.viiyue.mapper.spring.demo.service.AccountServiceImpl;
import com.viiyue.plugins.mybatis.condition.Example;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:/spring/spring-context.xml" )
public class App {
	
	static final String NICK_NAME = "唐小白";
	static final String LOGIN_NAME = "tangxbai";
	static final String EMAIL = "tangxbai@hotmail.com";

	@Autowired
	private AccountMapper mapper;
	
	@Autowired
	private AccountServiceImpl accountService;
	
	@Test
	public void baseDeleteMapper() {
		mapper.delete( Account.builder().loginName( LOGIN_NAME ).build() );
		mapper.deleteByPrimaryKey( 1L );
		mapper.deleteByPrimaryKeyGroup( 1L, 2L, 3L, 4L );
		mapper.deleteByPrimaryKeyIndex( 0, 1L );
		mapper.deleteByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L );
		mapper.deleteAll();
	}
	
	@Test
	public void baseInsertMapper() {
		Account account = new Account();
		account.setId( 1L ); // 多次测试，由于包含删除操作和新增操作，主键值会越来越大，这里为了保证后面Id值的测试正确性，手动写死Id值
		account.setNickName( NICK_NAME );
		account.setLoginName( LOGIN_NAME );
		account.setPassword( EMAIL );
		account.setGender( Gender.MALE );
		account.setAge( 996 );
		account.setDisplay( true );
		assert mapper.insert( account ) == 1;
		
		Account account2 = new Account();
		account2.setId( 2L ); // 多次测试，由于包含删除操作和新增操作，主键值会越来越大，这里为了保证后面Id值的测试正确性，手动写死Id值
		account2.setNickName( NICK_NAME );
		account2.setLoginName( LOGIN_NAME + "007" );
		account2.setPassword( EMAIL );
		account2.setRemark( "A java programmer" );
		account2.setAge( 007 );
		account2.setDisplay( false );
		assert mapper.insertBySelective( account2 ) == 1;
	}
	
	@Test
	public void baseExampleMapper() {
		mapper.selectByExample( Example.query( Account.class ).eq( "id", 1L ).condition( "login_name = #{loginName}", "tangxbai" ).limit( 20, 20 ) );
		mapper.selectByExample( Example.select( Account.class ).includes( "id", "loginName", "password" ).when().eq( "age", 996 ) );
		mapper.updateByExample( Example.update( Account.class ).set( "loginName", "password" ).values( "zhangsan", "Aa123456" ) );
		mapper.updateByExample( Example.update( Account.class ).set( "loginName", "password" ).values( "zhangsan", "Aa123456" ).when().eq( "age", 996 ) );
		mapper.updateByExample( Example.update( Account.class ).bind( Account.builder().remark( "A java programmer of 007 ..." ).build() ).when().eq( "id", 1L ) );
//		mapper.deleteByExample( Example.query( Account.class ).eq( "age", 007 ) ) );
	}
	
	@Test
	public void baseSelectLimitMapper() {
		mapper.selectByLimit( 0, 10 );
	}
	
	@Test
	public void baseSelectMapper() {
		mapper.select( Account.builder().loginName( LOGIN_NAME ).build() );
		mapper.selectAll();
		mapper.selectByPrimaryKey( 1L );
		mapper.selectByPrimaryKeyGroup( 1L, 2L, 3L, 4L );
		mapper.selectByPrimaryKeyIndex( 0, 1L );
		mapper.selectByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L );
	}
	
	@Test
	public void baseUpdateMapper() {
		Account account = new Account();
		account.setId( 1L );
		account.setLoginName( LOGIN_NAME );
		account.setVersion( 1L );
		account.setRemark( "UPDATED" );
		mapper.updateByPrimaryKey( account );
		mapper.updateByPrimaryKeySelective( account );
		mapper.updateByPrimaryKeyIndex( 0, account );
		mapper.updateByPrimaryKeyIndexSelective( 0, account );
	}
	
	@Test
	public void aggregateFunctionMapper() {
		mapper.selectStatisticByAggregateFunction( Example.count( Account.class, "*" ).orderBy( "createTime" ) );
		mapper.selectStatisticByAggregateFunction( Example.count( Account.class, "*" ).when().eq( "gender", Gender.FEMALE ).orderBy( "createTime" ) );
		mapper.selectStatisticByAggregateFunction( Example.average( Account.class, "age" ) );
		mapper.selectStatisticByAggregateFunction( Example.maximum( Account.class, "age" ) );
		mapper.selectStatisticByAggregateFunction( Example.minimum( Account.class, "age" ) );
		mapper.selectStatisticListByAggregateFunction( Example.summation( Account.class, "age", "version" ).groupBy( "gender" ).orderBy( "createTime", false ) );
	}
	
	@Test
	public void forUpdateMapper() {
		mapper.selectForUpdate( Account.builder().loginName( LOGIN_NAME ).build() );
		mapper.selectByPrimaryKeyForUpdate( 1L );
		mapper.selectByPrimaryKeyGroupForUpdate( 1L, 2L, 3L, 4L );
		mapper.selectByPrimaryKeyIndexForUpdate( 0, 1L );
		mapper.selectByPrimaryKeyIndexGroupForUpdate( 0, 1L, 2L, 3L, 4L );
	}
	
	@Test
	public void logicallyDeleteMapper() {
		mapper.logicallyDelete( Account.builder().loginName( LOGIN_NAME ).build() );
		mapper.logicallyDeleteAll();
		mapper.logicallyDeleteByPrimaryKey( 1L );
		mapper.logicallyDeleteByPrimaryKeyGroup( 1L, 2L, 3L, 4L );
		mapper.logicallyDeleteByPrimaryKeyIndex( 0, 1L );
		mapper.logicallyDeleteByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L );
	}
	
	@Test
	public void recycleBinMapper() {
		mapper.selectAllDeleted();
		mapper.restore( Account.builder().loginName( LOGIN_NAME ).build() );
		mapper.restoreAllDeleted();
		mapper.restoreByPrimaryKey( 1L );
		mapper.restoreByPrimaryKeyGroup( 1L, 2L, 3L, 4L );
		mapper.restoreByPrimaryKeyIndex( 0, 1L );
		mapper.restoreByPrimaryKeyIndexGroup( 0, 1L, 2L, 3L, 4L );
	}
	
	@Test
	public void accountMapper() {
		mapper.selectByLoginName( "tangxbai" );
	}
	
	@Test
	public void accountServiceImpl() {
		accountService.loginByName( "tangxbai", "tangxbai@hotmail.com" );
	}
	
}
