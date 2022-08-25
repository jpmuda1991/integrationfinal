package com.tennis.cli.tenn.custm.tennisapp.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tennis.cli.tenn.custm.tennisapp.Activities.MainActivity;
import com.tennis.cli.tenn.custm.tennisapp.Adapters.RecentMatchesAdapter;
import com.tennis.cli.tenn.custm.tennisapp.Application.TennisApp;
import com.tennis.cli.tenn.custm.tennisapp.Models.DailySummeries.Summeraies;
import com.tennis.cli.tenn.custm.tennisapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TournamentFragment extends Fragment {

    private View view;
    private TextInputEditText tnNameEdit;
    private AppCompatAutoCompleteTextView countryAutoComplete;
    private AppCompatButton searchBtn;
    private RecyclerView recyclerView;
    private RecentMatchesAdapter recentMatchesAdapter;
    private List<Summeraies> summeraiesList;

    private List<String> countryNames;
    private ArrayAdapter<String> countriesAdapter;
    private String selectedCountry;


    public TournamentFragment() {
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
        view =  inflater.inflate(R.layout.fragment_tournament, container, false);

        countryNames = new ArrayList<>();

        summeraiesList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView_tournaments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);

        recentMatchesAdapter = new RecentMatchesAdapter(getActivity());

        recyclerView.setAdapter(recentMatchesAdapter);

        countryAutoComplete = view.findViewById(R.id.country_autocomplete);
        tnNameEdit = view.findViewById(R.id.tn_name);
        searchBtn = view.findViewById(R.id.search_btn);

        if (countryNames.size() > 0){

            countryNames.clear();
        }

        for (int i = 0 ; i < TennisApp.getFemaleRankingsList().size() ; i++){

            if (!countryNames.contains(TennisApp.getFemaleRankingsList().get(i).getCompetitor().getCountry())) {
                countryNames.add(TennisApp.getFemaleRankingsList().get(i).getCompetitor().getCountry());
            }
        }

        for (int i = 0 ; i < TennisApp.getMaleRankingsList().size() ; i++){

            if (!countryNames.contains(TennisApp.getMaleRankingsList().get(i).getCompetitor().getCountry())) {
                countryNames.add(TennisApp.getMaleRankingsList().get(i).getCompetitor().getCountry());
            }
        }


        Collections.sort(countryNames,String.CASE_INSENSITIVE_ORDER);


        countriesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,countryNames);
        countryAutoComplete.setAdapter(countriesAdapter);


        countryAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedCountry = adapterView.getItemAtPosition(i).toString();

            }
        });

        recentMatchesAdapter.setOnItemClickListener(new RecentMatchesAdapter.OnItemClickListener() {
            @Override
            public void OnCardClicked(Summeraies summeraies) {

                TennisApp.setMatchDetailFrom("Tournament");
                TennisApp.setSummeraies(summeraies);

                // goto All Details Fragment //

                if (MainActivity.getNavController() != null && MainActivity.getNavController().getCurrentDestination().getId() == R.id.nav_tournaments){

                    MainActivity.getNavController().navigate(R.id.action_nav_tournaments_to_matchDetailFragment);
                }

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String tnName = tnNameEdit.getText().toString().trim();
              String cnName = countryAutoComplete.getText().toString().trim();

                if (summeraiesList.size() > 0){

                    summeraiesList.clear();
                }


                if (!TextUtils.isEmpty(tnName)){

                    if (!TextUtils.isEmpty(cnName)){

                        // search with both country and tournament name //
                        System.out.println("SEARCH WITH BOTH");

                        for (int i = 0 ; i < TennisApp.getSummeraiesList().size() ; i++){


                           if (tnName.toLowerCase().contentEquals(TennisApp.getSummeraiesList().get(i).getSport_event().getSport_event_context().getCompetition().getName().toLowerCase()) ||
                                   TennisApp.getSummeraiesList().get(i).getSport_event().getSport_event_context().getCompetition().getName().toLowerCase().contains(tnName.toLowerCase())) {

                               System.out.println("TN NAMES FOUND");

                               try {

                                   if (cnName.toLowerCase().contentEquals(TennisApp.getSummeraiesList().get(i).getSport_event().getVenue().getCountry_name().toLowerCase())
                                           || TennisApp.getSummeraiesList().get(i).getSport_event().getVenue().getCountry_name().toLowerCase().contains(cnName.toLowerCase())){

                                       System.out.println("CN NAMES FOUND");

                                       summeraiesList.add(TennisApp.getSummeraiesList().get(i));

                                   }

                               }catch (NullPointerException ex){

                                   ex.printStackTrace();
                               }


                           }
                        }

                        System.out.println("BOTH LIST SIZE IS: " + summeraiesList.size());

                        recentMatchesAdapter.submitList(summeraiesList);
                        recentMatchesAdapter.notifyDataSetChanged();

                    }else{

                        // Search only with  Tournament name //
                        System.out.println("ONLY SEARCH WITH TOURNAMENT NAME");
                        for (int i = 0 ; i < TennisApp.getSummeraiesList().size() ; i++){

                            if (tnName.toLowerCase().contentEquals(TennisApp.getSummeraiesList().get(i).getSport_event().getSport_event_context().getCompetition().getName().toLowerCase()) ||
                                    TennisApp.getSummeraiesList().get(i).getSport_event().getSport_event_context().getCompetition().getName().toLowerCase().contains(tnName.toLowerCase())) {

                                summeraiesList.add(TennisApp.getSummeraiesList().get(i));

                            }
                        }

                        System.out.println("ONLY TOURNAMENT LIST SIZE IS: " + summeraiesList.size());

                        recentMatchesAdapter.submitList(summeraiesList);
                        recentMatchesAdapter.notifyDataSetChanged();
                    }

                }else{

                    if (!TextUtils.isEmpty(cnName)){

                        // SEARCH ACCOUNT TO COUNTRY //
                        System.out.println("ONLY SEARCH WITH Country NAME");

                        for (int i = 0 ; i < TennisApp.getSummeraiesList().size() ; i++){


                            try {

                                if (TennisApp.getSummeraiesList().get(i).getSport_event().getVenue().getCountry_name().toLowerCase().contentEquals(cnName)
                                        || TennisApp.getSummeraiesList().get(i).getSport_event().getVenue().getCountry_name().toLowerCase().contains(cnName.toLowerCase())){

                                    summeraiesList.add(TennisApp.getSummeraiesList().get(i));

                                }

                            }catch (NullPointerException ex){

                                ex.printStackTrace();
                            }


                        }

                        System.out.println("ONLY COUNTRY LIST SIZE IS: " + summeraiesList.size());

                        recentMatchesAdapter.submitList(summeraiesList);
                        recentMatchesAdapter.notifyDataSetChanged();


                    }else{

                        Toast.makeText(getActivity(), "Enter tournament or country to search", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }
}