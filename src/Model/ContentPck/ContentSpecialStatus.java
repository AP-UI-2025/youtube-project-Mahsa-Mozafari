package Model.ContentPck;

public enum ContentSpecialStatus {
    SPECIAL('Y'),
    NOT_SPECIAL('N');
    private final char code;

    ContentSpecialStatus(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}
