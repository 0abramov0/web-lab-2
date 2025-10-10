package models;

public class ErrorMessage {
    private boolean error;
    private final StringBuilder errorMessage;

    public ErrorMessage() {
        error = false;
        errorMessage = new StringBuilder();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void addMessage(String message) {
        errorMessage.append(message).append("\n");
    }

    @Override
    public String toString() {
        return errorMessage.toString();
    }
}
