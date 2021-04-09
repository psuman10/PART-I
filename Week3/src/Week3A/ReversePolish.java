package Week3A;

import java.util.*;
public class ReversePolish {
    static int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
        
        case '*':
        case'/':
            return 2;
            
        case '^':
            return 3;
        }
        return -1;
    }
    static String infixToPostFix(String expression){

    	 

        
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <expression.length() ; i++) {
            char c = expression.charAt(i);

 

            //To check if char is an operator
            if(Prec(c)>0){
                while(stack.isEmpty()==false && Prec(stack.peek())>=Prec(c)){
                    result += stack.pop();
                }
                stack.push(c);
            }else if(c==')'){
                char x = stack.pop();
                while(x!='('){
                    result += x;
                    x = stack.pop();
                }
            }else if(c=='('){
                stack.push(c);
            }else{
                result += c;
            }
        }
        for (int i = 0; i <=stack.size() ; i++) {
            result += stack.pop();
        }
        return result;
    }
    public static void main(String[] args) {
        String exp="4*(5+6)";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + infixToPostFix(exp));
        String con = infixToPostFix(exp);
        
        String s[] = new String[con.length()];
        
        for(int i=0; i<con.length(); i++) {
            char c = con.charAt(i);
            s[i] = String.valueOf(c);
        }
        
        
        
        for(int i=0; i<s.length; i++) {
            System.out.println("index"+i+"="+s[i]);
        }
        
       
      
        Main obj = new Main(); 
        int result = obj.Stack(s); 
        System.out.println("The evaluated value is : " +  result);
    }

 

 

 

}