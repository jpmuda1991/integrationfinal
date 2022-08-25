package com.tennis.cli.tenn.custm.tennisapp.Models.DailySummeries.SportEventStatusPkg;

import java.util.List;

public class SportEventStatus {

    private String status;
    private String match_status;
    private int home_score;
    private String away_score;
    private String winner_id;
    private List<PeriodScoresM> period_scores;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatch_status() {
        return match_status;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public String getAway_score() {
        return away_score;
    }

    public void setAway_score(String away_score) {
        this.away_score = away_score;
    }

    public String getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(String winner_id) {
        this.winner_id = winner_id;
    }

    public List<PeriodScoresM> getPeriod_scores() {
        return period_scores;
    }

    public void setPeriod_scores(List<PeriodScoresM> period_scores) {
        this.period_scores = period_scores;
    }
}
