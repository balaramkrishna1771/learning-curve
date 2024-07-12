import java.util.*;
public class UdemyPractice {
    // write code here
    public static int reverse(int number){
        int result = 0;
        while(number != 0){

            result = (result * 10) + (number % 10); 
            number /= 10;
        }
        return result;
    }
    public static void numberToWords(int number){
        String[] wordsList = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
        if ( number < 0 ) System.out.println("Invalid Value");
        else if (number > 0 && number < 10) System.out.println(wordsList[number]);
        else if (number > 9){
            number = reverse(number);
            // System.out.println(number);
            while (number != 0){
                System.out.println(wordsList[number % 10]);
                number /= 10;
            }
        }
    }
    public static void main(String[] args){
        int number;
        Scanner  scan = new Scanner(System.in); 
        number = scan.nextInt();
        numberToWords(number);
        scan.close();
    }   
}

