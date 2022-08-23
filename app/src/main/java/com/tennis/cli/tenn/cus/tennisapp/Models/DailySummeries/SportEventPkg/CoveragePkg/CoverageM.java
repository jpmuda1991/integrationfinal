package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg.CoveragePkg;

public class CoverageM {

    private String type;
    private SportEventProperties sport_event_properties;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SportEventProperties getSport_event_properties() {
        return sport_event_properties;
    }

    public void setSport_event_properties(SportEventProperties sport_event_properties) {
        this.sport_event_properties = sport_event_properties;
    }
}
