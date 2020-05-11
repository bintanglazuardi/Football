package com.vokasi.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.vokasi.football.MainActivity.EXTRA_AWAY_DEFENSE;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_FORMATION;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_FORWARD;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_GOAL_DETAIL;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_KEEPER;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_MIDFIELD;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_RED_CARD;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_SHOT;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_SUBTITUTE;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_TEAM_ID;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_TEAM_NAME;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_TEAM_SCORE;
import static com.vokasi.football.MainActivity.EXTRA_AWAY_YELLOW_CARD;
import static com.vokasi.football.MainActivity.EXTRA_HOME_DEFENSE;
import static com.vokasi.football.MainActivity.EXTRA_HOME_FORMATION;
import static com.vokasi.football.MainActivity.EXTRA_HOME_FORWARD;
import static com.vokasi.football.MainActivity.EXTRA_HOME_GOAL_DETAIL;
import static com.vokasi.football.MainActivity.EXTRA_HOME_KEEPER;
import static com.vokasi.football.MainActivity.EXTRA_HOME_MIDFIELD;
import static com.vokasi.football.MainActivity.EXTRA_HOME_RED_CARD;
import static com.vokasi.football.MainActivity.EXTRA_HOME_SHOT;
import static com.vokasi.football.MainActivity.EXTRA_HOME_SUBTITUTE;
import static com.vokasi.football.MainActivity.EXTRA_HOME_TEAM_ID;
import static com.vokasi.football.MainActivity.EXTRA_HOME_TEAM_NAME;
import static com.vokasi.football.MainActivity.EXTRA_HOME_TEAM_SCORE;
import static com.vokasi.football.MainActivity.EXTRA_HOME_YELLOW_CARD;
import static com.vokasi.football.MainActivity.EXTRA_JAM_TANDING;
import static com.vokasi.football.MainActivity.EXTRA_TANGGAL_TANDING;

public class EventDetailActivity extends AppCompatActivity {
    private RequestQueue mSearchRequestQueue;

    String homeTeamLogo;
    String awayTeamLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        getSupportActionBar().setTitle("Detail Match");

        Intent intent = getIntent();

        String homeTeamId = intent.getStringExtra(EXTRA_HOME_TEAM_ID);
        String awayTeamId = intent.getStringExtra(EXTRA_AWAY_TEAM_ID);

        String tanggalTanding = intent.getStringExtra(EXTRA_TANGGAL_TANDING);
        String jamTanding = intent.getStringExtra(EXTRA_JAM_TANDING);

        mSearchRequestQueue = Volley.newRequestQueue(this);
        String homeTeamName = intent.getStringExtra(EXTRA_HOME_TEAM_NAME);
        homeTeamLogo = parseSearchJSON(homeTeamName);

        String awayTeamName = intent.getStringExtra(EXTRA_AWAY_TEAM_NAME);
        awayTeamLogo = parseSearchJSON(awayTeamName);


        int homeTeamScore = intent.getIntExtra(EXTRA_HOME_TEAM_SCORE,0);
        int awayTeamScore = intent.getIntExtra(EXTRA_AWAY_TEAM_SCORE,0);
//        String homeTeamLogo = intent.getStringExtra(EXTRA_HOME_TEAM_LOGO);
//        String awayTeamLogo = intent.getStringExtra(EXTRA_AWAY_TEAM_LOGO);

        String homeGoalDetail = intent.getStringExtra(EXTRA_HOME_GOAL_DETAIL);
        int homeShot = intent.getIntExtra(EXTRA_HOME_SHOT, 0);
        String homeRedCard = intent.getStringExtra(EXTRA_HOME_RED_CARD);
        String homeYellowCard = intent.getStringExtra(EXTRA_HOME_YELLOW_CARD);
        String homeKeeper = intent.getStringExtra(EXTRA_HOME_KEEPER);
        String homeDefense = intent.getStringExtra(EXTRA_HOME_DEFENSE);
        String homeMidfield = intent.getStringExtra(EXTRA_HOME_MIDFIELD);
        String homeForward = intent.getStringExtra(EXTRA_HOME_FORWARD);
        String homeSubtitute = intent.getStringExtra(EXTRA_HOME_SUBTITUTE);
        String homeFormation = intent.getStringExtra(EXTRA_HOME_FORMATION);

        String awayGoalDetail = intent.getStringExtra(EXTRA_AWAY_GOAL_DETAIL);
        int awayShot = intent.getIntExtra(EXTRA_AWAY_SHOT, 0);
        String awayRedCard = intent.getStringExtra(EXTRA_AWAY_RED_CARD);
        String awayYellowCard = intent.getStringExtra(EXTRA_AWAY_YELLOW_CARD);
        String awayKeeper = intent.getStringExtra(EXTRA_AWAY_KEEPER);
        String awayDefense = intent.getStringExtra(EXTRA_AWAY_DEFENSE);
        String awayMidfield = intent.getStringExtra(EXTRA_AWAY_MIDFIELD);
        String awayForward = intent.getStringExtra(EXTRA_AWAY_FORWARD);
        String awaySubtitute = intent.getStringExtra(EXTRA_AWAY_SUBTITUTE);
        String awayFormation = intent.getStringExtra(EXTRA_AWAY_FORMATION);

        //==========================================================================================

        TextView detailTanggalTanding = findViewById(R.id.detail_tanggal_tanding);
        TextView detailJamTanding = findViewById(R.id.detail_jam_tanding);
        TextView detailHomeTeamName = findViewById(R.id.detail_home_team_name);
        TextView detailAwayTeamName = findViewById(R.id.detail_away_team_name);
        TextView detailHomeTeamScore = findViewById(R.id.detail_home_team_score);
        TextView detailAwayTeamScore = findViewById(R.id.detail_away_team_score);
        ImageView detailHomeTeamLogo = findViewById(R.id.detail_home_team_logo);
        ImageView detailAwayTeamLogo = findViewById(R.id.detail_away_team_logo);

        TextView detailHomeGoal = findViewById(R.id.home_goal_detail);
        TextView detailHomeShot = findViewById(R.id.home_shots);
        TextView detailHomeRedCard = findViewById(R.id.home_red_card);
        TextView detailHomeYellowCard = findViewById(R.id.home_yellow_card);
        TextView detailHomeKeeper = findViewById(R.id.home_keeper);
        TextView detailHomeDefense = findViewById(R.id.home_defense);
        TextView detailHomeMidfield = findViewById(R.id.home_midfield);
        TextView detailHomeForward = findViewById(R.id.home_forward);
        TextView detailHomeSubtitute = findViewById(R.id.home_subtitute);
        TextView detailHomeFormation = findViewById(R.id.home_formation);

        TextView detailAwayGoal = findViewById(R.id.away_goal_detail);
        TextView detailAwayShot = findViewById(R.id.away_shots);
        TextView detailAwayRedCard = findViewById(R.id.away_red_card);
        TextView detailAwayYellowCard = findViewById(R.id.away_yellow_card);
        TextView detailAwayKeeper = findViewById(R.id.away_keeper);
        TextView detailAwayDefense = findViewById(R.id.away_defense);
        TextView detailAwayMidfield = findViewById(R.id.away_midfield);
        TextView detailAwayForward = findViewById(R.id.away_forward);
        TextView detailAwaySubtitute = findViewById(R.id.away_subtitute);
        TextView detailAwayFormation = findViewById(R.id.away_formation);

        //==========================================================================================

        detailTanggalTanding.setText(tanggalTanding + "   |   ");
        detailJamTanding.setText(jamTanding);
        detailHomeTeamName.setText(homeTeamName);
        detailAwayTeamName.setText(awayTeamName);
        detailHomeTeamScore.setText("" + homeTeamScore);
        detailAwayTeamScore.setText("" + awayTeamScore);
        Picasso.with(this).load("https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png")
                .fit().centerInside().into(detailHomeTeamLogo);
        Picasso.with(this).load(awayTeamLogo).fit().centerInside().into(detailAwayTeamLogo);

        detailHomeGoal.setText(homeGoalDetail);
        detailHomeShot.setText("" + homeShot);
        detailHomeRedCard.setText(homeRedCard);
        detailHomeYellowCard.setText(homeYellowCard);
        detailHomeKeeper.setText(homeKeeper);
        detailHomeDefense.setText(homeDefense);
        detailHomeMidfield.setText(homeMidfield);
        detailHomeForward.setText(homeForward);
        detailHomeSubtitute.setText(homeSubtitute);
        detailHomeFormation.setText(homeFormation);

        detailAwayGoal.setText(awayGoalDetail);
        detailAwayShot.setText("" + awayShot);
        detailAwayRedCard.setText(awayRedCard);
        detailAwayYellowCard.setText(awayYellowCard);
        detailAwayKeeper.setText(awayKeeper);
        detailAwayDefense.setText(awayDefense);
        detailAwayMidfield.setText(awayMidfield);
        detailAwayForward.setText(awayForward);
        detailAwaySubtitute.setText(awaySubtitute);
        detailAwayFormation.setText(awayFormation);
    }

    private String parseSearchJSON(String namaTeam) {
        String searchUrl = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t="+namaTeam;
        final String[] logoTeam = new String[1];

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, searchUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");
                            JSONObject team = jsonArray.getJSONObject(0);
                            logoTeam[0] = team.getString("strTeamBadge");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mSearchRequestQueue.add(request);

        return "https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png";

    }

    public void strAwayTeam(View view) {
//        Toast.makeText(this, "Home team : " + homeTeamLogo, Toast.LENGTH_SHORT).show();
    }

    public void strHomeTeam(View view) {
//        Toast.makeText(this, "Away team : " + awayTeamLogo, Toast.LENGTH_SHORT).show();
    }
}
