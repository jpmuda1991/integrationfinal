package com.tennis.cli.tenn.custm.tennisapp.Models.Profile;

import com.tennis.cli.tenn.custm.tennisapp.Models.Competitor;

public class CprofileModel {

    private Competitor competitor;

    private InfoProfile info;

    public Competitor getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    public InfoProfile getInfo() {
        return info;
    }

    public void setInfo(InfoProfile info) {
        this.info = info;
    }
}
