class Recursion {
    static double predictFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        
        double nextYearValue = presentValue * (1 + growthRate);
        
        return predictFutureValue(nextYearValue, growthRate, years - 1);
    }
}

public class Forecast {
    public static void main(String[] args) {
       double pv = 1000;     
        double rate = 0.10;   
        int years = 3;   
        
        double fv = Recursion.predictFutureValue(pv, rate, years);
        System.out.println("Future Value: $" + fv);
    }
}

// Output
// Future Value: $1331.0

// Time Complexity: O(n) - the recursive call is made years times (n times).
// Better to use iterative approach, since it avoids the overhead of multiple func calls, and occupies O(1) stack space, unlike the former which occupiies O(n) stack space
// For this particular problem, we can also use the formula FV = PV * (1 + r)^n, which has O(1) time complexity.