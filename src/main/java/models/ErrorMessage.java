package models;

public class ErrorMessage {
    private boolean isError;
    private final StringBuilder errorMessage;

    public ErrorMessage() {
        isError = false;
        errorMessage = new StringBuilder();
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void addMessage(String message) {
        errorMessage.append(message).append("\n");
    }

    @Override
    public String toString() {
        return errorMessage.toString();
    }
}
