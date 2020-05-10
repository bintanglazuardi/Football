package com.vokasi.football;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context mContext;
    private ArrayList<EventItem> mEventList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public EventAdapter(Context context, ArrayList<EventItem> eventList){
        mContext = context;
        mEventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventItem currentItem = mEventList.get(position);

        String tanggalTanding = currentItem.getmTanggalTanding();
        String jamTanding = currentItem.getmJamTanding();
        String homeTeamName = currentItem.getmHomeTeamName();
        String awayTeamName = currentItem.getmAwayTeamName();
        int homeTeamScore = currentItem.getmHomeTeamScore();
        int awayTeamScore = currentItem.getmAwayTeamScore();
        String homeTeamLogo = currentItem.getmHomeTeamLogo();
        String awayTeamLogo = currentItem.getmAwayTeamLogo();

        holder.mTanggalTanding.setText(tanggalTanding + "   |   ");
        holder.mJamTanding.setText(jamTanding);
        holder.mHomeTeamName.setText(homeTeamName);
        holder.mAwayTeamName.setText(awayTeamName);
        holder.mHomeTeamScore.setText("" + homeTeamScore);
        holder.mAwayTeamScore.setText("" + awayTeamScore);
//        Picasso.with(mContext).load(homeTeamLogo).fit().centerInside().into(holder.mHomeTeamLogo);
//        Picasso.with(mContext).load(awayTeamLogo).fit().centerInside().into(holder.mAwayTeamLogo);

    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView mTanggalTanding;
        public TextView mJamTanding;
        public TextView mHomeTeamName;
        public TextView mAwayTeamName;
        public TextView mHomeTeamScore;
        public TextView mAwayTeamScore;
        public ImageView mHomeTeamLogo;
        public ImageView mAwayTeamLogo;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            mTanggalTanding = itemView.findViewById(R.id.tanggal_tanding);
            mJamTanding = itemView.findViewById(R.id.jam_tanding);
            mHomeTeamName = itemView.findViewById(R.id.home_team_name);
            mAwayTeamName = itemView.findViewById(R.id.away_team_name);
            mHomeTeamScore = itemView.findViewById(R.id.home_team_score);
            mAwayTeamScore = itemView.findViewById(R.id.away_team_score);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
