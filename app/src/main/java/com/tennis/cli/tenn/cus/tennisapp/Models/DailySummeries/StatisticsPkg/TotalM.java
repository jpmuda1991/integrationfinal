package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.StatisticsPkg;

import java.util.List;

public class TotalM {

    private List<CompetitorsStatistics> competitors;


    public List<CompetitorsStatistics> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<CompetitorsStatistics> competitors) {
        this.competitors = competitors;
    }
}
