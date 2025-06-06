package ru.nsu.khubanov;

public class PrimeCheck {
    public static boolean IsPrime(int num){
        if(num<2){
            return true;
        }
        for(int i=2; i*i<=num;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
