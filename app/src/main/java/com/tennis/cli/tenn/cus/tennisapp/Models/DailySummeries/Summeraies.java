package com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries;

import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventPkg.SportEvent;
import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.SportEventStatusPkg.SportEventStatus;
import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.StatisticsPkg.StatisticsM;

public class Summeraies {

    private SportEvent sport_event;
    private SportEventStatus sport_event_status;
    private StatisticsM statistics;


    public SportEvent getSport_event() {
        return sport_event;
    }

    public void setSport_event(SportEvent sport_event) {
        this.sport_event = sport_event;
    }

    public SportEventStatus getSport_event_status() {
        return sport_event_status;
    }

    public void setSport_event_status(SportEventStatus sport_event_status) {
        this.sport_event_status = sport_event_status;
    }

    public StatisticsM getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsM statistics) {
        this.statistics = statistics;
    }
}
