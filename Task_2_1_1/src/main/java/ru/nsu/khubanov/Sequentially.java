package ru.nsu.khubanov;

public class Sequentially {
    public static boolean HasNotPrimeSeq(int[] nums){
        for(int num:nums){
            if(!PrimeCheck.IsPrime(num)){
                return true;
            }
        }
        return false;
    }

}
