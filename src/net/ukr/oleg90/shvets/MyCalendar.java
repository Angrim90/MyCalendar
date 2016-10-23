package net.ukr.oleg90.shvets;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author Shvets Oleg
 * @version 1.0
 */
public class MyCalendar {
    private LocalDate date;



    public MyCalendar() {
    }

    public MyCalendar(LocalDate date) {
        this.date = date;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate incomingDate)  {
       this.date = incomingDate;


    }


    public void checkDate(LocalDate localDate){
        int dayOfMonth = localDate.getDayOfMonth();
        Month monthLine = localDate.getMonth();
        int month = monthLine.getValue();
        int year = localDate.getYear();
        createTable(dayOfMonth,month,year);


    }
    private int getFirstWeekOnMonth(int month,int year){
        LocalDate localDate = LocalDate.of(year,Month.of(month),1);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }

    private int getLastWeekOnMonth(int month,int year){
        LocalDate localDate= LocalDate.of(year,Month.of(month),Month.of(month).length(true));

        int result = (localDate.lengthOfMonth()/7)+ getFirstWeekOnMonth(month,year);
        return result;

    }


    private void createTable(int dayOfMonth,int month,int year){
        int firstDayOfMonth =getFirstDayOfMonth(dayOfMonth,month,year);
        int lengthOfMonth = this.date.lengthOfMonth();
        int weeksInMonth = 0;
        if(firstDayOfMonth>=6){
           weeksInMonth = getLastWeekOnMonth(month,year)-getFirstWeekOnMonth(month,year)+2;
        }
        else {
            weeksInMonth = getLastWeekOnMonth(month,year)-getFirstWeekOnMonth(month,year)+1;
        }
        createLineWithDays();
        createLine();
            String [][] lineOnCalendar = new String [weeksInMonth][7];
            int count =0;
            // check count
        for (int j =1;j<=weeksInMonth;j++) {
            for (int i = 1; i <= 7; i++) {
                if (i >= firstDayOfMonth && count <= lengthOfMonth||count>=1&&count<=lengthOfMonth) {
                    count++;
                    if(count==lengthOfMonth+1){
                        break;
                    }
                }
                String line = "";
                //create lines if good
                if (count > 0) {
                    //red days
                    if(i==6||i==7||count==dayOfMonth){
                        line = String.format(lineOnCalendar[j - 1][i - 1] = (char) 27 + "[31m%2d|"+(char) 27 + "[0m",count );
                        System.out.format(line);
                    }else {
                        line = String.format(lineOnCalendar[j - 1][i - 1] = "%2d|", count);
                        System.out.format(line);
                    }
                } else {
                    line =   "  |";
                    System.out.print(line);
                }
            }
           System.out.println("");
            createLine();
        }
    }


    private void createLine(){
        System.out.println("--+--+--+--+--+--+--");
    }
    private int getFirstDayOfMonth(int dayOfMonth,int month,int year){

        LocalDate localDate = LocalDate.of(year,month,dayOfMonth);
        LocalDate nDate = LocalDate.of(localDate.getYear(),localDate.getMonth(),1);
        int firstDayOfMonth = nDate.getDayOfWeek().getValue();


        return firstDayOfMonth;

    }
    private void createLineWithDays(){
        Formatter formatter = new Formatter();
        formatter.format("%s|%s|%s|%s|%s|"+(char) 27 + "[31m%s|"+(char) 27 + "[31m%s|"+(char)27 + "[0m","Mo","Tu","We","Th","Fr","Sa","Su");
        String lineWithDais = formatter.toString();
        System.out.println(lineWithDais);
    }
    @Override
    public String toString() {
        return "net.ukr.oleg90.shvets.MyCalendar{" +
                "date=" + date +
                '}';
    }

}
