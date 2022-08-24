package com.tennis.cli.tenn.cus.tennisapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.tennis.cli.tenn.cus.tennisapp.Models.DailySummeries.Summeraies;
import com.tennis.cli.tenn.cus.tennisapp.R;

public class RecentMatchesAdapter extends ListAdapter<Summeraies,RecentMatchesAdapter.RecentMatViewHolder> {

    private Context context;

    public interface  OnItemClickListener{

        void OnCardClicked(Summeraies summeraies);
    }

    public OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecentMatchesAdapter(Context context) {
        super(diffCallback);

        this.context = context;
    }

    public static DiffUtil.ItemCallback<Summeraies> diffCallback = new DiffUtil.ItemCallback<Summeraies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Summeraies oldItem, @NonNull Summeraies newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Summeraies oldItem, @NonNull Summeraies newItem) {
            return oldItem.getSport_event() == newItem.getSport_event() &&
                    oldItem.getStatistics()  == newItem.getStatistics() &&
                    oldItem.getSport_event_status() == newItem.getSport_event_status();
        }
    };

    @NonNull
    @Override
    public RecentMatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_adpt_lyt,parent,false);
        return new RecentMatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentMatViewHolder holder, int position) {

        Summeraies summeraies = getItem(position);

        String winner_id = summeraies.getSport_event_status().getWinner_id();
        String type = summeraies.getSport_event().getSport_event_context().getCompetition().getType();

        if (type.contentEquals("doubles")){

            holder.teamOrPlayerOneTxt.setText("Players 1");
            holder.teamOrPlayerTwoTxt.setText("Players 2");

        }else{

            holder.teamOrPlayerOneTxt.setText("Player 1");
            holder.teamOrPlayerTwoTxt.setText("Player 2");
        }


        holder.tNameTxt.setText(summeraies.getSport_event().getSport_event_context().getCompetition().getName());
        holder.couNametxt.setText(summeraies.getSport_event().getVenue().getCountry_name());
        holder.citNameTxt.setText(summeraies.getSport_event().getVenue().getCity_name());

        if (!TextUtils.isEmpty(winner_id) && winner_id.contentEquals(summeraies.getSport_event().getCompetitors().get(0).getId())) {

            holder.playerOneTxt.setTextColor(context.getResources().getColor(R.color.teal_200));

        }else{

            holder.playerOneTxt.setTextColor(context.getResources().getColor(R.color.orange_def));

        }

        if (!TextUtils.isEmpty(winner_id) && winner_id.contentEquals(summeraies.getSport_event().getCompetitors().get(1).getId())) {

            holder.playerTwoTxt.setTextColor(context.getResources().getColor(R.color.teal_200));

        }else{

            holder.playerTwoTxt.setTextColor(context.getResources().getColor(R.color.orange_def));

        }


        holder.playerOneTxt.setText(summeraies.getSport_event().getCompetitors().get(0).getName());
        holder.playerTwoTxt.setText(summeraies.getSport_event().getCompetitors().get(1).getName());

        holder.roundTxt.setText(summeraies.getSport_event().getSport_event_context().getRound().getName());

    }

    public class RecentMatViewHolder extends RecyclerView.ViewHolder{

        private CardView mainCard;
        private AppCompatTextView tNameTxt,couNametxt,citNameTxt,teamOrPlayerOneTxt,teamOrPlayerTwoTxt,playerOneTxt,playerTwoTxt,roundTxt;

        public RecentMatViewHolder(@NonNull View itemView) {
            super(itemView);

            tNameTxt = itemView.findViewById(R.id.tournament_name);
            couNametxt = itemView.findViewById(R.id.country_name);
            citNameTxt = itemView.findViewById(R.id.city_name);
            teamOrPlayerOneTxt = itemView.findViewById(R.id.team_or_plyr_one_txt);
            teamOrPlayerTwoTxt = itemView.findViewById(R.id.team_or_plyr_two_txt);
            playerOneTxt = itemView.findViewById(R.id.players_one_name);
            playerTwoTxt = itemView.findViewById(R.id.players_two_name);
            roundTxt = itemView.findViewById(R.id.round_name);

            mainCard = itemView.findViewById(R.id.main_card);

            mainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int p = getAdapterPosition();

                    if (onItemClickListener != null && p != RecyclerView.NO_POSITION){

                        onItemClickListener.OnCardClicked(getItem(p));
                    }
                }
            });
        }
    }
}
