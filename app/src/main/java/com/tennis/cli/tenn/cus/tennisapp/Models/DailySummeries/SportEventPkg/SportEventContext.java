package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg;

import java.util.List;

public class SportEventContext {

    private Sport sport;
    private CategoryM category;
    private CompetitionM competition;
    private SeasonM  season;
    private StageM stage;
    private RoundM round;
    private List<GroupsM> groups;
    private ModeM mode;


    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public CategoryM getCategory() {
        return category;
    }

    public void setCategory(CategoryM category) {
        this.category = category;
    }

    public CompetitionM getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionM competition) {
        this.competition = competition;
    }

    public SeasonM getSeason() {
        return season;
    }

    public void setSeason(SeasonM season) {
        this.season = season;
    }

    public StageM getStage() {
        return stage;
    }

    public void setStage(StageM stage) {
        this.stage = stage;
    }

    public RoundM getRound() {
        return round;
    }

    public void setRound(RoundM round) {
        this.round = round;
    }

    public List<GroupsM> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsM> groups) {
        this.groups = groups;
    }

    public ModeM getMode() {
        return mode;
    }

    public void setMode(ModeM mode) {
        this.mode = mode;
    }
}
