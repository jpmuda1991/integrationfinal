package com.tennis.cli.tenn.custm.tennisapp.RetrofitUtils;

import com.tennis.cli.tenn.custm.tennisapp.Models.CModel.CountryModel;
import com.tennis.cli.tenn.custm.tennisapp.Models.DailySummeries.DailySummeraies;
import com.tennis.cli.tenn.custm.tennisapp.Models.Profile.CprofileModel;
import com.tennis.cli.tenn.custm.tennisapp.Models.RankingsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET
    Call<RankingsModel> rankings(@Url String url);

    @GET
    Call<CprofileModel> profile(@Url String url);

    @GET
    Call<DailySummeraies> getDailySummeries(@Url String url);

    @GET
    Call<List<CountryModel>> getConFlags(@Url String url);

}
