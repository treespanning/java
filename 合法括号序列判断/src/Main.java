import java.util.*;
public class Main {
    public boolean chkParenthesis(String A, int n) {
        // write code here
        int brackets=0;
        for(int i=0;i<n;i++){
            if(A.charAt(i)=='(')
            {
                brackets++;
            }
            else if(A.charAt(i)==')')
            {
                brackets--;
            }
        }
        if(brackets==0)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
