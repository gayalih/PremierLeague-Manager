package com.example;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager {
    private final ArrayList<FootballClub> premierLeagueTeams;
    private final ArrayList<Match> matches;
    private final ArrayList<Match> temp;
    Scanner sc=new Scanner(System.in);
    Random rand=new Random();
    public ArrayList<Match> getMatchesList() {
        return matches;
    }
    public ArrayList<FootballClub> getPremierLeagueTeams() {
        return premierLeagueTeams;
    }
    public PremierLeagueManager(){
        premierLeagueTeams=new ArrayList<FootballClub>();
        matches=new ArrayList<Match>();
        temp=new ArrayList<Match>();
    }
    //method for the console menu
    public void cliMenu() {
        int menuInput=0;
        System.out.println("--------------\n1 - Show GUI (JavaFX)\n2 - Create and add new football club\n3 - Delete existing football club" +
                "\n4 - Display statistics for selected club\n5 - Display premier league table\n6 - Add a played match" +
                "\n7 - Save in a file\n8 - Load from file\n9 - Show GUI (angular)\n0 - Quit\n--------------");
        menuInput=0;
        while(true) {
            System.out.print("Input Number: ");
            String input = sc.next();
            try{
                menuInput=Integer.parseInt(input);
                break;
            }catch(NumberFormatException ne){
                System.out.print("Enter a valid integer\n");
            }
        }
        switch (menuInput) {
            case 1:
                showGUI();
                break;
            case 2:
                addFootballClub();
                break;
            case 3:
                deleteClub();
                break;
            case 4:
                displayStats();
                break;
            case 5:
                displayPremierLeagueTable();
                break;
            case 6:
                addMatch();
                break;
            case 7:
                saveInfile();
                break;
            case 8:
                loadFromFile();
                cliMenu();
                break;
            case 9:
                Runtime rt = Runtime.getRuntime();
                String url = "http://localhost:4200/";
                try {
                    rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cliMenu();
                break;
            case 0:
                System.out.println("--------------\nProgram has been ended by user");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
                cliMenu();
        }
    }
    //method to add club to the league from console
    public void addFootballClub() {
        //since max number of clubs in the league is 20
        if (premierLeagueTeams.size()<20) {
            System.out.print("\nName of the Club: ");
            sc.nextLine();
            String clubName = sc.nextLine().toUpperCase();
            for (FootballClub fb : premierLeagueTeams){
                if (fb.getClubName().equals(clubName)){
                    System.out.println("\n** "+clubName+" is already in the Premier League **");
                    addFootballClub();
                }
            }
            System.out.print("Location of the Club: ");
            String location = sc.nextLine().toUpperCase();
            FootballClub footballClub = new FootballClub(clubName, location);
            premierLeagueTeams.add(footballClub);
        }
        else{
            System.out.println("\n** There are no free slots in the Premier League **");
        }
        cliMenu();
    }
    //method to delete club from console
    public void deleteClub() {
        System.out.print("\nName of the club that should be removed: ");
        sc.nextLine();
        String deleteName=sc.nextLine().toUpperCase();
        for(int i=0; i < premierLeagueTeams.size(); i++){
            if(premierLeagueTeams.get(i).getClubName().equals(deleteName)){
                premierLeagueTeams.remove(i);
                System.out.println("\n** "+deleteName+" has been removed from the Premier League **");
                cliMenu();
                return;
            }
        }
        //if the specified club isn't in the league
        System.out.println("\n** This club doesn't exist in the League **");
        cliMenu();
    }
    //display stats of specified club in console
    public void displayStats(){
        System.out.print("\nName of the club that should show statistics: ");
        sc.nextLine();
        String statName=sc.nextLine().toUpperCase();
        for (FootballClub premierLeagueTeam : premierLeagueTeams) {
            if (premierLeagueTeam.getClubName().equals(statName)) {
                System.out.println(premierLeagueTeam.showStats());
                cliMenu();
                return;
            }
        }
        //if the specified club isn't in the league
        System.out.println("\n** This club doesn't exist in the League **");
        cliMenu();
    }
    //display premier league table sorted by points in console
    public void displayPremierLeagueTable() {
        FootballClub footballClub=new FootballClub();
        footballClub.pointSort(premierLeagueTeams);
        System.out.println("\n--Premier League Table--");
        for (FootballClub premierLeagueTeam : premierLeagueTeams) {
            System.out.println(premierLeagueTeam.tableString());
        }
        cliMenu();
    }
    //add match from console
    public void addMatch() {
        Date date1=new Date();
        date1.setMonth(sc);
        int month=date1.getMonth();
        date1.setDay(sc);
        int day=date1.getDay();
        date1.setYear(sc);
        int year=date1.getYear();
        Date date =new Date(day,month,year);
        System.out.print("\nHome Team: ");
        sc.nextLine();
        String ht = sc.nextLine().toUpperCase();
        if (premierLeagueTeams.size()==0){
            System.out.println("No clubs in the League!");
        }
        for(int i=0; i < premierLeagueTeams.size(); i++){
            //check if both clubs are the same
            if(premierLeagueTeams.get(i).getClubName().equals(ht)){
                System.out.print("Away Team: ");
                String at =sc.nextLine().toUpperCase();
                if(at.equals(ht)){
                    System.out.println("Cannot enter same team!");
                    cliMenu();
                    return;
                }
                for(int j=0; j < premierLeagueTeams.size(); j++){
                    if(premierLeagueTeams.get(j).getClubName().equals(at) && !at.equals(ht)){
                        int hGoalsScored=rand.nextInt(11);
                        int aGoalsScored=rand.nextInt(11);
                        Match match=new Match();
                        match.addMatch(premierLeagueTeams,i,j,hGoalsScored,aGoalsScored);
                        String homeTeamName=premierLeagueTeams.get(i).getClubName();
                        String awayTeamName=premierLeagueTeams.get(j).getClubName();
                        match= new Match(homeTeamName,awayTeamName,hGoalsScored,aGoalsScored,date);
                        matches.add(match);
                        System.out.print("\n"+match.toString());
                        cliMenu();
                        return;
                    }
                }
            }
        }
        System.out.println("This club doesn't exist in the league");
        cliMenu();
    }
    //method to save data in file
    public void saveInfile() {
        try{
            FileOutputStream fos=new FileOutputStream("premierLeagueTeams.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            for (FootballClub fb : premierLeagueTeams){
                oos.writeObject(fb);
            }
            oos.flush();
            oos.close();
            FileOutputStream fos2=new FileOutputStream("matches.txt");
            ObjectOutputStream oos2=new ObjectOutputStream(fos2);
            for (Match match : matches){
                oos2.writeObject(match);
            }
            oos2.flush();
            oos2.close();
            System.out.println("Data has been Saved");
        }
        catch (Exception ex){ex.printStackTrace();}
        cliMenu();
    }
    //method to retrieve data from file
    public void loadFromFile() {
        premierLeagueTeams.clear();
        matches.clear();
        try{
            FileInputStream fis = new FileInputStream("premierLeagueTeams.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (;;) {
                try {
                    premierLeagueTeams.add((FootballClub) ois.readObject());
                } catch (Exception e) {
                    break;
                }
            }
            FileInputStream fis2 = new FileInputStream("matches.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            for (;;) {
                try {
                    matches.add((Match) ois2.readObject());
                } catch (Exception e) {
                    break;
                }
            }
            System.out.println("\nPrevious data has been Loaded");
        }
        catch(Exception ex){System.out.println("No Previous Data to Load");}
    }
    //javafx gui start
    public void showGUI() {
        AnchorPane welRoot=new AnchorPane();
        welRoot.setStyle("-fx-background-color:#340D39;-fx-border-width:5;-fx-border-color:black");/*17E183*/
        Stage welStage=new Stage();
        welStage.setResizable(false);
        welStage.setTitle("Premier League");
        Scene scene=new Scene(welRoot,500,355);
        welStage.setScene(scene);
        welStage.show();
        Label title=new Label("P R E M I E R   L E A G U E");
        title.setLayoutX(70); title.setLayoutY(25); title.setStyle("-fx-text-fill:white;-fx-font:32 Roboto;-fx-font-weight:bold;-fx-letter-spacing:1");
        Button premierButton=new Button("LEAGUE TABLE");
        premierButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        premierButton.setLayoutX(175); premierButton.setLayoutY(100); premierButton.setPrefSize(170,40);
        premierButton.setOnMouseEntered(e -> premierButton.setStyle("-fx-background-color:#76B396;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        premierButton.setOnMouseExited(e -> premierButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        Button addMatchButton=new Button("ADD MATCH");
        addMatchButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        addMatchButton.setLayoutX(175); addMatchButton.setLayoutY(170); addMatchButton.setPrefSize(170,40);
        addMatchButton.setOnMouseEntered(e -> addMatchButton.setStyle("-fx-background-color:#76B396;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        addMatchButton.setOnMouseExited(e -> addMatchButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        Button showMatchButton=new Button("SHOW MATCHES");
        showMatchButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        showMatchButton.setLayoutX(175); showMatchButton.setLayoutY(240); showMatchButton.setPrefSize(170,40);
        showMatchButton.setOnMouseEntered(e -> showMatchButton.setStyle("-fx-background-color:#76B396;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        showMatchButton.setOnMouseExited(e -> showMatchButton.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        Button console=new Button("CONSOLE");
        console.setLayoutX(400); console.setLayoutY(305); console.setPrefSize(80,30);
        console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        console.setOnMouseEntered(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        console.setOnMouseExited(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        welRoot.getChildren().addAll(premierButton,addMatchButton,showMatchButton,console,title);
        premierButton.setOnAction(event -> {
            welStage.close();
            premierGUI();
        });
        addMatchButton.setOnAction(event -> {
            welStage.close();
            genMatchGUI();
        });
        showMatchButton.setOnAction(event -> {
            welStage.close();
            showMatchGUI();
        });
        console.setOnAction(event -> {
            welStage.close();
            cliMenu();
        });
    }
    //javafx premier league table
    public void premierGUI(){
        AnchorPane root1=new AnchorPane();
        root1.setStyle("-fx-background-color:#340D39;-fx-border-width:5;-fx-border-color:black");
        Stage tableStage=new Stage();
        tableStage.setResizable(false);
        tableStage.setTitle("Table");
        Scene scene1 = new Scene(root1, 665, 600);
        tableStage.setScene(scene1);
        tableStage.show();
        Button console=new Button("CONSOLE");
        console.setLayoutX(570); console.setLayoutY(550);console.setPrefSize(80,30);
        console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        console.setOnMouseEntered(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        console.setOnMouseExited(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Button back=new Button("BACK");
        back.setLayoutX(20);back.setLayoutY(550);back.setPrefSize(80,30);
        back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        back.setOnMouseEntered(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        back.setOnMouseExited(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Label head=new Label("Premier League Table");
        head.setLayoutX(230); head.setLayoutY(15); head.setStyle("-fx-font:20 Roboto;-fx-font-weight:bold;-fx-text-fill:white");
        Label sortBy=new Label("Sort By:");
        sortBy.setLayoutX(140); sortBy.setLayoutY(535); sortBy.setStyle("-fx-font:16 Roboto;-fx-font-weight:bold;-fx-text-fill:white");
        Button point=new Button("Points");
        point.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        point.setOnMouseEntered(e -> point.setStyle("-fx-background-color:#76B396;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        point.setOnMouseExited(e -> point.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        point.setLayoutX(215); point.setLayoutY(530); point.setPrefSize(70,30);
        Button goals=new Button("Goals Scored");
        goals.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        goals.setOnMouseEntered(e -> goals.setStyle("-fx-background-color:#76B396;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        goals.setOnMouseExited(e -> goals.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        goals.setLayoutX(305); goals.setLayoutY(530); goals.setPrefSize(100,30);
        Button wins=new Button("Wins");
        wins.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        wins.setOnMouseEntered(e -> wins.setStyle("-fx-background-color:#76B396;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        wins.setOnMouseExited(e -> wins.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        wins.setLayoutX(425); wins.setLayoutY(530); wins.setPrefSize(70,30);
        TableView tableView = new TableView();
        tableView.setPlaceholder(new Label(""));
        tableView.setPrefHeight(450); tableView.setStyle("-fx-font:12 Roboto");
        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("clubName")); column1.setPrefWidth(120);
        TableColumn<FootballClub, Integer> column2 = new TableColumn<>("Points");
        column2.setCellValueFactory(new PropertyValueFactory<>("points")); column2.setPrefWidth(60);
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Wins");
        column3.setCellValueFactory(new PropertyValueFactory<>("numberOfWins")); column3.setPrefWidth(60);
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Defeats");
        column4.setCellValueFactory(new PropertyValueFactory<>("numberOfDefeats")); column4.setPrefWidth(60);
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Draws");
        column5.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws")); column5.setPrefWidth(60);
        TableColumn<FootballClub, Integer> column6 = new TableColumn<>("Goals Scored");
        column6.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        TableColumn<FootballClub, Integer> column7 = new TableColumn<>("Received");
        column7.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        TableColumn<FootballClub, Integer> column8 = new TableColumn<>("Goal Diff");
        column8.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        tableView.getColumns().addAll(column1,column2,column3,column4,column5,column6,column7,column8);
        VBox vbox = new VBox(tableView);
        vbox.setLayoutX(20); vbox.setLayoutY(45); vbox.setStyle("-fx-background-color:white;-fx-border-width:2;-fx-border-color:black");
        vbox.setPadding(new Insets(10, 10, 10, 10));
        root1.getChildren().addAll(console,head,point,goals,wins,vbox,back,sortBy);
        FootballClub footballClub=new FootballClub();
        point.setOnAction(event -> {
            if (premierLeagueTeams.size()==0){
                tableView.setPlaceholder(new Label("There are no clubs in the league"));
            }
            else{
                footballClub.pointSort(premierLeagueTeams);
            tableView.getItems().clear();
                for (FootballClub premierLeagueTeam : premierLeagueTeams) {
                    tableView.getItems().add(new FootballClub(premierLeagueTeam.getClubName(), premierLeagueTeam.getPoints(),
                            premierLeagueTeam.getNumberOfWins(), premierLeagueTeam.getNumberOfDefeats(),
                            premierLeagueTeam.getNumberOfDraws(), premierLeagueTeam.getGoalsScored(), premierLeagueTeam.getGoalsReceived(),
                            premierLeagueTeam.getGoalDifference()));
                }
        }});
        goals.setOnAction(event -> {
            if (premierLeagueTeams.size()==0){
                tableView.setPlaceholder(new Label("There are no clubs in the league"));
            }
            else{
            footballClub.goalSort(premierLeagueTeams);
            tableView.getItems().clear();
                for (FootballClub premierLeagueTeam : premierLeagueTeams) {
                    tableView.getItems().add(new FootballClub(premierLeagueTeam.getClubName(), premierLeagueTeam.getPoints(),
                            premierLeagueTeam.getNumberOfWins(), premierLeagueTeam.getNumberOfDefeats(),
                            premierLeagueTeam.getNumberOfDraws(), premierLeagueTeam.getGoalsScored(), premierLeagueTeam.getGoalsReceived(),
                            premierLeagueTeam.getGoalDifference()));
                }
        }});
        wins.setOnAction(event -> {
            if (premierLeagueTeams.size()==0){
                tableView.setPlaceholder(new Label("There are no clubs in the league"));
            }
            else{
            footballClub.winSort(premierLeagueTeams);
            tableView.getItems().clear();
                for (FootballClub premierLeagueTeam : premierLeagueTeams) {
                    tableView.getItems().add(new FootballClub(premierLeagueTeam.getClubName(), premierLeagueTeam.getPoints(),
                            premierLeagueTeam.getNumberOfWins(), premierLeagueTeam.getNumberOfDefeats(),
                            premierLeagueTeam.getNumberOfDraws(), premierLeagueTeam.getGoalsScored(), premierLeagueTeam.getGoalsReceived(),
                            premierLeagueTeam.getGoalDifference()));
                }
        }});

        console.setOnAction(event -> {
            tableStage.close();
            cliMenu();
        });
        back.setOnAction(event -> {
            tableStage.close();
            showGUI();
        });
    }
    //javafx generate match
    public void genMatchGUI(){
        AnchorPane genRoot=new AnchorPane();
        genRoot.setStyle("-fx-background-color:#340D39;-fx-border-width:5;-fx-border-color:black");
        Stage genMatchStage=new Stage();
        genMatchStage.setResizable(false);
        genMatchStage.setTitle("Add New Match");
        Scene scene2 = new Scene(genRoot, 345, 255);
        genMatchStage.setScene(scene2);
        genMatchStage.show();
        Button console=new Button("CONSOLE");
        console.setLayoutX(247); console.setLayoutY(210);console.setPrefSize(80,30);
        console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        console.setOnMouseEntered(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        console.setOnMouseExited(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Button back=new Button("BACK");
        back.setLayoutX(20);back.setLayoutY(205);back.setPrefSize(80,30);
        back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        back.setOnMouseEntered(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        back.setOnMouseExited(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Button generate=new Button("Generate Match");
        generate.setLayoutX(105);generate.setLayoutY(15); generate.setPrefSize(150,35);
        generate.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        generate.setOnMouseEntered(e -> generate.setStyle("-fx-background-color:#76B396;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        generate.setOnMouseExited(e -> generate.setStyle("-fx-background-color:#05FA87;-fx-font:14 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        Label genDeets=new Label();
        genDeets.setLayoutX(25); genDeets.setLayoutY(65); genDeets.setPrefSize(300,120); genDeets.setPadding(new Insets(5, 5, 5,10));
        genDeets.setStyle("-fx-font:13 Roboto;-fx-text-fill:#340D39;-fx-background-color:white;-fx-border-width:2;-fx-border-color:black");
        genRoot.getChildren().addAll(console,back,generate,genDeets);
        generate.setOnAction(event -> {
            if (premierLeagueTeams.size()==0){
                genDeets.setText("No Clubs in Premier League");
            }
            else{
            Date date =new Date();
            int month=date.getRandomNumberInRange(1, 12);
            int day;
            if (month==1 ||month==3||month==5||month==7||month==8||month==10||month==12){
                day=date.getRandomNumberInRange(1,31);
            }
            else if(month==2){
                day=date.getRandomNumberInRange(1,28);
            }
            else{
                day=date.getRandomNumberInRange(1,30);
            }
            int year=date.getRandomNumberInRange(2019,2021);
            date=new Date(day,month,year);
            int i=rand.nextInt(premierLeagueTeams.size());
            int hGoalsScored=rand.nextInt(11);
            int j=i;
            while (j==i) {
                j = rand.nextInt(premierLeagueTeams.size());
            }
            int aGoalsScored=rand.nextInt(11);
            Match match=new Match();
            match.addMatch(premierLeagueTeams,i,j,hGoalsScored,aGoalsScored);
            String homeTeamName=premierLeagueTeams.get(i).getClubName();
            String awayTeamName=premierLeagueTeams.get(j).getClubName();
            match= new Match(homeTeamName,awayTeamName,hGoalsScored,aGoalsScored,date);
            matches.add(match);
            genDeets.setText(match.toString());
        }});
        back.setOnAction(event -> {
            genMatchStage.close();
            showGUI();
        });
        console.setOnAction(event -> {
            genMatchStage.close();
            cliMenu();
        });
    }
    //javafx sort and find matches by date
    public void showMatchGUI(){
        AnchorPane showMatchRoot=new AnchorPane();
        showMatchRoot.setStyle("-fx-background-color:#340D39;-fx-border-width:5;-fx-border-color:black");
        Stage showMatchStage=new Stage();
        showMatchStage.setResizable(false);
        showMatchStage.setTitle("Show Matches");
        Scene scene3 = new Scene(showMatchRoot, 560, 615);
        showMatchStage.setScene(scene3);
        showMatchStage.show();
        Button matchSort=new Button("Sort Matches");
        matchSort.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        matchSort.setOnMouseEntered(e -> matchSort.setStyle("-fx-background-color:#76B396;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        matchSort.setOnMouseExited(e -> matchSort.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        matchSort.setLayoutX(20); matchSort.setLayoutY(10); matchSort.setPrefSize(120,35);
        Button console=new Button("CONSOLE");
        console.setLayoutX(470); console.setLayoutY(570);console.setPrefSize(80,30);
        console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        console.setOnMouseEntered(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        console.setOnMouseExited(e -> console.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Button back=new Button("BACK");
        back.setLayoutX(20);back.setLayoutY(570);back.setPrefSize(80,30);
        back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;");
        back.setOnMouseEntered(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#64516B;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        back.setOnMouseExited(e -> back.setStyle("-fx-font:12 Roboto;-fx-background-color:#808080;-fx-text-fill:black; -fx-background-radius:12;;-fx-font-weight:bold;"));
        Label dateSelect=new Label("Search by Date:");
        dateSelect.setLayoutX(170); dateSelect.setLayoutY(19);dateSelect.setStyle("-fx-font:14 Roboto;-fx-font-weight:bold;-fx-text-fill:white;");
        Button ok=new Button("OK");
        ok.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;");
        ok.setOnMouseEntered(e -> ok.setStyle("-fx-background-color:#76B396;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        ok.setOnMouseExited(e -> ok.setStyle("-fx-background-color:#05FA87;-fx-font:12 Roboto;-fx-text-fill:black;-fx-background-radius: 12;-fx-font-weight:bold;"));
        ok.setLayoutX(500); ok.setLayoutY(10); ok.setPrefSize(40,35);
        ComboBox comboDay = new ComboBox();
        comboDay.setPromptText("DD");
        comboDay.setStyle("-fx-font:12 Roboto;-fx-background-color:white;");
        comboDay.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        comboDay.setLayoutX(280); comboDay.setLayoutY(17);
        ComboBox comboMonth = new ComboBox();comboMonth.setPromptText("MM"); comboMonth.setStyle("-fx-font:12 Roboto;-fx-background-color:white;");
        comboMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        comboMonth.setLayoutX(345); comboMonth.setLayoutY(17);
        ComboBox comboYear = new ComboBox();
        comboYear.setPromptText("YYYY"); comboYear.setStyle("-fx-font:12 Roboto;-fx-background-color:white;");
        comboYear.getItems().addAll("2019","2020","2021");
        comboYear.setLayoutX(413); comboYear.setLayoutY(17);
        TableView tableView = new TableView();
        tableView.setPrefSize(503,480); tableView.setStyle("-fx-font:12 Roboto");
        tableView.setPlaceholder(new Label("")); tableView.setStyle("-fx-font:12 Roboto;");
        TableColumn<Match, String> column1 = new TableColumn<>("Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("date")); column1.setPrefWidth(100);
        TableColumn<Match, String> column2 = new TableColumn<>("Home Team");
        column2.setCellValueFactory(new PropertyValueFactory<>("homeTeamName")); column2.setPrefWidth(120);
        TableColumn<Match, Integer> column3 = new TableColumn<>("Home Goals");
        column3.setCellValueFactory(new PropertyValueFactory<>("hGoalsScored"));column3.setPrefWidth(80);
        TableColumn<Match, String> column4 = new TableColumn<>("Away Team");
        column4.setCellValueFactory(new PropertyValueFactory<>("awayTeamName")); column4.setPrefWidth(120);
        TableColumn<Match, Integer> column5 = new TableColumn<>("Away Goals");
        column5.setCellValueFactory(new PropertyValueFactory<>("aGoalsScored"));column5.setPrefWidth(80);
        tableView.getColumns().addAll(column1,column2,column3,column4,column5);
        VBox vbox = new VBox(tableView);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setLayoutX(20); vbox.setLayoutY(55); vbox.setStyle("-fx-background-color:white;-fx-border-width:2;-fx-border-color:black");
        showMatchRoot.getChildren().addAll(matchSort,back,console,vbox,ok,comboMonth,comboDay,comboYear,dateSelect);
        ok.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String day= (String) comboDay.getValue();
            String month=(String) comboMonth.getValue();
            String year=(String) comboYear.getValue();
            if(comboDay.getValue()==null || comboMonth.getValue()==null || comboYear.getValue()==null){
                alert.setContentText("Please select a date!");
                alert.show();
            }
            else{
                if(matches.size()==0){
                    tableView.setPlaceholder(new Label("There are no matches"));
                }
                else{
                tableView.getItems().clear();
                for(int i = 0; i < matches.size(); i++){
                    if (matches.get(i).getDate().toString().equals(day+"-"+month+"-"+year)){
                        tableView.getItems().add(new Match(matches.get(i).getHomeTeamName(),matches.get(i).getAwayTeamName(),
                                matches.get(i).getHGoalsScored(),matches.get(i).getAGoalsScored(),matches.get(i).getDate()));
                    }
                }
                if(tableView.getItems().isEmpty()){tableView.setPlaceholder(new Label("There are no matches from this date"));}
            }}
        });
        matchSort.setOnAction(event -> {
            if (matches.size()==0){
               tableView.setPlaceholder(new Label("There are no matches"));
            }
            else{
                Match match=new Match();
                match.matchSort(matches);
                tableView.getItems().clear();
             for(int m=0;m<matches.size();m++){
                tableView.getItems().add(new Match(matches.get(m).getHomeTeamName(),matches.get(m).getAwayTeamName(),
                        matches.get(m).getHGoalsScored(),matches.get(m).getAGoalsScored(),matches.get(m).getDate()));
            }
        }});
        back.setOnAction(event -> {
            showMatchStage.close();
            showGUI();
        });
        console.setOnAction(event -> {
            showMatchStage.close();
            cliMenu();
        });
    }
    //methods for angular GUI
    @Override
    public ArrayList<FootballClub> points(FootballClub footballClub) {
        footballClub.pointSort(premierLeagueTeams);
        return premierLeagueTeams;
    }
    @Override
    public ArrayList<FootballClub> goals(FootballClub footballClub) {
        footballClub.goalSort(premierLeagueTeams);
        return premierLeagueTeams;
    }
    @Override
    public ArrayList<FootballClub> wins(FootballClub footballClub) {
        footballClub.winSort(premierLeagueTeams);
        return premierLeagueTeams;
    }
    @Override
    public ArrayList<Match> getMatches(Match match){
        match.matchSort(matches);
        return matches;
    }
    @Override
    public String add(FootballClub footballClub){
        for(int i=0;i<premierLeagueTeams.size();i++){
            if (premierLeagueTeams.get(i).getClubName().equals(footballClub.getClubName())){
                return "CLUB ALREADY EXISTS";
            }
        }
        premierLeagueTeams.add(footballClub);
        return "CLUB IS ADDED";
    }
    @Override
    public String addMatchAng(Match match){
        if (match.getHomeTeamName().equals(match.getAwayTeamName())){
            return "SAME CLUB NAME!";
        }
        for(int i=0;i<premierLeagueTeams.size();i++){
            if(premierLeagueTeams.get(i).getClubName().equals(match.getHomeTeamName())){
                for(int j=0;j<premierLeagueTeams.size();j++){
                    if(premierLeagueTeams.get(j).getClubName().equals(match.getAwayTeamName())){
                        String st=match.getStdate();
                        String[] parts = st.split("-");
                        int day = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);
                        Date date=new Date(day,month,year);
                        match.setDate(date);
                        int hGoalsScored=rand.nextInt(11);
                        int aGoalsScored=rand.nextInt(11);
                        match.sethGoalsScored(hGoalsScored);
                        match.setaGoalsScored(aGoalsScored);
                        match.addMatch(premierLeagueTeams,i,j,hGoalsScored,aGoalsScored);
                        matches.add(match);
                        return "MATCH ADDED";
                    }
                }
            }
        }
        return "CLUB DOESN'T EXIST";
    }
    public ArrayList<Match> findMatch(String stdate) {
        temp.clear();
        for (int i=0;i<matches.size();i++){
            if(matches.get(i).getStdate().equals(stdate)){
                temp.add(matches.get(i));
            }
        }
        return temp;
    }
    @Override
    public ArrayList<Match> genMatchAng(Match match){
        Date date =new Date();
        int month=date.getRandomNumberInRange(1, 12);
        int day;
        if (month==1 ||month==3||month==5||month==7||month==8||month==10||month==12){
            day=date.getRandomNumberInRange(1,31);
        }
        else if(month==2){
            day=date.getRandomNumberInRange(1,28);
        }
        else{
            day=date.getRandomNumberInRange(1,30);
        }
        int year=date.getRandomNumberInRange(2019,2021);
        date=new Date(day,month,year);
        int i=rand.nextInt(premierLeagueTeams.size());
        int hGoalsScored=rand.nextInt(11);
        int j=i;
        while (j==i) {
            j = rand.nextInt(premierLeagueTeams.size());
        }
        int aGoalsScored=rand.nextInt(11);
        match.addMatch(premierLeagueTeams,i,j,hGoalsScored,aGoalsScored);
        String homeTeamName=premierLeagueTeams.get(i).getClubName();
        String awayTeamName=premierLeagueTeams.get(j).getClubName();
        match= new Match(homeTeamName,awayTeamName,hGoalsScored,aGoalsScored,date);
        matches.add(match);
        temp.clear();
        temp.add(match);
        return temp;
    }
    @Override
    public String deleteMatch(String clubName){
        for(int i=0; i < premierLeagueTeams.size(); i++){
            if(premierLeagueTeams.get(i).getClubName().equals(clubName)){
                premierLeagueTeams.remove(i);
                return "CLUB IS REMOVED";
            }
        }
        return "CLUB DOESN'T EXIST";
    }
}

