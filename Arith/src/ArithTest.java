import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.junit.Test;
//-------------------------------------------------------------------------
/**
 *  Test class for Arith
 *
 *  @author Merlin Prasad
 *  @version 12/10/16 18:15
 */
@RunWith(JUnit4.class)
public class ArithTest {

	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new Arith();
	}

	@Test
	public void testConvertInfixToPostfix() {
		String infixLiteral[] = {"-7"};
		String expectedResult0[] =  {"-7"};
		assertArrayEquals( expectedResult0,Arith.convertInfixToPostfix(infixLiteral));


		String infixLiterals[] = {"(","1", "+", "25",")", "/", "-3" };
		String expectedResult[] =  {"1", "25", "+", "-3", "/"};
		assertArrayEquals( expectedResult,Arith.convertInfixToPostfix(infixLiterals));

		String infixLiterals2[] = {"1","+","2","-","3" };
		String expectedResult2[] = {"1","2","+","3","-"};
		assertArrayEquals( expectedResult2,Arith.convertInfixToPostfix(infixLiterals2));

		String infixLiterals3[] = {"(", "56", "+","-607",")","/", "(","50","*", "4", ")" };
		String expectedResult3[] = {"56","-607","+","50","4","*","/"};	
		assertArrayEquals( expectedResult3,Arith.convertInfixToPostfix(infixLiterals3));

		String infixLiterale4[] = {"-45", "(", "/",")","507","*","7","-","9"};
		String expectedResult4[] = {"-45","/","507","7","*","9","-"};	
		assertArrayEquals( expectedResult4,Arith.convertInfixToPostfix(infixLiterale4));	
		
		String infixLiterale5[] = {"(", "7","+","3","/","2",")"};
		String expectedResult5[] = {"7","3","2","/","+"};	
		assertArrayEquals( expectedResult5,Arith.convertInfixToPostfix(infixLiterale5));
		
		String infixLiterale6[] = {"(","1","-","2",")","*","3"};
		String expectedResult6[] = {"1","2","-","3","*"};
		System.out.println();
		assertArrayEquals(expectedResult6,Arith.convertInfixToPostfix(infixLiterale6));

	}

	@Test
	public void testValidateInfixOrder() {

		String infixLiterals[] = {"(","1", "+", "-25",")", "-", "-3" }; 
		assertTrue(Arith.validateInfixOrder(infixLiterals));

		//checks that postfix order is incorrect
		String infixLiterals1[] = {"3","7","9","+","+"};  
		assertFalse(Arith.validateInfixOrder(infixLiterals1));

		String infixLiterals2[] = {"3","+", "-2", "7"};  
		assertFalse(Arith.validateInfixOrder(infixLiterals2));

		String infixLiterals3[] = {"7"};
		assertTrue(Arith.validateInfixOrder(infixLiterals3));

		String infixLiterals4[] = {"*", "-2", "7"};  
		assertFalse(Arith.validateInfixOrder(infixLiterals4));

		String infixLiterals5[] = {"(","7",")"};
		assertTrue(Arith.validateInfixOrder(infixLiterals5));

		String infixLiterals6[] = {"-77"};
		assertTrue(Arith.validateInfixOrder(infixLiterals6));

		String infixLiterals7[] = {"-", "/", "7"};  
		assertFalse(Arith.validateInfixOrder(infixLiterals7));

		String infixLiterals8[] = {"(","1", "*", "-25",")", "/", "-3","*","+","/","*","0" }; 
		assertFalse(Arith.validateInfixOrder(infixLiterals8));

		String infixLiterals9[] = {"(","7",")", "*", "-601"};
		assertTrue(Arith.validateInfixOrder(infixLiterals9));
		
		String infixLiterals10[] = {"(","-1", "*", "-25",")" }; 
		assertTrue(Arith.validateInfixOrder(infixLiterals10));

		String infixLiterals11[] = {"(","1","-25",")", "/", "-3","*","+","/","*","0" }; 
		assertFalse(Arith.validateInfixOrder(infixLiterals8));
	}

	@Test
	public void testEvaluateInfixOrder() {
		//test single element
		String infixLiterals[] = {"(","7",")"};
		assertEquals(7,Arith.evaluateInfixOrder(infixLiterals));
		
		//test multiple elements
		String infixLiterals1[] = {"(","1", "+", "5",")", "-", "-3" };
		assertEquals(9,Arith.evaluateInfixOrder(infixLiterals1));
		
		String infixLiterals2[] = {"50","/","5" }; //10
		assertEquals(10,Arith.evaluateInfixOrder(infixLiterals2));
		
		String infixLiterals3[] = {"4","*","5" ,"-","3"}; //17
		assertEquals(17,Arith.evaluateInfixOrder(infixLiterals3));
	}
	
	@Test
	public void testConvertPostfixToInfix() {
		String postfixLiteral0[] = {"-7"};
		String expectedResult0[] =  {"-7"};
		assertArrayEquals( expectedResult0,Arith.convertPostfixToInfix(postfixLiteral0));


		String expectedResults[] = {"(","1", "+", "25",")", "/", "-3" };
		String postfixLiteral[] =  {"1", "25", "+", "-3", "/"};
		assertArrayEquals( expectedResults,Arith.convertPostfixToInfix(postfixLiteral));

		String expectedResult2[] = {"(","1","+","2",")","-","3" };
		String postfixLiteral2[] = {"1","2","+","3","-"};
		assertArrayEquals( expectedResult2,Arith.convertPostfixToInfix(postfixLiteral2));

		String expectedResult3[] = {"(", "56", "+","-607",")","/", "(","50","*", "4", ")" };
		String postfixLiteral3[] = {"56","-607","+","50","4","*","/"};	
		assertArrayEquals( expectedResult3,Arith.convertPostfixToInfix(postfixLiteral3));

		String postfixLiteral4[] = {"-45","507","/","7","9","*","-"};
		String expectedResult4[] = {"(","-45","/","507",")","-","(","7","*","9",")"};
		assertArrayEquals( expectedResult4,Arith.convertPostfixToInfix(postfixLiteral4));	
		
		String expectedResult5[] = {"(","3", "/", "2", ")", "+", "7"};
		String postfixLiteral5[] = {"7","3","2","/","+"};	
		assertArrayEquals( expectedResult5,Arith.convertPostfixToInfix(postfixLiteral5));
	}

}
