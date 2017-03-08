package com.banksd.bank;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import com.banksd.BankServices;

public class TAccount {
@Test
public void testgenAccNo(){
	
	long lAcc1 = Account.genAcc();
	long lAcc2 = Account.genAcc();
	
	assertEquals(true,lAcc1!=lAcc2);
}

@Test
public void testDeposit(){
	double exValue = 1500.5;
	Account acc = new Account(23434234,"Checking",1000);
	acc.setBalance(500.5);
	
	double cValue = acc.getBalance();
	assertEquals(true,exValue==cValue);
}

@Test
public void testWithdraw(){
	double exValue = 749.8;
	Account acc = new Account(23434234,"Checking",1000);
	acc.getBalance(250.2);
	
	double cValue = acc.getBalance();
	assertEquals(true,exValue==cValue);
}

}
