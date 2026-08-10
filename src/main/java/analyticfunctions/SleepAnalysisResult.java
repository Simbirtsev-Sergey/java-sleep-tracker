package analyticfunctions;

import java.util.Objects;

public class SleepAnalysisResult {
    private final String description;
    private final Object value;


    public SleepAnalysisResult(final String description, final Object value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, value);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        SleepAnalysisResult other = (SleepAnalysisResult) obj;
        return this.description.equals(other.description) && this.value.equals(other.value);
    }
}
