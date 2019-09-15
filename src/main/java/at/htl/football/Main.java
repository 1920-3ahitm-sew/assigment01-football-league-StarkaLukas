package at.htl.football;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    final static String FILENAME = "bundesliga-1819.csv";

    public static void main(String[] args) {
        League league = new League();
        readFromFile(league);
        printTable(league.getTable());
    }

    private static void readFromFile(League league) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(FILENAME))) {
            String line = bufferedReader.readLine();

            int placeHomeName = getPlace("HomeTeam", line);
            int placeGuestName = getPlace("GuestTeam", line);
            int placeHomeGoals = getPlace("HomeGoals", line);
            int placeGuestGoals = getPlace("GuestGoals", line);

            while ((line = bufferedReader.readLine()) != null) {
                league.addMatchResult(new Match(line.split(";")[placeHomeName], line.split(";")[placeGuestName], Integer.parseInt(line.split(";")[placeHomeGoals]), Integer.parseInt(line.split(";")[placeGuestGoals])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getPlace(String searchedString, String line) {
        String[] parts = line.split(";");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals(searchedString)) {
                return i;
            }
        }

        return -1;
    }

    private static void printTable(List<Team> teams) {
        System.out.printf("Team                Pts  W   D   L  GF  GA  GD%n");
        for (Team team : teams) {
            System.out.print(team.getName());
            for (int i = 0; i < 20 - team.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.printf("%2d  %2d  %2d  %2d  %2d  %2d  %2d%n", team.getPoints(), team.getWins(), team.getDraws(), team.getDefeats(), team.getGoalsShot(), team.getGoalsReceived(), team.getGoalDifference());
        }
        System.out.printf("Pts...Points, W...Won, D...Drawn, L...Lost%nGF...Goals for, GA...Goals against, GD...Goal difference");
        System.out.println();
    }
}
