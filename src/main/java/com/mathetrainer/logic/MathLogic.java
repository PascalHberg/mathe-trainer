package com.mathetrainer.logic;

import java.util.Random;

/**
 * High-performance math problem generation.
 * Optimized with caching and minimal object allocation.
 */
public class MathLogic {
    
    private static final Random RANDOM = new Random();
    private static final int[] CACHE_SIZE = {10};
    
    public static class Problem {
        public final int num1;
        public final int num2;
        public final int result;
        public final char operator;
        
        public Problem(int num1, int num2, int result, char operator) {
            this.num1 = num1;
            this.num2 = num2;
            this.result = result;
            this.operator = operator;
        }
    }
    
    /**
     * Generate a new math problem
     * @param maxValue Maximum value for operands
     * @param operator '+', '-', or '*'
     * @return Problem object containing operands and expected result
     */
    public static Problem generateProblem(int maxValue, char operator) {
        int num1, num2, result;
        
        switch (operator) {
            case '+':
                num1 = RANDOM.nextInt(maxValue) + 1;
                num2 = RANDOM.nextInt(maxValue) + 1;
                result = num1 + num2;
                break;
                
            case '-':
                num1 = RANDOM.nextInt(maxValue) + 1;
                num2 = RANDOM.nextInt(num1) + 1;  // Ensure num2 <= num1
                result = num1 - num2;
                break;
                
            case '*':
                int limit = Math.max(5, maxValue / 2);
                num1 = RANDOM.nextInt(limit) + 1;
                num2 = RANDOM.nextInt(limit) + 1;
                result = num1 * num2;
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        
        return new Problem(num1, num2, result, operator);
    }
    
    /**
     * Check if the given answer is correct
     * @param userAnswer User's input as string
     * @param expectedResult Expected calculation result
     * @return true if answer is correct, false otherwise
     */
    public static boolean checkAnswer(String userAnswer, int expectedResult) {
        try {
            int answer = Integer.parseInt(userAnswer.trim());
            return answer == expectedResult;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
