package models;

import java.util.ArrayList;
import java.util.List;

public class AreaCheckResultRepository {
    private final List<AreaCheckResult> results;

    public AreaCheckResultRepository() {
        this.results = new ArrayList<>();
    }

    public void addResult(AreaCheckResult result) {
        synchronized (results) {
            results.add(result);
        }
    }

    public List<AreaCheckResult> getResults() {
        synchronized (results) {
            return new ArrayList<>(results);
        }
    }
}
