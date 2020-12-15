package com.qmetry;

import com.qmetry.models.Number;
import com.qmetry.models.NumbericSystem;
import org.apache.log4j.Logger;

public class NumberSystemsConverter {
    final static Logger logger = Logger.getLogger(NumberSystemsConverter.class);

    public Number convertIntToHex (int num){
        logger.info("convertIntToHex method");

        int rem;
        String str2="";

        char hex[]= NumbericSystem.hexadecimal.digitsAlphabet;

        while(num>0)
        {
            rem=num%16;
            str2=hex[rem]+str2;
            num=num/16;
        }

        Number result = new Number(str2, NumbericSystem.hexadecimal);
        return result;
    }

    public Number convertIntToBin(int num){
        logger.info("convertIntToBin method");

        int count = 0, a;
        String x = "";

        while(num > 0)
        {
            a = num % 2;
            if(a == 1)
            {
                count++;
            }
            x = a + "" + x;
            num = num / 2;
        }

        Number result = new Number(x, NumbericSystem.binary);
        return result;
    }
}
