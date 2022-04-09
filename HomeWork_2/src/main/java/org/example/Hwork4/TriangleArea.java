package org.example.Hwork4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TriangleArea {

    public double triangleArea(int a, int b, int c) throws MyExceptions {
        if(!isTriangle(a,b,c)) throw new MyExceptions("Это не треугольник!");
        double p2 = (a + b + c)/2f; // полупериметр
        // System.out.println("полупериметр = " + p2);
        double result = Math.sqrt(p2*(p2-a)*(p2-b)*(p2-c));
        result = round(result, 6);
        // System.out.println("результат = " + result);
        return result;
    }

    public boolean isTriangle(int a, int b, int c) {
        if(a<1 || b<1 || c<1) return false; // проверка, что стороны положительные
        if(a + b > c && a + c > b && b + c > a) return true; // проверка, что сумма любых двух сторон больше третьей
        return false;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
