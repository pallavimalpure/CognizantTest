package com.cts.java.test;

import com.cts.java.main.*;
import com.cts.java.beans.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class EmployeeAddTest 
{
	
  @Test
  public void testEmployeeId0() 
  {
	  Employee emp = ConsoleMenu.SaveEmployee(0, "xyz");
	  Assert.assertEquals(emp, null);
  }
  
  @Test
  public void testEmployeeIdNegative() 
  {
	  Employee emp = ConsoleMenu.SaveEmployee(-1, "xyz");
	  Assert.assertEquals(emp, null);
  }

  @Test
  public void testEmployeeNameNull() 
  {
	  Employee emp = ConsoleMenu.SaveEmployee(1, null);
	  Assert.assertEquals(emp, null);
  }
 
  @Test
  public void testEmployeeNameEmpty() 
  {
	  Employee emp = ConsoleMenu.SaveEmployee(1, "");
	  Assert.assertEquals(emp, null);
  }
  
  @BeforeTest
  public void beforeTest() 
  {
	  
  }

  @AfterTest
  public void afterTest() 
  {
	  
  }

}
