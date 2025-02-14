import java.util.Scanner;

public class CalculationRequestReader {

    public CalculationRequest read() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("두개 숫자 입력");
        String s = scanner.nextLine();
        String[] parts = s.split(" ");

        return new CalculationRequest(parts);
    }
}
