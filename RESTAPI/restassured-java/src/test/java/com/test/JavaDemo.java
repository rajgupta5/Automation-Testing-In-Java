package com.test;

public class JavaDemo {

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.isPalindrome(121));

    }


}

class Solution {
    public int reverse(int x) {
        StringBuilder mystring = new StringBuilder();
        int neg = 0;
        if (x<0) {
            x = Math.abs(x);
            neg = 1;

        }
        while(x>0) {
            int temp = x%10;
            mystring = mystring.append(String.valueOf(temp));
            x = x/10;

        }
        try
        {
            Integer output = Integer.valueOf(mystring.toString());
            if(neg==1) {
                output *= -1;
            }
            return output;
        }
        catch(Exception e) {
            return 0;
        }


    }
}

class Solution1 {
    public boolean isPalindrome(int x) {
//        String mystring1 = new StringBuilder().append(x).reverse().toString();
//        String mystring2 = new StringBuilder().append(x).toString();
//        return mystring2.equalsIgnoreCase(mystring1) ;

        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }

}