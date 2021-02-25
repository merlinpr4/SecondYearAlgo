
// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for infix arithmetic expressions.
 *
 *  @author Merlin Prasad
 *  @version 1/12/15 13:03:48
 */
import java.util.*;
import java.lang.*;

public class Arith 
{
	//~ Validation methods ..........................................................

	
	

	/**
	 * Validation method for infix notation.
	 *
	 * @param infixLiterals : an array containing the string literals hopefully in infix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer "0", "1" , "2", ..., "-1", "-2", ...
	 *
	 * @return true if the parameter is indeed in infix notation, and false otherwise.
	 **/
	 /* Note I have done this and all further functions with asymptotic worst case running (Big O notation )considering worst case inputs 
	  * this will guarantee the functions will work well regardless of the inputs 
	  *  Order of growth: O (N^2)
	 *
	 *  Explanation: All assignment operations are assumed to have O(1). acesing array given index = O(1)
	 *  
	 *   All if statment blocks have a O(1)
	 *   methodConvertInfixToPostfix has O(N^2)
	 *   for loop has O (N) all methods inside for loop has O(1)
	 *   
	 *   Functions O = 1 + 1 .. + N^2 + N  = N ^2 
	 *  
	 *  Why it is optimal : An array to represent the literals is  good as accessing an element when you know index has O(1) 
	 *  Only the array data structure is being used. A 1 dimensonal array doesnt take up too much memory space.This makes method effient in respect to time and memory
	 *  
	 *  Trying to also validatepostfixOrder is simpler than validating infix order so it is easier to understand code to read and implement
	 */
	public static boolean validateInfixOrder(String infixLiterals[])
	{
		String firstDigit = infixLiterals[0];
		if (firstDigit.length() == 1)
		{
			if( Character.isDigit(firstDigit.charAt(0)) == false ) 
			{
				if(!firstDigit.equals("("))
				{
					return false;
				}

			}
		}
		String lastDigit = infixLiterals[infixLiterals.length-1];
		if (lastDigit.length() == 1)
		{
			if (Character.isDigit(lastDigit.charAt(0)) == false) 
			{
				if(!lastDigit.equals(")"))
				{
					return false;
				}
			}
		}

		String[] postfix = convertInfixToPostfix(infixLiterals);
		String operand = postfix[0];
		char ch1 = operand.charAt(0); 
		if (postfix.length >1)
		{
			String operator = postfix[postfix.length-1];
			int count = 0 ;
			for (int i = 0 ; i < postfix.length ; i ++)
			{
				String element = postfix[i];
				if(Character.isDigit(element.charAt(0)))
				{
					count ++ ;
				}
				else if(element.length()>1)
				{ 
					count ++ ;
				}
				else 
				{
					count -- ;
				}
				if (count <= 0)
				{
					return false;
				}
			}
			if (count == 1)
			{
				return true ;
			}
			return false;
		}
		return true;
	}


	//~ Evaluation  methods ..........................................................


	/**
	 * Evaluation method for infix notation.
	 *
	 * @param infixLiterals : an array containing the string literals in infix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	 /*
	 *  Order of Growth = O(N^2)
	 *  Explanation:  All assignment operations are assumed to have O(1). acesing array given index = O(1)
	 *  
	 *   creating a DLL has O(1)
	 *   methodConvertInfixToPostfix has O(N^2)
	 *   assignments = O (1)
	 *   for loop has O (N) 
	 *   Operation inside for loop has O(N^2). function integer.parseInt has O(N) so N * (N ) = N^2
	 *   
	 *   Functions O = 1  + N^2 + N +N^2  = 2N ^2 = N^2 
	 *  
	 *  Why it is optimal : Using a linked list to represent the stack is good as the stack will constantly need to change in size
	 *  append to head of a doubly linked list is O(1) compared to arrayList which would need to be constantly resized for each remove and adding of elements
	 *  leading to O(N). An array to represent the literals is also good as accessing an element when you know index has O(1)
	 *  This makes method effient in respect to time and memory
	 *  
	 *  Only creating and using 1 LL and 1 array reduces the amount of data structures needed for function which is more memory effient. 
	 *  Trying to also evaluateepostfixOrder is simpler than evaluating infix order so it is easier to understand code to read
	 */
	public static int evaluateInfixOrder(String infixLiterals[])
	{
		LinkedList<String> stack = new LinkedList<String>(); 
		String[] postfix = convertInfixToPostfix(infixLiterals);
		String op1 = "";
		String op2 = "";
		String answer = "";
		int    operand1 = 0;
		int    operand2 = 0;
		int result = 0 ;	
		for (int i = 0 ; i < postfix.length ; i ++)
		{
			String operand = postfix[i];
			if(operand.length() == 1)
			{

				if (operand.equals("+"))
				{
					op2 = stack.pop();
					op1 = stack.pop();
					operand2 = Integer.parseInt(op2);x
					operand1 = Integer.parseInt(op1);
					result = operand1 + operand2 ;
					answer = String.valueOf(result);
					stack.push(answer);
				}
				else if (operand.equals("-"))
				{
					op2 = stack.pop();
					op1 = stack.pop();
					operand2 = Integer.parseInt(op2);
					operand1 = Integer.parseInt(op1);
					result = operand1 - operand2 ;
					answer = String.valueOf(result);
					stack.push(answer);
				}
				else if (operand.equals("*"))
				{
					op2 = stack.pop();
					op1 = stack.pop();
					operand2 = Integer.parseInt(op2);
					operand1 = Integer.parseInt(op1);
					result = operand1 * operand2 ;
					answer = String.valueOf(result);
					stack.push(answer);
				}
				else if (operand.equals("/"))
				{
					op2 = stack.pop();
					op1 = stack.pop();
					operand2 = Integer.parseInt(op2);
					operand1 = Integer.parseInt(op1);
					result = operand1 / operand2 ;
					answer = String.valueOf(result);
					stack.push(answer);
				}
				else
				{
					operand = postfix[i];
					stack.push(operand);
				}
			}
			else
			{
				operand = postfix[i];
				stack.push(operand);		
			}	
		}
		answer = stack.pop();
		result = Integer.parseInt(answer); 
		return result ;
	}

	//~ Conversion  methods ..........................................................

	/**
	 * Converts infix to postfix.
	 *
	 * @param infixLiterals : an array containing the string literals in infix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	 /*  Order of growth:Worst case  O (N^2)
	 * Worst case is an input that causes all the functions to run the longest such as ( with a lot elements in between than ) as it would make while loop run longest
	 * 
	 *  Explanation:All assignment operations are assumed to have O(1). aseesing array given index = O(1)
	 * 	Creating an empty linked list = O (1)
	 *  Creating postfix array = O (N)
	 *  
	 *  	for loop1 = O (N) depends on size of infixLiterals which are of N length
	 * 		 if statements and assignments = 1 + 1 ..  = O (1)
		 * 		while loop1 =  O(N^2) as its also nested
		 * 		while loop2  = O(N^2) as its nested in for loop(N*N = N^2) and all assignments inside are O(1) . N^2 + 1 +1.. = N^2
		 *  	Big O of these 3 loops all together is O(N) + O(N^2) + O (N^2) + O(1) + O(1)... = N +2N^2 = 2N^2 = N^2
	 *  
	 *  	for loop2 = O(M) .M will be amount of elements left in stack and M <N 
	 *  
	 *  	Created postfix resized = O(P) P <=N as postfix size will either be equal to or less than
	 *  
	 *  	Copying the array = O (P)
	 *  
	 *  O of this function is 1 + N + N^2 + M + P + P = N^2 + 2P +M + N= N^2  in asymptotic notation (we know M and P is <=N so we ignore it when considering highest order terms
	 *  
	 *  Why it is optimal : Using a linked list to represent the stack is good as the stack will constantly need to change in size
	 *  append to head of a doubly linked list is O(1) compared to arrayList which would need to be constantly resized for each remove and adding of elements
	 *  leading to O(N). An array to represent the literals is also good as accessing an element when you know index has O(1)
	 *  This makes method effient in respect to time and memory. 
	 *  
	 *  Only creating & using  2 array and a LL reduces the amount of data structures needed for function which is more memory effient. 
	 *  By declaring an new array at only the start and end and outside for loop I dont need to constantly resize and it removes the need to use an array list making algorithim more effient 
	 */
	public static String[] convertInfixToPostfix(String infixLiterals[])
	{
		LinkedList<String> stack = new LinkedList<String>(); 
		int postfix_length = 0 ;
		int size = infixLiterals.length ; 
		String postfix[] = new String[infixLiterals.length];
		int elements = 0;
		for (int i = 0; i < infixLiterals.length ; i++ )
		{
			String operand = infixLiterals[i];
			char ch1 = operand.charAt(0); 
			if( Character.isDigit(ch1) == true ) 
			{
				postfix[postfix_length] = operand;
				postfix_length ++ ;
			}
			else if (operand.length()>1)
			{
				postfix[postfix_length] = operand;
				postfix_length ++ ;
			}
			else if (operand.equals("("))
			{
				stack.push(operand);
				elements ++;
			}
			else if (operand.equals(")"))
			{
				String top = stack.getFirst();
				while (!(top.equals("("))) //this while O (N^2) as its nested in for loop
				{
					String op = stack.pop();
					postfix[postfix_length] = op;
					postfix_length ++;
					elements --;
					top = stack.getFirst();
				}	
				stack.pop();
				elements --;				
			}
			else 
			{
				if (elements > 0)
				{
					String top = stack.getFirst();
					int top_prec = precedence(top.charAt(0)); //O(1) constant
					int token = precedence(operand.charAt(0));
					while (top_prec >= token) //While loop has O(N^2) as its nested in for loop
					{
						String op = stack.pop();
						postfix[postfix_length] = op;
						postfix_length ++;
						elements --;
						if (!stack.isEmpty())
						{
							top = stack.getFirst();
							top_prec = precedence(top.charAt(0)); 
						}
						else  
						{
							top_prec = 0 ;
						}
					}

				}
				stack.addFirst(operand);
				elements ++;
			}			
		}
		for (int i = 0; i < elements ; i++) //M where M<N
		{
			String op = stack.pop();
			postfix[postfix_length] = op;
			postfix_length++;

		}
		String[] postfix_sized = new String[postfix_length];
		System.arraycopy(postfix,0,postfix_sized,0,postfix_length);
		return postfix_sized;
	}
	//precedence function has O(1) as 1 + 1 + 1 + 1 = 4 = 1 so O (1) a constant
	static int precedence (char ch) { 
		switch(ch) { 
		case '+':
			return 1;

		case '-':
			return 1;

		case '*':
			return 2;

		case '/':
			return 2;
		} 
		return 0;
	}




	/**
	 * Converts postfix to infix.
	 *
	 * @param postfixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	 /*  Order of growth:Worst case  O (N) 
	 * Worst case is an input that causes all the functions to run the longest such as ( with a lot elements in between than ) as it would make while loop run longest
	 * 
	 *  Explanation: All assignment operations are assumed to have O(1). aseesing array given index = O(1)
	 * creating empty linked list  = O(1)
	 *  Creating infix array = O (N) 
	 *  	for loop = O (L) depends on size of postfixLiterals which are of unknown length and somtimes size is getting decreased by 1 leading O(L) L<=N 
	 * 	    if statments array access and assigments = 1 + 1 ..  = O (1)
	 * 		New array of size N = O (N) N is size of infix Literals
	 * 		array copy = O(N)  
	 * 		
	 * 		Big O of function is : 1 + N + L + N + N = O (N) we only consider Highest order operations
	 *  
	 *  Why it is optimal : Using a linked list to represent the stack is good as the stack will constantly need to change in size
	 *  append to head of a doubly linked list is O(1) compared to arrayList which would need to be constantly resized for each remove and adding of elements
	 *  leading to O(N). An array to represent the literals is also good as accessing an element when you know index has O(1)
	 *  This makes method efficient in respect to time and memory
	 *  
	 *   By declaring an new array at only the start and end and outside for loop I remove the need to use an array list or constantly resize the array making algorithim more efficient
	 *   Only using a LL and array reduces the amount of data structures needed for function which is more memory effient. 
	 */
	public static String[] convertPostfixToInfix(String postfixLiterals[])
	{
		LinkedList<String> stack = new LinkedList<String>(); 
		int infix_length = 0 ;
		int elements = 0;
		int size = postfixLiterals.length ; 
		String infix[] = new String[size*3]; 
		String op1 = "";
		String op2 = "";
		for (int i = 0; i < size ; i++ )
		{
			String operand = postfixLiterals[i];
			char ch1 = operand.charAt(0); 
			if( Character.isDigit(ch1) == true ) 
			{
				stack.push(operand);
				elements ++ ;
			}
			else if (operand.length()>1)
			{
				stack.push(operand);
				elements ++ ;
			}
			else 
			{
				op2 = stack.pop();
				op1 = stack.pop();
				infix[infix_length] = "(";
				infix_length ++;
				infix[infix_length] = op1;
				infix_length ++;
				infix[infix_length] = operand;
				infix_length ++;
				infix[infix_length] = op2;
				infix_length ++;
				infix[infix_length] = ")";
				infix_length ++;
				if(i < size-1)
				{
					op1 = postfixLiterals[size-1];
					infix[infix_length] = op1;
					infix_length ++;
					size --;
				}
			}	
		}
		if (!stack.isEmpty())
		{
			op1 = stack.pop();
			infix[infix_length] = op1;
			infix_length ++;
		}
		String[] infixed = new String[infix_length];
		System.arraycopy(infix,0,infixed,0,infix_length);		
		return infixed;
	}

	
}

/*Data Structures used
 * Double Linked List
 * Method Calls and Runtime :push() = O(1), pop() = O(1) ,getFirst() = O(1) ,isEmpty() =O(1). creating new LL = O(1)
 * Reasoning : Javas version of a linked list is actually a double linked list data structure
 * 				I used this list to represent the stack because the stack would be constanly resized with the various
 * 				push and pop operations and with a double linked list append and deleting the first node is reletvely
 * 				simple operation if we have a pointer to node we are adding to it is just O(1). As we are always adding 
 * 				and removing from head of list resizing the list is not too costly an operation. 
 * 				isEmpty() function simply returns true if this list contains no elements which would lead to O(1)
 * 				By using a linked list instead of an array List I removed the need to  create a new array for multiple
 * 				insertions and deletions.
 * 
 * Sources: Lecture notes,
 * 		 https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 *		 https://docs.oracle.com/javase/7/docs/api/java/util/List.html#isEmpty()
 * 
 * Array
 * Method Calls and Runtime :arraycopy()= O(N) ,insert when you know index = O(1) ,search = O(n). Creating an new array = O(N)
 * Reasoning : I used arrays to represent the postfix and infix notation. This is because if I knew the index adding 
 * 				element to array only took O(1). I also didnt have to constantly resize my array as when I 
 * 				used an array like convertPostfixtoinfix I created an array 
 * 				big enough at the start of method and at the very end created one new array with size needed at O(n)
 * 				reducing the need to constantly resize the array or use an arrayList. 
 * Sources : Lecture notes, 
 * 			https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html
 * 
 * 
 * 
 * 
 */
 

