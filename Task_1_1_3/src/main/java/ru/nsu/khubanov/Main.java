package ru.nsu.khubanov;
import Expression.*;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new MyNumber(3), new Mul(new MyNumber(2), new Variable("x"))); // (3+(2*x))
        int result = e.eval("x = 10; y = 13");
        System.out.println(result);


        int[] nums = {1, 2, 3, 4, 5};
        CustomCollection collection = new CustomCollection(nums);

        for (int num : collection) {
            System.out.println(num);
        }
    }
}



