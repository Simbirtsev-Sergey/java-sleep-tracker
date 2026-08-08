package analyticFunctions;

public class SleepAnalysisResult {
    private final String description;
    private final Integer value;


    public SleepAnalysisResult(final String description, final Integer value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }
}
