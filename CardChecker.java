package dontgiveup;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CardChecker{
    private static final HashMap<String, String> bins = new HashMap<>();

    public static void main(String[] args){
        char[] numberChars;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Write the number of your card: ");
            String number = scanner.nextLine();
            numberChars = number.toCharArray();

        } while(! check(numberChars));

        fillBins();
        checkBin(numberChars);

        int[] pairedDigits = new int[8];
        int[] nonPairedDigits = new int[8];

        //fill pairedArray
        int counter = 0;
        for(int i = 1; i < numberChars.length; i += 2){
            pairedDigits[counter] = Integer.parseInt(String.valueOf(numberChars[i]));
            counter++;
        }

        //fill nonPairedArray
        int counter2 = 0;
        for(int i = 0; i < numberChars.length; i += 2){
            nonPairedDigits[counter2] = Integer.parseInt(String.valueOf(numberChars[i]));
            counter2++;
        }

        int pairedSum = 0;
        for(int pairedDigit : pairedDigits)
            pairedSum += pairedDigit;

        int nonPairedSum = 0;
        for(int nonPairedDigit : nonPairedDigits){
            int temp = nonPairedDigit*2;
            if(temp > 9){
                temp = temp-9;
            }
            nonPairedSum += temp;
        }


        if((nonPairedSum+pairedSum)%10 != 0){
            System.out.println("Invalid card");
        }else{
            System.out.println("Valid card");
        }

    }

    public static boolean check(char[] numberChars){
        if(numberChars.length != 16){
            System.out.println("Card number must contains only 16 digits");
            return false;
        }

        for(char numberChar : numberChars){
            try{
                Integer.parseInt(String.valueOf(numberChar));
            }catch(Exception e){
                System.out.println("Card number must contains only digits!");
                return false;
            }
        }
        return true;
    }

    public static void checkBin(char[] charArray){
        String oneDigitCombination = String.valueOf(charArray[0]);
        String twoDigitCombination = String.valueOf(charArray[0])+charArray[1];
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 4; i++){
            stringBuilder.append(charArray[i]);
        }
        String fourDigitCombination = stringBuilder.toString();

        for(Map.Entry<String, String> entry : bins.entrySet()){
            if(oneDigitCombination.equals(entry.getKey())){
                System.out.println("The card belongs to "+entry.getValue());
                break;
            }
            if(twoDigitCombination.equals(entry.getKey())){
                System.out.println("The card belongs to "+entry.getValue());
                break;
            }
            if(fourDigitCombination.equals(entry.getKey())){
                System.out.println("The card belongs to "+entry.getValue());
                break;
            }
        }
    }

    public static void fillBins(){ //add more bins if you want

        bins.put("4", "Visa");
        //mastercard filling
        for(int i = 51; i < 56; i++)
            bins.put(String.valueOf(i), "MasterCard");

        for(int i = 2200; i < 2204; i++)
            bins.put(String.valueOf(i), "Mir");
    }
}
