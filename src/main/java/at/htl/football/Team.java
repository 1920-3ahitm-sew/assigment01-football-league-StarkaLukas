package at.htl.football;

public class Team implements Comparable<Team> {
    private String name;
    private int points;
    private int wins;
    private int draws;
    private int defeats;
    private int goalsShot;
    private int goalsReceived;

    public Team(String name) {
        this.name = name;
    }

    public void addMatch(Match match) {
        if (this.name.equals(match.getHomeName())) {
            addStats(match.getHomePoints(), match.getHomeGoals(), match.getGuestGoals());
        } else if (this.name.equals(match.getGuestName())) {
            addStats(match.getGuestPoints(), match.getGuestGoals(), match.getHomeGoals());
        }
    }

    private void addStats(int pointsTeam, int goalsForTeam, int goalsReceivedFrom) {
        if (pointsTeam == 3) {
            this.wins++;
            this.points += 3;
        } else if (pointsTeam == 1) {
            this.draws++;
            this.points += 1;
        }else{
            this.defeats++;
        }

        this.goalsShot += goalsForTeam;
        this.goalsReceived += goalsReceivedFrom;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getGoalsShot() {
        return goalsShot;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public int getGoalDifference() {
        return this.goalsShot - this.goalsReceived;
    }

    public int compareTo(Team o) {
        if (this.getPoints() < o.getPoints()) {
            return 1;
        } else if (this.getPoints() > o.getPoints()) {
            return -1;
        }else{
            return o.getGoalDifference() - this.getGoalDifference();
        }
    }
}
