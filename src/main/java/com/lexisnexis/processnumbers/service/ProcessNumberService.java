package com.lexisnexis.processnumbers.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProcessNumberService {

    public List<Integer> decodeNumber(String numberString) {
        if (checkIfInteger(numberString)) {
            int integer = Integer.parseInt(numberString,8);
            return decodeInteger(integer);
        } else {
            BigInteger decimalNumber = new BigInteger(numberString,8);
            return decodeBigInteger(decimalNumber);
        }
    }

    private boolean checkIfInteger(String numberString){
        try{
            Integer.parseInt(numberString);
        }
        catch(NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }


    private List<Integer> decodeInteger(int decimalNumber) {
        List<Integer> byteList = new ArrayList<>();
        while (decimalNumber > 0) {
            // Convert decimal number to a list of bytes
            byteList.add(decimalNumber % 256);
            decimalNumber /= 256;
        }
        Collections.reverse(byteList);
        return byteList;
    }

    private List<Integer> decodeBigInteger(BigInteger decimalNumber) {
        List<Integer> byteList = new ArrayList<>();
        while (!decimalNumber.equals(BigInteger.ZERO)) {
            // Convert decimal number to a list of bytes
            int toBeAdded = decimalNumber.mod(BigInteger.valueOf(256)).intValue();
            byteList.add(toBeAdded);
            decimalNumber = decimalNumber.divide(BigInteger.valueOf(256));
        }
        Collections.reverse(byteList);
        return byteList;
    }

    public static void main(String[] args) {
        int smallInteger = 31646541;
        ProcessNumberService processNumberService = new ProcessNumberService();
        List<Integer> resultSmall = processNumberService.decodeNumber(String.valueOf(smallInteger));
        System.out.println("Small Integer Result: " + resultSmall);

        String largeIntegerString = "2277114742311135167021343424004141432061264036716605455" +
                "35070012425145143366515462107042711115720106717127670062" +
                "71704657770433346073017047360217626325467150763006577133" +
                "54152655466766041402716542311111131505761476052650004524" +
                "21616177052165224543311447543654741617367042213645643631" +
                "33346575330621635642541636644326535501666004333326756424" +
                "47003252221104064117622317044717471111";
        List<Integer> resultLarge = processNumberService.decodeNumber(largeIntegerString);
        System.out.println("Large Integer Result: " + resultLarge);
    }
}

