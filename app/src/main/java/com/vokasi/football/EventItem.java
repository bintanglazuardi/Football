package com.vokasi.football;

public class EventItem {
    private String mHomeTeamId;
    private String mAwayTeamId;

    private String mTanggalTanding;
    private String mJamTanding;
    private String mHomeTeamName;
    private String mAwayTeamName;
    private int mHomeTeamScore;
    private int mAwayTeamScore;

    private String mHomeGoalDetail;
    private int mHomeShot;
    private String mHomeRedCard;
    private String mHomeYellowCard;
    private String mHomeKeeper;
    private String mHomeDefense;
    private String mHomeMidfield;
    private String mHomeForward;
    private String mHomeSubtitute;
    private int mHomeFormation;

    private String mAwayGoalDetail;
    private int mAwayShot;
    private String mAwayRedCard;
    private String mAwayYellowCard;
    private String mAwayKeeper;
    private String mAwayDefense;
    private String mAwayMidfield;
    private String mAwayForward;
    private String mAwaySubtitute;
    private int mAwayFormation;

    private String mHomeTeamLogo;
    private String mAwayTeamLogo;


    public EventItem(String mHomeTeamId, String mAwayTeamId,
                     String mTanggalTanding, String mJamTanding, String mHomeTeamName,
                     String mAwayTeamName, int mHomeTeamScore, int mAwayTeamScore,
                     String mHomeGoalDetail, int mHomeShot, String mHomeRedCard,
                     String mHomeYellowCard, String mHomeKeeper, String mHomeDefense,
                     String mHomeMidfield, String mHomeForward, String mHomeSubtitute,
                     int mHomeFormation, String mAwayGoalDetail, int mAwayShot,
                     String mAwayRedCard, String mAwayYellowCard, String mAwayKeeper,
                     String mAwayDefense, String mAwayMidfield, String mAwayForward,
                     String mAwaySubtitute, int mAwayFormation) {
        this.mHomeTeamId = mHomeTeamId;
        this.mAwayTeamId = mAwayTeamId;
        this.mTanggalTanding = mTanggalTanding;
        this.mJamTanding = mJamTanding;
        this.mHomeTeamName = mHomeTeamName;
        this.mAwayTeamName = mAwayTeamName;
        this.mHomeTeamScore = mHomeTeamScore;
        this.mAwayTeamScore = mAwayTeamScore;
        this.mHomeGoalDetail = mHomeGoalDetail;
        this.mHomeShot = mHomeShot;
        this.mHomeRedCard = mHomeRedCard;
        this.mHomeYellowCard = mHomeYellowCard;
        this.mHomeKeeper = mHomeKeeper;
        this.mHomeDefense = mHomeDefense;
        this.mHomeMidfield = mHomeMidfield;
        this.mHomeForward = mHomeForward;
        this.mHomeSubtitute = mHomeSubtitute;
        this.mHomeFormation = mHomeFormation;
        this.mAwayGoalDetail = mAwayGoalDetail;
        this.mAwayShot = mAwayShot;
        this.mAwayRedCard = mAwayRedCard;
        this.mAwayYellowCard = mAwayYellowCard;
        this.mAwayKeeper = mAwayKeeper;
        this.mAwayDefense = mAwayDefense;
        this.mAwayMidfield = mAwayMidfield;
        this.mAwayForward = mAwayForward;
        this.mAwaySubtitute = mAwaySubtitute;
        this.mAwayFormation = mAwayFormation;
    }

//    public EventItem(String tanggalTanding, String jamTanding, String homeTeamName,
//                     String awayTeamName, int homeTeamScore, int awayTeamScore) {
//        this.mTanggalTanding = mTanggalTanding;
//        this.mJamTanding = mJamTanding;
//        this.mHomeTeamName = mHomeTeamName;
//        this.mAwayTeamName = mAwayTeamName;
//        this.mHomeTeamScore = mHomeTeamScore;
//        this.mAwayTeamScore = mAwayTeamScore;
//    }

    public String getmHomeTeamId() {
        return mHomeTeamId;
    }

    public String getmAwayTeamId() {
        return mAwayTeamId;
    }

    public String getmTanggalTanding() {
        return mTanggalTanding;
    }

    public String getmJamTanding() {
        return mJamTanding;
    }

    public String getmHomeTeamName() {
        return mHomeTeamName;
    }

    public String getmAwayTeamName() {
        return mAwayTeamName;
    }

    public int getmHomeTeamScore() {
        return mHomeTeamScore;
    }

    public int getmAwayTeamScore() {
        return mAwayTeamScore;
    }

    public String getmHomeTeamLogo() {
        return mHomeTeamLogo;
    }

    public String getmAwayTeamLogo() {
        return mAwayTeamLogo;
    }

    public String getmHomeGoalDetail() {
        return mHomeGoalDetail;
    }

    public int getmHomeShot() {
        return mHomeShot;
    }

    public String getmHomeRedCard() {
        return mHomeRedCard;
    }

    public String getmHomeYellowCard() {
        return mHomeYellowCard;
    }

    public String getmHomeKeeper() {
        return mHomeKeeper;
    }

    public String getmHomeDefense() {
        return mHomeDefense;
    }

    public String getmHomeMidfield() {
        return mHomeMidfield;
    }

    public String getmHomeForward() {
        return mHomeForward;
    }

    public String getmHomeSubtitute() {
        return mHomeSubtitute;
    }

    public int getmHomeFormation() {
        return mHomeFormation;
    }

    public String getmAwayGoalDetail() {
        return mAwayGoalDetail;
    }

    public int getmAwayShot() {
        return mAwayShot;
    }

    public String getmAwayRedCard() {
        return mAwayRedCard;
    }

    public String getmAwayYellowCard() {
        return mAwayYellowCard;
    }

    public String getmAwayKeeper() {
        return mAwayKeeper;
    }

    public String getmAwayDefense() {
        return mAwayDefense;
    }

    public String getmAwayMidfield() {
        return mAwayMidfield;
    }

    public String getmAwayForward() {
        return mAwayForward;
    }

    public String getmAwaySubtitute() {
        return mAwaySubtitute;
    }

    public int getmAwayFormation() {
        return mAwayFormation;
    }
}
