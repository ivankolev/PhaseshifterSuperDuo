package barqsoft.footballscores;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilities
{
    public static final int CHAMPIONS_LEAGUE = 405;
    public static final int BUNDESLIGA1 = 394;
    public static final int BUNDESLIGA2 = 395;
    public static final int LIGUE1 = 396;
    public static final int LIGUE2 = 397;
    public static final int PREMIER_LEAGUE = 398;
    public static final int PRIMERA_DIVISION = 399;
    public static final int SEGUNDA_DIVISION = 400;
    public static final int SERIE_A = 401;
    public static final int PRIMEIRA_LIGA = 402;
    public static final int BUNDESLIGA3 = 403;
    public static final int EREDIVISIE = 404;
    private Resources res;

    public Utilities(Context context) {
        this.res  = context.getResources();
    }

    public String getLeague(int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return res.getString(R.string.group_serie_a);
            case PREMIER_LEAGUE : return res.getString(R.string.group_premier_league);
            case CHAMPIONS_LEAGUE : return res.getString(R.string.group_champions_league);
            case PRIMERA_DIVISION : return res.getString(R.string.group_primera_division);
            case BUNDESLIGA1 : return res.getString(R.string.group_bundesliga);
            case BUNDESLIGA2 : return res.getString(R.string.group_bundesliga2);
            case LIGUE1 : return res.getString(R.string.group_ligue1);
            case LIGUE2 : return res.getString(R.string.group_ligue2);
            case SEGUNDA_DIVISION : return res.getString(R.string.group_segunda_division);
            case PRIMEIRA_LIGA : return res.getString(R.string.group_primeira_liga);
            case BUNDESLIGA3 : return res.getString(R.string.group_bundesliga3);
            case EREDIVISIE : return res.getString(R.string.group_eredivisie);
            default: return res.getString(R.string.group_unknown);
        }
    }
    public String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return res.getString(R.string.champs_group_stage);
            }
            else if(match_day == 7 || match_day == 8)
            {
                return res.getString(R.string.champs_first_round);
            }
            else if(match_day == 9 || match_day == 10)
            {
                return res.getString(R.string.champs_quarter_final);
            }
            else if(match_day == 11 || match_day == 12)
            {
                return res.getString(R.string.champs_semi_final);
            }
            else
            {
                return res.getString(R.string.champs_final);
            }
        }
        else
        {
            return res.getString(R.string.matchday) + String.valueOf(match_day);
        }
    }

    public String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return res.getString(R.string.dash);
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
        }
    }
}
