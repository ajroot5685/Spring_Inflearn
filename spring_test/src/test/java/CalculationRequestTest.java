import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculationRequestTest {

    @Test
    public void 유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"2", "+", "3"};

        // when
        CalculationRequest calculationRequest = new CalculationRequest(parts);

        // then
        assertEquals(2, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(3, calculationRequest.getNum2());
    }

    @Test
    public void 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"232", "+", "312"};

        // when
        CalculationRequest calculationRequest = new CalculationRequest(parts);

        // then
        assertEquals(232, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(312, calculationRequest.getNum2());
    }

    @Test
    public void 유효하지_않은_숫자길이가_들어오면_에러() {
        // given
        String[] parts = {"232", "+"};

        // when
        // then
        assertThrows(BadRequestException.class, () -> new CalculationRequest(parts));
    }

    @Test
    public void 유효하지_않은_연산자가_들어오면_에러() {
        // given
        String[] parts = {"232", "x", "312"};

        // when
        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(parts));
    }

    @Test
    public void 유효하지_않은_길이의_연산자가_들어오면_에러() {
        // given
        String[] parts = {"232", "+-", "312"};

        // when
        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(parts));
    }
}