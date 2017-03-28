package us.team.awesome.calculator.util;

/**
 * Created by JWO on 24.03.2017.
 */

public class Input {

    private static final char[] VALID_INPUT = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9', '+','-','*', '/'};
    private static final char[] VALID_FUNCTION_INPUT = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9', '+','-','*', '/', 'N'};

    public static boolean validFunction(char[] input){
        return validInput(input, VALID_FUNCTION_INPUT);
    }

    public static boolean valid(char[] input){
        return validInput(input, VALID_INPUT);
    }

    private static boolean validInput(char[] input, char[] result){
        boolean validScan = true;
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < result.length; j++){
                if(input[i] == result[j]){
                    break;
                }else if(j == (result.length - 1) && input[i] != result[j]){
                    validScan = false;
                }
            }
            if(!validScan){
                break;
            }
        }
        return validScan;
    }

}
