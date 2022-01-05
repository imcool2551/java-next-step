public class StringCalculator {
    public double add(String string) {
        if (string.isBlank()) {
            return 0;
        }

        String expression = string;
        String seperator = "[,|:]";
        if (hasCustomSeperator(string)) {
            expression = string.substring(4);
            seperator = String.valueOf(string.charAt(2));
        }

        return calcAdd(expression, seperator);
    }

    private boolean hasCustomSeperator(String expression) {
        return expression.charAt(0) == '/' &&
            expression.charAt(1) == '/' &&
            expression.charAt(3) == '\n';
    }

    private double calcAdd(String expression, String seperator) {
        System.out.println("string = " + expression);
        System.out.println("seperator = " + seperator);
        String[] numericStrings = expression.split(seperator);

        double sum = 0;
        for (String numericString : numericStrings) {
            double number = Double.parseDouble(numericString);
            if (number < 0) {
                throw new RuntimeException("음수는 계산할 수 없습니다");
            }
            sum += number;
        }
        return sum;
    }
}
