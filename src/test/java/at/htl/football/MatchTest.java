package at.htl.football;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class MatchTest {

    @Test
    void League_findOrCreateTeamWithExistingTeams(){
        League league = new League();

        league.addMatchResult(new Match("Team1", "Team2", 0, 0));
        List<Team> teams = league.getTable();

        assertThat(teams.get(0).getName(), is("Team1"));
        assertThat(teams.get(1).getName(), is("Team2"));
    }

    @Test
    void League_findOrCreateTeamWithNonExistingTeams() {
        League league = new League();

        league.addMatchResult(new Match("Team1", "Team2", 0, 0));
        league.addMatchResult(new Match("Team3", "Team4", 0, 0));
        List<Team> teams = league.getTable();

        assertThat(teams.get(2).getName(), is("Team3"));
        assertThat(teams.get(3).getName(), is("Team4"));
    }

    @Test
    void Team_addDefeat(){
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 0, 2));

        assertThat(team.getPoints(), is(0));
    }

    @Test
    void Team_addDraw(){
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 2));

        assertThat(team.getPoints(), is(1));
    }

    @Test
    void Team_addWin(){
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 0));

        assertThat(team.getPoints(), is(3));
    }

    @Test
    void Team_addMorePoints() {
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 2));
        team.addMatch(new Match("Team", "otherTeam", 2, 0));

        assertThat(team.getPoints(), is(4));
    }

    @Test
    void Team_addGoalsShot() {
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 2));
        team.addMatch(new Match("Team", "otherTeam", 2, 0));

        assertThat(team.getGoalsShot(), is(4));
    }

    @Test
    void Team_addGoalsReceived() {
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 2));
        team.addMatch(new Match("Team", "otherTeam", 2, 0));

        assertThat(team.getGoalsReceived(), is(2));
    }

    @Test
    void Team_getGoalDifference() {
        Team team = new Team("Team");
        team.addMatch(new Match("Team", "otherTeam", 2, 2));
        team.addMatch(new Match("Team", "otherTeam", 2, 0));

        assertThat(team.getGoalDifference(), is(2));
    }

    @Test
    void Team_compareTwoTeamsGreaterThan() {
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        team1.addMatch(new Match("Team1", "Team2", 0, 2));
        team2.addMatch(new Match("Team1", "Team2", 0, 2));

        assertThat(team1.compareTo(team2), greaterThan(0));
    }

    @Test
    void Team_compareTwoTeamsLessThan() {
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        team1.addMatch(new Match("Team1", "Team2", 2, 0));
        team2.addMatch(new Match("Team1", "Team2", 2, 0));

        assertThat(team1.compareTo(team2), lessThan(0));
    }

    @Test
    void Team_compareTwoTeamsEqual() {
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");

        team1.addMatch(new Match("Team1", "Team2", 2, 2));
        team2.addMatch(new Match("Team1", "Team2", 2, 2));

        assertThat(team1.compareTo(team2), is(0));
    }
}