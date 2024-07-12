import java.util.*;
    public class Main {

        public int ageCalc(int age){
            return age - 1;
        }
        public static void main(String[] args){
        String inputString = "the,door,was,open,and,too,close,door";
        String[] resultArr = inputString.split("[\\.,]");
        HashMap<String , ArrayList<String>> finalResult = new HashMap<>();
        for(int i=0; i<resultArr.length; i++){
            if(finalResult.containsKey(resultArr[i])){
                finalResult.get(resultArr[i]).add(resultArr[i]);
            }
            else {
                // ArrayList<String> arrayList = new ArrayList<>);
                // arrayList.add(0,resultArr[i]);
                finalResult.put(resultArr[i] , new ArrayList<>());
            }
            // else {
            //     finalResult.put(resultArr[i],resultArr[i]);
            // }
        }
        for(String eachString : finalResult.keySet()){
            finalResult.get(eachString).add(eachString);
        }
        for(ArrayList<String> eachString : finalResult.values()){
            System.out.println(eachString);
        }
        System.out.println(finalResult);
        // int lengthOfResultArr = resultArr.length;
        // String[][] finalRes = new String[lengthOfResultArr][lengthOfResultArr];
        // for(String str : resultArr){
        //     for( String checkStr : finalRes[]){

        //     }
        // }
        // int[] numbers = new int[5];
        // Arrays numbers =
        // Main mainClassInstance = new Main();
        // for(byte i = 0; i < 5; i++){
        //     numbers[i] = mainClassInstance.ageCalc(i);
        // }
        // System.out.println(Arrays.toString(numbers));
        // for(int number: numbers){
        //     System.out.println(number);
        // }
        //    System.out.println(resultArr);

        //    for(int i = 0 ; i < resultArr.length; i++){
        //     System.out.println(resultArr[i]);
        //    }
        // for (String str: resultArr){
        //     System.out.println(str);
        // }
        // System.out.println(Arrays.toString(resultArr));
        }
    }
