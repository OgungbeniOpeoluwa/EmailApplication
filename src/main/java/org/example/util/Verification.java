package org.example.util;

public class Verification {

    public static boolean verifyPhoneNumber(String phoneNumber){
        if (phoneNumber.startsWith("+")) return phoneNumber.matches("[+][1-9][0-9]{6,12}");
        else return phoneNumber.matches("0[7-9][0-1][0-9]{8}");

    }
    public static boolean verifyPassword(String password){
        return  password.matches("[0-9]{4}");
    }
}
