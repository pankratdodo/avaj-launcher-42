public class ValidationException extends Exception{

    String mess;

    public ValidationException(String mess)
    {
        this.mess = mess;
        System.err.println(mess);
    }
}
