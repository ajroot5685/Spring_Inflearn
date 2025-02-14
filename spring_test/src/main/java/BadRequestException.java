public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("크기가 맞지 않음");
    }
}
