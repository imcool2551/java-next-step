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
    public void zeroForEmptyString() {
        // given
        String emptyString = "";

        // when
        double result = calculator.add(emptyString);

        // then
        assertEquals(0, result);
    }

    @Test
    public void splitByDefault() {
        // given
        String string = "1.0,2.0:3.5";

        // when
        double result = calculator.add(string);

        // then
        assertEquals(6.5, result);
    }

    @Test
    public void splitBySeperator() {
        // given
        String string = "//;\n1;2;3";

        // when
        double result = calculator.add(string);

        // then
        assertEquals(6, result);
    }

    @Test
    public void throwWhenGivenNegativeNumber() {
        // given
        String string = "1,-2:3";

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            double result = calculator.add(string);
        });

        // then
        assertEquals("음수는 계산할 수 없습니다", e.getMessage());
    }

    @Test
    public void throwWhenGivenNonParsable() {
        // given
        String string = "1@,-2:3";

        // when + then
        assertThrows(NumberFormatException.class, () -> {
            calculator.add(string);
        });
    }
}
