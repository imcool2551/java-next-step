package chapter2.calculator;

import chapter2.calculator.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    public void zeroForEmptyOrNull() {
        assertEquals(0, calculator.add(""));
        assertEquals(0, calculator.add(null));
        assertEquals(0, calculator.add("  "));
    }

    @Test
    public void singleString() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void splitByDefault() {
        assertEquals(6.5, calculator.add("1.0,2.0:3.5"));
    }

    @Test
    public void splitBySeperator() {
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    public void throwWhenGivenNegativeNumber() {
        assertThrows(RuntimeException.class, () -> {
            double result = calculator.add("1,-2:3");
        });
    }

    @Test
    public void throwWhenGivenNonParsable() {
        assertThrows(NumberFormatException.class, () -> {
            calculator.add("1@,-2:3");
        });

        assertThrows(NumberFormatException.class, () -> {
            calculator.add("//;\n1,-2:3");
        });
    }
}
