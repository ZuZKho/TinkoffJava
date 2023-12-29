package edu.hw9.task1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Collector {

    private final ConcurrentHashMap<String, Statistics> metrics = new ConcurrentHashMap<>();
    private final ExecutorService executorService;

    public Collector(int nThreads) {
        this.executorService = Executors.newFixedThreadPool(nThreads);
    }

    private void processData(String metricName, double[] data) {
        if (data.length == 0) {
            return;
        }
        Statistics currentStat = new Statistics(
            Arrays.stream(data).sum(),
            data.length,
            Arrays.stream(data).average().getAsDouble(),
            Arrays.stream(data).max().getAsDouble(),
            Arrays.stream(data).min().getAsDouble()
        );

        metrics.merge(
            metricName,
            currentStat,
            (stat1, stat2) -> new Statistics(
                stat1.sum() + stat2.sum(),
                stat1.count() + stat2.count(),
                (stat1.sum() + stat2.sum()) / (stat1.count() + stat2.count()),
                Math.max(stat1.maximum(), stat2.maximum()),
                Math.min(stat1.minimum(), stat2.minimum())
            )
        );
    }

    public void push(String metricName, double[] data) {
        executorService.execute(() -> {
            processData(metricName, data);
        });
    }

    public HashMap<String, Statistics> getStatistics() {
        synchronized (metrics) {
            return new HashMap<String, Statistics>(metrics);
        }
    }

}
