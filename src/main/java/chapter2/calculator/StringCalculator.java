package chapter2.calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public double add(String text) {
        if (isBlank(text)) {
            return 0;
        }
        
        return sum(toDoubles(split(text)));
    }

    private boolean isBlank(String text) {
        return text == null || text.isEmpty() || text.isBlank();
    }

    private String[] split(String text) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (matcher.find()) {
            String customDelimiter = matcher.group(1);
            return matcher.group(2).split(customDelimiter);
        }

        return text.split(",|:");
    }

    private double[] toDoubles(String[] tokens) {
        double[] numbers = new double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = toPositive(tokens[i]);
        }
        return numbers;
    }

    private double toPositive(String value) {
        double number = Double.parseDouble(value);
        if (number < 0) {
            throw new RuntimeException("음수는 더할 수 없습니다");
        }
        return number;
    }

    private double sum(double[] arr) {
        return Arrays.stream(arr).sum();
    }
}
