package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.StatisticsPkg;

public class CompetitorsStatistics {

    private String id;
    private String name;
    private String abbreviation;
    private String qualifier;
    private StatisticsMatch statistics;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public StatisticsMatch getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsMatch statistics) {
        this.statistics = statistics;
    }
}
