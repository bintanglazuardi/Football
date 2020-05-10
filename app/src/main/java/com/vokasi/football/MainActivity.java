package com.vokasi.football;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EventAdapter.OnItemClickListener{
    public static final String EXTRA_HOME_TEAM_ID = "homeTeamId";
    public static final String EXTRA_AWAY_TEAM_ID = "awayTeamId";

    public static final String EXTRA_TANGGAL_TANDING = "tanggalTanding";
    public static final String EXTRA_JAM_TANDING = "jamTanding";
    public static final String EXTRA_HOME_TEAM_NAME = "homeTeamName";
    public static final String EXTRA_AWAY_TEAM_NAME = "awayTeamName";
    public static final String EXTRA_HOME_TEAM_SCORE = "homeTeamScore";
    public static final String EXTRA_AWAY_TEAM_SCORE = "awayTeamScore";
//    public static final String EXTRA_HOME_TEAM_LOGO = "homeTeamLogo";
//    public static final String EXTRA_AWAY_TEAM_LOGO = "awayTeamLogo";

    public static final String EXTRA_HOME_GOAL_DETAIL = "homeGoalDetail";
    public static final String EXTRA_HOME_SHOT = "homeShot";
    public static final String EXTRA_HOME_RED_CARD = "homeRedCard";
    public static final String EXTRA_HOME_YELLOW_CARD = "homeYellowCard";
    public static final String EXTRA_HOME_KEEPER = "homeKeeper";
    public static final String EXTRA_HOME_DEFENSE = "homeDefense";
    public static final String EXTRA_HOME_MIDFIELD = "homeMidfield";
    public static final String EXTRA_HOME_FORWARD = "homeForward";
    public static final String EXTRA_HOME_SUBTITUTE = "homeSubtitute";
    public static final String EXTRA_HOME_FORMATION = "homeFormation";

    public static final String EXTRA_AWAY_GOAL_DETAIL = "awayGoalDetail";
    public static final String EXTRA_AWAY_SHOT = "awayShot";
    public static final String EXTRA_AWAY_RED_CARD = "awayRedCard";
    public static final String EXTRA_AWAY_YELLOW_CARD = "awayYellowCard";
    public static final String EXTRA_AWAY_KEEPER = "awayKeeper";
    public static final String EXTRA_AWAY_DEFENSE = "awayDefense";
    public static final String EXTRA_AWAY_MIDFIELD = "awayMidfield";
    public static final String EXTRA_AWAY_FORWARD = "awayForward";
    public static final String EXTRA_AWAY_SUBTITUTE = "awaySubtitute";
    public static final String EXTRA_AWAY_FORMATION = "awayFormation";

    private RecyclerView mRecyclerView;
    private EventAdapter mEventAdapter;
    private ArrayList<EventItem> mEventList;
    private RequestQueue mRequestQueue;
    private RequestQueue mRequestQueueLast;
    private RequestQueue mSearchRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEventList = new ArrayList<>();

//        mRequestQueueLast = Volley.newRequestQueue(this);
//        parseLastJSON();

        mRequestQueue = Volley.newRequestQueue(this);
        parseNextJSON();

        mSearchRequestQueue = Volley.newRequestQueue(this);

    }

    private void parseLastJSON() {
        String url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("events");

                            for (int i = jsonArray.length()-1; i >=0; i-- ){
                                JSONObject event = jsonArray.getJSONObject(i);
                                String homeTeamId = event.getString("idHomeTeam");
                                String awayTeamId = event.getString("idAwayTeam");

                                String tanggalTanding = event.getString("strDate");
                                String jamTanding = event.getString("strTime");
                                String homeTeamName = event.getString("strHomeTeam");
                                String awayTeamName = event.getString("strAwayTeam");
                                int homeTeamScore = event.optInt("intHomeScore",0);
                                int awayTeamScore = event.optInt("intAwayScore",0);

//                                String homeTeamLogo = parseSearchJSON(homeTeamName);
//                                String awayTeamLogo = parseSearchJSON(awayTeamName);

//                                mEventList.add(new EventItem(tanggalTanding, jamTanding, homeTeamName,
//                                        awayTeamName, homeTeamScore, awayTeamScore));
                            }

                            mEventAdapter = new EventAdapter(MainActivity.this, mEventList);
                            mRecyclerView.setAdapter(mEventAdapter);
                            mEventAdapter.setOnItemClickListener(MainActivity.this);

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

        mRequestQueueLast.add(request);

    }

    private void parseNextJSON() {
        String url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("events");

                            for (int i = 0; i < jsonArray.length(); i++ ){
                                JSONObject event = jsonArray.getJSONObject(i);
                                String homeTeamId = event.getString("idHomeTeam");
                                String awayTeamId = event.getString("idAwayTeam");

                                String tanggalTanding = event.getString("strDate");
                                String jamTanding = event.getString("strTime");
                                String homeTeamName = event.getString("strHomeTeam");
                                String awayTeamName = event.getString("strAwayTeam");
                                int homeTeamScore = event.optInt("intHomeScore",0);
                                int awayTeamScore = event.optInt("intAwayScore",0);

                                String homeGoalDetail = event.optString("strHomeGoalDetails", "-");
                                int homeShot = event.optInt("intHomeShots", 0);
                                String homeRedCard = event.optString("strHomeRedCards", "-");
                                String homeYellowCard = event.optString("strHomeYellowCards", "-");
                                String homeKeeper = event.optString("strHomeLineupGoalkeeper", "-");
                                String homeDefense = event.optString("strHomeLineupDefense", "-");
                                String homeMidfield = event.optString("strHomeLineupMidfield", "-");
                                String homeForward = event.optString("strHomeLineupForward", "-");
                                String homeSubtitute = event.optString("strHomeLineupSubstitutes", "-");
                                int homeFormation = event.optInt("strHomeFormation", 0);

                                String awayGoalDetail = event.optString("strAwayGoalDetails", "-");
                                int awayShot = event.optInt("intAwayShots", 0);
                                String awayRedCard = event.optString("strAwayRedCards", "-");
                                String awayYellowCard = event.optString("strAwayYellowCards", "-");
                                String awayKeeper = event.optString("strAwayLineupGoalkeeper", "-");
                                String awayDefense = event.optString("strAwayLineupDefense", "-");
                                String awayMidfield = event.optString("strAwayLineupMidfield", "-");
                                String awayForward = event.optString("strAwayLineupForward", "-");
                                String awaySubtitute = event.optString("strAwayLineupSubstitutes", "-");
                                int awayFormation = event.optInt("strAwayFormation", 0);

//                                String homeTeamLogo = parseSearchJSON(homeTeamName);
//                                String awayTeamLogo = parseSearchJSON(awayTeamName);

//                                mEventList.add(new EventItem(tanggalTanding, jamTanding, homeTeamName,
//                                        awayTeamName, homeTeamScore, awayTeamScore));
                                mEventList.add(new EventItem(homeTeamId, awayTeamId,
                                        tanggalTanding, jamTanding, homeTeamName,
                                        awayTeamName, homeTeamScore, awayTeamScore,
                                        homeGoalDetail, homeShot, homeRedCard,
                                        homeYellowCard, homeKeeper, homeDefense,
                                        homeMidfield, homeForward, homeSubtitute,
                                        homeFormation, awayGoalDetail, awayShot,
                                        awayRedCard, awayYellowCard, awayKeeper,
                                        awayDefense, awayMidfield, awayForward,
                                        awaySubtitute, awayFormation));
                            }

                            mEventAdapter = new EventAdapter(MainActivity.this, mEventList);
                            mRecyclerView.setAdapter(mEventAdapter);
                            mEventAdapter.setOnItemClickListener(MainActivity.this);

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

        mRequestQueue.add(request);

    }

    private String[] parseTeamJSON(final String homeId, final String awayId) {
        String teamUrl = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328";
        final String[] arr = new String[2];

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, teamUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");

                            for (int i = 0; i < jsonArray.length(); i++ ){
                                JSONObject team = jsonArray.getJSONObject(i);
                                String homeTeamId = team.getString("idTeam");
                                String awayTeamId = team.getString("idTeam");

                                if (homeTeamId.equals(homeId)){
                                    String homeTeamLogo = team.getString("strTeamBadge");
                                    arr[0] = homeTeamLogo;
                                }
                                if (awayTeamId.equals(awayId)){
                                    String awayTeamLogo = team.getString("strTeamBadge");
                                    arr[1] = awayTeamLogo;
                                }
                            }

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
        mRequestQueue.add(request);

        return arr;
    }

    private String parseSearchJSON(String namaTeam) {
//        String searchUrl = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=Arsenal";
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

        return logoTeam[0];

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, EventDetailActivity.class);
        EventItem clickedItem = mEventList.get(position);

        detailIntent.putExtra(EXTRA_HOME_TEAM_ID, clickedItem.getmHomeTeamId());
        detailIntent.putExtra(EXTRA_AWAY_TEAM_ID, clickedItem.getmAwayTeamId());

        detailIntent.putExtra(EXTRA_TANGGAL_TANDING, clickedItem.getmTanggalTanding());
        detailIntent.putExtra(EXTRA_JAM_TANDING, clickedItem.getmJamTanding());
        detailIntent.putExtra(EXTRA_HOME_TEAM_NAME, clickedItem.getmHomeTeamName());
        detailIntent.putExtra(EXTRA_AWAY_TEAM_NAME, clickedItem.getmAwayTeamName());
        detailIntent.putExtra(EXTRA_HOME_TEAM_SCORE,  clickedItem.getmHomeTeamScore());
        detailIntent.putExtra(EXTRA_AWAY_TEAM_SCORE, clickedItem.getmAwayTeamScore());
//        detailIntent.putExtra(EXTRA_HOME_TEAM_LOGO,  clickedItem.getmHomeTeamLogo());
//        detailIntent.putExtra(EXTRA_AWAY_TEAM_LOGO, clickedItem.getmAwayTeamLogo());

        detailIntent.putExtra(EXTRA_HOME_GOAL_DETAIL,  clickedItem.getmHomeGoalDetail());
        detailIntent.putExtra(EXTRA_HOME_SHOT , clickedItem.getmHomeShot());
        detailIntent.putExtra(EXTRA_HOME_RED_CARD,  clickedItem.getmHomeRedCard());
        detailIntent.putExtra(EXTRA_HOME_YELLOW_CARD, clickedItem.getmHomeYellowCard());
        detailIntent.putExtra(EXTRA_HOME_KEEPER,  clickedItem.getmHomeKeeper());
        detailIntent.putExtra(EXTRA_HOME_DEFENSE, clickedItem.getmHomeDefense());
        detailIntent.putExtra(EXTRA_HOME_MIDFIELD,  clickedItem.getmHomeMidfield());
        detailIntent.putExtra(EXTRA_HOME_FORWARD, clickedItem.getmHomeForward());
        detailIntent.putExtra(EXTRA_HOME_SUBTITUTE,  clickedItem.getmHomeSubtitute());
        detailIntent.putExtra(EXTRA_HOME_FORMATION, clickedItem.getmHomeFormation());

        detailIntent.putExtra(EXTRA_AWAY_GOAL_DETAIL,  clickedItem.getmAwayGoalDetail());
        detailIntent.putExtra(EXTRA_AWAY_SHOT , clickedItem.getmAwayShot());
        detailIntent.putExtra(EXTRA_AWAY_RED_CARD,  clickedItem.getmAwayRedCard());
        detailIntent.putExtra(EXTRA_AWAY_YELLOW_CARD, clickedItem.getmAwayYellowCard());
        detailIntent.putExtra(EXTRA_AWAY_KEEPER,  clickedItem.getmAwayKeeper());
        detailIntent.putExtra(EXTRA_AWAY_DEFENSE, clickedItem.getmAwayDefense());
        detailIntent.putExtra(EXTRA_AWAY_MIDFIELD,  clickedItem.getmAwayMidfield());
        detailIntent.putExtra(EXTRA_AWAY_FORWARD, clickedItem.getmAwayForward());
        detailIntent.putExtra(EXTRA_AWAY_SUBTITUTE,  clickedItem.getmAwaySubtitute());
        detailIntent.putExtra(EXTRA_AWAY_FORMATION, clickedItem.getmAwayFormation());

//        Toast.makeText(this, tanggalTanding +"\n"+ jamTanding+"\n"+
//                "HOME : ---------------- \n" + homeTeamId +"\n"+ homeTeamName+"\n"+ homeTeamLogo+"\n"+
//                "AWAY : ---------------- \n" + awayTeamId +"\n"+ awayTeamName+"\n"+ awayTeamLogo+"\n", Toast.LENGTH_LONG).show();

        startActivity(detailIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_notification:
                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_language:
                Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(languageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
