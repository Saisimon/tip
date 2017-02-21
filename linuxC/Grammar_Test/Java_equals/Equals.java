/*************************************************************************
 *    File Name: equals.java
  *   Author: Younix
   *  Mail: foreveryounix@gmail.com 
    ** Created Time: Mon 20 Feb 2017 09:39:06 AM CST
 ************************************************************************/

public class Equals{
    public String name = "abc";
    public static void main(String[] args){

        Equals test = new Equals();
        Equals testB = new Equals();
        System.out.println(test.equals(testB)+","+test.name.equals(testB.name));
    }
}
