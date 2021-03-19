package com.example;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;
    public  Date(){}
    public Date(int day, int month, int year) {
        this.day=day;
        this.month=month;
        this.year=year;
    }
    //method to get random integer within a range
    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
    //method to set year
    public void setYear(Scanner sc) {
        int year = 0;
        while (year < 2019 || year > 2021) {
            while (true) {
                System.out.print("Year of the match (2019-2021) : ");
                String input = sc.next();
                try {
                    year = Integer.parseInt(input);
                    if (year < 2019 || year > 2021) {
                        System.out.println("Enter valid year (2019-2021)");
                    }
                    break;
                } catch (NumberFormatException ne) {
                    System.out.print("Enter valid year\n");
                }
            }
        }
       this.year=year;
    }
    //method to set month
    public void setMonth(Scanner sc) {
        int month=0;
        while (month < 1 || month > 12) {
            while(true) {
                System.out.print("\nMonth of the match (1-12): ");
                String input = sc.next();
                try{
                    month=Integer.parseInt(input);
                    if (month<1 || month>12){
                        System.out.println("Enter valid month (1-12)");
                    }
                    break;
                }catch(NumberFormatException ne){
                    System.out.print("Enter valid month (1-12)\n");
                }
            }
        }
        this.month=month;
    }
    //method to set day
    public void setDay(Scanner sc){
        int day=0;
        if (month==1 ||month==3||month==5||month==7||month==8||month==10||month==12){
            while (day < 1 || day > 31) {
                while(true) {
                    System.out.print("Day of the match (1-31): ");
                    String input = sc.next();
                    try{
                        day=Integer.parseInt(input);
                        if (day<1 || day>31){
                            System.out.println("Enter valid date (1-31)");
                        }
                        break;
                    }catch(NumberFormatException ne){
                        System.out.print("Enter valid date (1-31)\n");
                    }
                }
            }
        }
        else if(month==2){
            while (day < 1 || day > 28) {
                while(true) {
                    System.out.print("Day of the match (1-28): ");
                    String input = sc.next();
                    try{
                        day=Integer.parseInt(input);
                        if (day<1 || day>28){
                            System.out.println("Enter valid date (1-28)");
                        }
                        break;
                    }catch(NumberFormatException ne){
                        System.out.print("Enter valid date (1-28)\n");
                    }
                }
            }
        }
        else{
            while (day < 1 || day > 30) {
                while(true) {
                    System.out.print("Day of the match (1-30): ");
                    String input = sc.next();
                    try{
                        day=Integer.parseInt(input);
                        if (day<1 || day>30){
                            System.out.println("Enter valid date (1-30)");
                        }
                        break;
                    }catch(NumberFormatException ne){
                        System.out.print("Enter valid date (1-30)\n");
                    }
                }
            }
        }
        this.day=day;
    }
    public int getYear() {
        return year;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public String toString() {
        return day+"-"+month+"-"+year;
    }
}
