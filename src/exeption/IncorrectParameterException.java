package exeption;

public class IncorrectParameterException extends Exception{
    private String parameter;

    public IncorrectParameterException(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        return "Параметр "+parameter+" задан неверно";
    }
}
