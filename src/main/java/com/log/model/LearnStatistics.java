package com.log.model;

public class LearnStatistics extends LearnStatisticsKey {
    private String runtime;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }
}