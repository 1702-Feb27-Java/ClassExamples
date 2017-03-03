package com.revature.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.revature.designpats.*;

@RunWith(Suite.class)
@SuiteClasses(
		{ Circle.class,
	MathClassTest.class,
	MathClassTest.class }
		)
public class AllTests {

}
