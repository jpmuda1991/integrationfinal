package com.tennis.cli.tenn.cus.tennisapp.Fragments;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.tennis.cli.tenn.cus.tennisapp.Activities.MainActivity;
import com.tennis.cli.tenn.cus.tennisapp.Adapters.RecentMatchesAdapter;
import com.tennis.cli.tenn.cus.tennisapp.Adapters.TopMaleAdapter;
import com.tennis.cli.tenn.cus.tennisapp.Application.TennisApp;
import com.tennis.cli.tenn.cus.tennisapp.Application.TennisCommon;
import com.tennis.cli.tenn.cus.tennisapp.Models.CModel.CListModel;
import com.tennis.cli.tenn.cus.tennisapp.Models.CModel.CountryModel;
import com.tennis.cli.tenn.cus.tennisapp.Models.CompetitorsArr;
import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.DailySummeraies;
import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.Summeraies;
import com.tennis.cli.tenn.cus.tennisapp.Models.PlayersModel;
import com.tennis.cli.tenn.cus.tennisapp.Models.RankingsArr;
import com.tennis.cli.tenn.cus.tennisapp.Models.RankingsModel;
import com.tennis.cli.tenn.cus.tennisapp.R;
import com.tennis.cli.tenn.cus.tennisapp.RetrofitUtils.Api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RankingFragment extends Fragment {

    private View view;
    private RecyclerView recyclerViewMale,recyclerViewFemale,recyclerViewSummeries;
    private TopMaleAdapter topMaleAdapter;
    private RecentMatchesAdapter recentMatchesAdapter;
    private TopMaleAdapter topFemaleAdapter;
    private List<PlayersModel> playersModelListMale,playersModelListFemale;
    private TennisCommon tennisCommon;
    private NestedScrollView scrollView;
    private AppCompatTextView maleTxt,femaleTxt;
    private boolean ismaleImgVisible = false;
    private boolean isfemaleImgVisible = false;
    private String genM,genF;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_ranking, container, false);



        tennisCommon = new TennisCommon();

        if (playersModelListMale == null){

            playersModelListMale = new ArrayList<>();
        }

        if (playersModelListFemale == null){

            playersModelListFemale = new ArrayList<>();
        }


        scrollView = view.findViewById(R.id.scrollView);
        maleTxt = view.findViewById(R.id.top_male_txt);
        femaleTxt = view.findViewById(R.id.top_female_txt);

        recyclerViewSummeries = view.findViewById(R.id.recyclerView_tournaments);
        recyclerViewSummeries.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewSummeries.setHasFixedSize(false);

        recentMatchesAdapter = new RecentMatchesAdapter(getActivity());

        recyclerViewSummeries.setAdapter(recentMatchesAdapter);

        recyclerViewMale = view.findViewById(R.id.recyclerView_top_male);
        recyclerViewMale.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMale.setHasFixedSize(true);

        topMaleAdapter = new TopMaleAdapter(getActivity());

        recyclerViewMale.setAdapter(topMaleAdapter);

        recyclerViewFemale= view.findViewById(R.id.recyclerView_top_female);
        recyclerViewFemale.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFemale.setHasFixedSize(true);

        topFemaleAdapter = new TopMaleAdapter(getActivity());
        recyclerViewFemale.setAdapter(topFemaleAdapter);

        topMaleAdapter.submitList(playersModelListMale);
        topFemaleAdapter.submitList(playersModelListFemale);

        recentMatchesAdapter.setOnItemClickListener(new RecentMatchesAdapter.OnItemClickListener() {
            @Override
            public void OnCardClicked(Summeraies summeraies) {

                TennisApp.setMatchDetailFrom("Rankings");
                TennisApp.setSummeraies(summeraies);

                // goto All Details Fragment //

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_matchDetailFragment);
                }
            }
        });


        topMaleAdapter.setOnItemClickListener(new TopMaleAdapter.OnItemClickListener() {
            @Override
            public void onFlagIsClicked(PlayersModel playersModel) {

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setSamelMaleOrFemale("MALE");


                    TennisApp.setSameCountry(playersModel.getCountry());
                    TennisApp.setSameCityFrom("RANKING");
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_sameCtyPlayersFragment2);
                }


            }

            @Override
            public void onPlayerClicked(PlayersModel playersModel) {

                System.out.println("MALE CARD IS CLICKED");

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setFrom("RANKING");
                    TennisApp.setSelectedPlayerModel(playersModel);
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_profileFragment);
                }

            }

            @Override
            public void onPlayerNameIsClicked(PlayersModel playersModel) {

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setFrom("RANKING");
                    TennisApp.setSelectedPlayerModel(playersModel);
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_profileFragment);
                }

            }
        });

        topFemaleAdapter.setOnItemClickListener(new TopMaleAdapter.OnItemClickListener() {
            @Override
            public void onFlagIsClicked(PlayersModel playersModel) {

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setSamelMaleOrFemale("FEMALE");
                    TennisApp.setSameCountry(playersModel.getCountry());
                    TennisApp.setSameCityFrom("RANKING");
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_sameCtyPlayersFragment2);
                }


            }

            @Override
            public void onPlayerClicked(PlayersModel playersModel) {

                System.out.println("FEMALE CARD IS CLICKED");

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setFrom("RANKING");
                    TennisApp.setSelectedPlayerModel(playersModel);
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_profileFragment);
                }

            }

            @Override
            public void onPlayerNameIsClicked(PlayersModel playersModel) {

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_rankings){

                    TennisApp.setFrom("RANKING");
                    TennisApp.setSelectedPlayerModel(playersModel);
                    MainActivity.getNavController().navigate(R.id.action_nav_rankings_to_profileFragment);
                }

            }
        });

        getDataFromTennisV3();


        return view;
    }


    private void getDataFromTennisV3() {

        if (tennisCommon.isNetWorkAvailable(getActivity())) {



                if (TennisApp.getFlagsList() != null && TennisApp.getFlagsList().size() <= 0){

                OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS).readTimeout(40,TimeUnit.SECONDS).build();

                String BASE_URL =  "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                Api service = retrofit.create(Api.class);

                service.getConFlags("dist/index.json").enqueue(new Callback<List<CountryModel>>() {
                    @Override
                    public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {

                        List<CountryModel> list = response.body();
                        System.out.println("COUNTRY LIST SIZE IS: " + list.size());
                        TennisApp.setFlagsList(list);
                    }

                    @Override
                    public void onFailure(Call<List<CountryModel>> call, Throwable t) {

                        System.out.println("FLAG EXCEPTION IS: " + t.getMessage());

                    }
                });



            }

            if (TennisApp.getMaleRankingsList() != null && TennisApp.getMaleRankingsList().size() <= 0) {

                ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Getting Rankings....");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(40,TimeUnit.SECONDS).build();

                String BASE_URL =  "https://api.sportradar.com/tennis/trial/v3/en/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api service = retrofit.create(Api.class);

                service.rankings("rankings.json?api_key=bsrp4bft6arabtrrdwjupf25").enqueue(new Callback<RankingsModel>() {
                    @Override
                    public void onResponse(Call<RankingsModel> call, Response<RankingsModel> response) {

                        RankingsModel rankingsModel = response.body();

                        try {

                            List<RankingsArr> rankingsList =  rankingsModel.getRankings();

                            System.out.println("LIST OF Competitor_rankings size : " + rankingsList.get(0).getCompetitor_rankings().size());



                            TennisApp.setMaleRankingsList(rankingsList.get(0).getCompetitor_rankings());
                            TennisApp.setFemaleRankingsList(rankingsList.get(1).getCompetitor_rankings());

                            if (playersModelListMale == null){

                                playersModelListMale = new ArrayList<>();
                            }

                            if (playersModelListMale.size() > 0){

                                playersModelListMale.clear();
                            }

                            if (playersModelListFemale == null){

                                playersModelListFemale = new ArrayList<>();
                            }

                            if (playersModelListFemale.size() > 0){

                                playersModelListFemale.clear();
                            }




                            for (int i = 0 ; i < 20 ; i++){

                                playersModelListMale.add(new PlayersModel(TennisApp.getMaleRankingsList().get(i).getCompetitor().getId(),"","",TennisApp.getMaleRankingsList().get(i).getCompetitor().getName(),"",TennisApp.getMaleRankingsList().get(i).getPoints()+"",TennisApp.getMaleRankingsList().get(i).getCompetitor().getCountry(),"M"));
                            }

                            for (int i = 0 ; i < 20 ; i++){

                                playersModelListFemale.add(new PlayersModel(TennisApp.getFemaleRankingsList().get(i).getCompetitor().getId(),"","",TennisApp.getFemaleRankingsList().get(i).getCompetitor().getName(),"",TennisApp.getFemaleRankingsList().get(i).getPoints()+"",TennisApp.getFemaleRankingsList().get(i).getCompetitor().getCountry(),"F"));
                            }


                            topMaleAdapter.notifyDataSetChanged();
                            topFemaleAdapter.notifyDataSetChanged();


                            if (TennisApp.getSummeraiesList() != null && TennisApp.getSummeraiesList().size() <= 0) {

                                new getSyncoSummeries().execute();

                            }else{

                                List<Summeraies> summeraies = new ArrayList<>();

                                for (int i = 0; i < 4; i++){

                                    summeraies.add(TennisApp.getSummeraiesList().get(i));
                                }

                                recentMatchesAdapter.submitList(summeraies);
                                recentMatchesAdapter.notifyDataSetChanged();

                            }

                            if(dialog.isShowing()) {
                                dialog.dismiss();
                            }


                        }catch (NullPointerException ex){

                            ex.printStackTrace();

                            if(dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        }


                    }

                    @Override
                    public void onFailure(Call<RankingsModel> call, Throwable t) {

                        if(dialog.isShowing()) {
                            dialog.dismiss();
                        }

                        tennisCommon.inFormUser(getActivity(),t.getMessage());
                    }
                });


            }else{

                // set already getted list to adapter //

                if (playersModelListMale == null){

                    playersModelListMale = new ArrayList<>();
                }

                if (playersModelListMale.size() > 0){

                    playersModelListMale.clear();
                }

                if (playersModelListFemale == null){

                    playersModelListFemale = new ArrayList<>();
                }

                if (playersModelListFemale.size() > 0){

                    playersModelListFemale.clear();
                }


                for (int i = 0 ; i < 20 ; i++){

                    playersModelListMale.add(new PlayersModel(TennisApp.getMaleRankingsList().get(i).getCompetitor().getId(),"","",TennisApp.getMaleRankingsList().get(i).getCompetitor().getName(),"",TennisApp.getMaleRankingsList().get(i).getPoints()+"",TennisApp.getMaleRankingsList().get(i).getCompetitor().getCountry(),"M"));
                }

                for (int i = 0 ; i < 20 ; i++){

                    playersModelListFemale.add(new PlayersModel(TennisApp.getFemaleRankingsList().get(i).getCompetitor().getId(),"","",TennisApp.getFemaleRankingsList().get(i).getCompetitor().getName(),"",TennisApp.getFemaleRankingsList().get(i).getPoints()+"",TennisApp.getFemaleRankingsList().get(i).getCompetitor().getCountry(),"F"));
                }

                topMaleAdapter.submitList(playersModelListMale);
                topFemaleAdapter.submitList(playersModelListFemale);

                if (TennisApp.getSummeraiesList() != null && TennisApp.getSummeraiesList().size() <= 0) {

                    new getSyncoSummeries().execute();

                }else{

                    List<Summeraies> summeraies = new ArrayList<>();

                    for (int i = 0; i < 4; i++){

                        summeraies.add(TennisApp.getSummeraiesList().get(i));
                    }

                    recentMatchesAdapter.submitList(summeraies);
                    recentMatchesAdapter.notifyDataSetChanged();

                }



            }


        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        topMaleAdapter = null;
        topFemaleAdapter = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        topMaleAdapter = null;
        topFemaleAdapter = null;
    }

    public class getSyncoSummeries extends AsyncTask<Void,Void,Void>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Getting Summeries....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            System.out.println("DO IN BACKGROUND WORKING");


            List<Summeraies> summeraies = new ArrayList<>();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = calendar.getTime();

            String formatDate = fmt.format(date);

            System.out.println("FORMAT DATE IS: " + formatDate);

            OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS).build();

            String dummyDate = "2022-08-20";
            String BASE_URL = "https://api.sportradar.com/tennis/trial/v3/en/schedules/"+dummyDate+"/";

            System.out.println("BASE URL SUMMERIES IS: " + BASE_URL);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api service = retrofit.create(Api.class);

            try {

                Response<DailySummeraies> res = service.getDailySummeries("summaries.json?api_key=bsrp4bft6arabtrrdwjupf25&start=0&limit=200").execute();
                DailySummeraies summeraies1 = res.body();
                TennisApp.setSummeraiesList(summeraies1.getSummaries());

            } catch (IOException e) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null && dialog.isShowing()){

                            dialog.dismiss();
                        }
                    }
                });

                System.out.println("exceptinn summer: " + e.getMessage());
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if (dialog != null && dialog.isShowing()){

                dialog.dismiss();
            }

            System.out.println("on post execute list: " + TennisApp.getSummeraiesList().size());

            List<Summeraies> summeraies = new ArrayList<>();

            for (int i = 0; i < 4; i++){

                summeraies.add(TennisApp.getSummeraiesList().get(i));
            }

            System.out.println("on post execute org list: " + summeraies.size());
            recentMatchesAdapter.submitList(summeraies);
            recentMatchesAdapter.notifyDataSetChanged();
        }
    }












    ///


//    List<Summeraies> summeraies = new ArrayList<>();
//
//    Calendar calendar = Calendar.getInstance();
//    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//    Date date = calendar.getTime();
//
//    String formatDate = fmt.format(date);
//
//                System.out.println("FORMAT DATE IS: " + formatDate);
//
//    // get Daily Summaries //
//    ProgressDialog dialog = new ProgressDialog(getActivity());
//                dialog.setMessage("Getting Daily Summeries....");
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.show();
//
//    OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS).readTimeout(40,TimeUnit.SECONDS).build();
//
//    String BASE_URL = "https://api.sportradar.com/tennis/trial/v3/en/schedules/"+formatDate+"/";
//
//                System.out.println("BASE URL SUMMERIES IS: " + BASE_URL);
//
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    Api service = retrofit.create(Api.class);
//
//            try {
//
//        Response<DailySummeraies> res = service.getDailySummeries("summaries.json?api_key=bsrp4bft6arabtrrdwjupf25&start=0&limit=4").execute();
//        DailySummeraies summeraies1 = res.body();
//        TennisApp.setSummeraiesList(summeraies1.getSummaries());
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
////            service.getDailySummeries("summaries.json?api_key=bsrp4bft6arabtrrdwjupf25&start=0&limit=4").enqueue(new Callback<DailySummeraies>() {
////                    @Override
////                    public void onResponse(Call<DailySummeraies> call, Response<DailySummeraies> response) {
////
////                        DailySummeraies summeraies =  response.body();
////
////                        TennisApp.setSummeraiesList(summeraies.getSummaries());
////
////                        System.out.println("SUMMERIES SIZE IS: " + TennisApp.getSummeraiesList().size());
////
////                        recentMatchesAdapter.submitList(summeraies.getSummaries());
////                        recentMatchesAdapter.notifyDataSetChanged();
////
////                        if(dialog.isShowing()) {
////                            dialog.dismiss();
////                        }
////
////
////
////                    }
////
////                    @Override
////                    public void onFailure(Call<DailySummeraies> call, Throwable t) {
////
////                        System.out.println("EXCEPTION SUMMERIES IS: " + t.getMessage());
////
////                        if(dialog.isShowing()) {
////                            dialog.dismiss();
////                        }
////
////                    }
////                });
//
//
//            return null;
//}
//
//
//    @Override
//    protected void onPostExecute(List<Summeraies> summeraies) {
//        super.onPostExecute(summeraies);
//
//
//
//    }
//}
}