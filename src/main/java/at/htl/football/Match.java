package at.htl.football;

public class Match {
    private String homeName;
    private String guestName;
    private int homeGoals;
    private int guestGoals;

    public Match(String homeName, String guestName, int homeGoals, int guestGoals) {
        this.homeName = homeName;
        this.guestName = guestName;
        this.homeGoals = homeGoals;
        this.guestGoals = guestGoals;
    }

    public String getHomeName() {
        return homeName;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getGuestGoals() {
        return guestGoals;
    }

    public int getHomePoints() {
        if (this.homeGoals < this.guestGoals) {
            return 0;
        } else if (this.homeGoals > this.guestGoals) {
            return 3;
        }
        return 1;
    }

    public int getGuestPoints() {
        if (this.homeGoals < this.guestGoals) {
            return 3;
        } else if (this.homeGoals > this.guestGoals) {
            return 0;
        }
        return 1;
    }
}
