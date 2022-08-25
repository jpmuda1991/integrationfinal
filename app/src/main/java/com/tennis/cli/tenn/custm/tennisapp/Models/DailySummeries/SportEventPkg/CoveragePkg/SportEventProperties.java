package com.tennis.cli.tenn.custm.tennisapp.Models.DailySummeries.SportEventPkg.CoveragePkg;

public class SportEventProperties {

    private boolean enhanced_stats;
    private String scores;
    private String detailed_serve_outcomes;
    private boolean play_by_play;


    public boolean isEnhanced_stats() {
        return enhanced_stats;
    }

    public void setEnhanced_stats(boolean enhanced_stats) {
        this.enhanced_stats = enhanced_stats;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getDetailed_serve_outcomes() {
        return detailed_serve_outcomes;
    }

    public void setDetailed_serve_outcomes(String detailed_serve_outcomes) {
        this.detailed_serve_outcomes = detailed_serve_outcomes;
    }

    public boolean isPlay_by_play() {
        return play_by_play;
    }

    public void setPlay_by_play(boolean play_by_play) {
        this.play_by_play = play_by_play;
    }
}
