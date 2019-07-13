package com.airtel.loggingaspect;


import org.springframework.stereotype.Component;

@Component("method")
public class Method {

    @Loggable
    public double power(int x, int p) {
        return Math.pow(x, p);
    }

    public  double other(Data data){
        int x = data.getNumber();
        int p = data.getPower();
        return  Math.pow(x,p);
    }
}
