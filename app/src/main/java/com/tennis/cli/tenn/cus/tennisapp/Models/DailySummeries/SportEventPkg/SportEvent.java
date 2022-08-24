package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg;

import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg.CompetitorsPkg.CompetitorsM;
import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg.CoveragePkg.CoverageM;

import java.util.List;

public class SportEvent {

    private String id;
    private String start_time;
    private String start_time_confirmed;
    private boolean estimated;
    private SportEventContext sport_event_context;
    private CoverageM coverage;
    private List<CompetitorsM> competitors;
    private VenueM venue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time_confirmed() {
        return start_time_confirmed;
    }

    public void setStart_time_confirmed(String start_time_confirmed) {
        this.start_time_confirmed = start_time_confirmed;
    }

    public boolean isEstimated() {
        return estimated;
    }

    public void setEstimated(boolean estimated) {
        this.estimated = estimated;
    }

    public SportEventContext getSport_event_context() {
        return sport_event_context;
    }

    public void setSport_event_context(SportEventContext sport_event_context) {
        this.sport_event_context = sport_event_context;
    }

    public CoverageM getCoverage() {
        return coverage;
    }

    public void setCoverage(CoverageM coverage) {
        this.coverage = coverage;
    }

    public List<CompetitorsM> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<CompetitorsM> competitors) {
        this.competitors = competitors;
    }

    public VenueM getVenue() {
        return venue;
    }

    public void setVenue(VenueM venue) {
        this.venue = venue;
    }
}
