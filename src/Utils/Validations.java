/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.regex.Pattern;

/**
 *
 * @author MAB
 */
public class Validations {
    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean isNumberValid(String number)
    {
        String emailRegex = "^[0-9]*$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (number == null)
            return false;
        return pat.matcher(number).matches();
    }
}
