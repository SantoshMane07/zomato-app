package com.santoshmane.zomato_app.utils;

import java.util.Random;

public class OtpGenerator {
    public static String generateRandomOtp() {
        Random random = new Random();
        int otpInt = random.nextInt(10000);  //0 to 9999
        return String.format("%04d", otpInt);
    }
}
