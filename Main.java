// Your code here
import  java.util.*;
public class  Main {
   public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }
  public static  void main(String args[]) {
    Scanner sc =  new Scanner(System.in);
    String  str =  sc.next();
    System.out.println(reverseString(str));

  }
}