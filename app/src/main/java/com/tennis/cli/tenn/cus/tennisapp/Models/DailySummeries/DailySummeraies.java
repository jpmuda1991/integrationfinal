package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries;

import java.util.List;

public class DailySummeraies {

    private String generated_at;

    private List<Summeraies> summaries;


    public String getGenerated_at() {
        return generated_at;
    }

    public void setGenerated_at(String generated_at) {
        this.generated_at = generated_at;
    }

    public List<Summeraies> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summeraies> summaries) {
        this.summaries = summaries;
    }
}
