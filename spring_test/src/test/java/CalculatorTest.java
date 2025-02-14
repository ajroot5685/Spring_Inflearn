import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void 덧셈_연산이_가능() {
        //given
        long num1 = 2;
        String operator = "+";
        long num2 = 3;
        Calculator calculator = new Calculator();

        //when
        long result = calculator.calculate(num1, operator, num2);

        //then
        assertEquals(5, result);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void 뺄셈_연산이_가능() {
        //given
        long num1 = 2;
        String operator = "-";
        long num2 = 3;
        Calculator calculator = new Calculator();

        //when
        long result = calculator.calculate(num1, operator, num2);

        //then
        assertEquals(-1, result);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void 곱셈_연산이_가능() {
        //given
        long num1 = 2;
        String operator = "*";
        long num2 = 3;
        Calculator calculator = new Calculator();

        //when
        long result = calculator.calculate(num1, operator, num2);

        //then
        assertEquals(6, result);
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void 나눗셈_연산이_가능() {
        //given
        long num1 = 6;
        String operator = "/";
        long num2 = 3;
        Calculator calculator = new Calculator();

        //when
        long result = calculator.calculate(num1, operator, num2);

        //then
        assertEquals(2, result);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void 잘못된_연산자가_들어와서_오류_발생() {
        //given
        long num1 = 6;
        String operator = "x";
        long num2 = 3;
        Calculator calculator = new Calculator();

        //when

        //then
        assertThrows(InvalidOperatorException.class, ()->{
            calculator.calculate(num1, operator, num2);
        });
    }
}
