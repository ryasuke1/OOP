package ru.nsu.khubanov;

public class Sequentially extends SearchNotPrime {

    @Override
    public boolean HasNotPrime(int[] nums) {
        for(int num:nums){
            if(!PrimeCheck.IsPrime(num)){
                return true;
            }
        }
        return false;
    }
}
