package dpapps.tools;

import dpapps.constants.AppConstants;

import java.util.Random;

public class VerificationCodeGenerator {

    private final String[] characters =  {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n" ,"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" , "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    public String generateCode() {
        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();

        for (int iteration = 0; iteration < AppConstants.VERIFICATION_CODE_LENGTH; iteration++) {
            stringBuilder.append(characters[random.nextInt(characters.length)]);
        }

        return stringBuilder.toString();
    }

}