package net.ukr.oleg90.shvets;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        int[] array = null;
            if(args.length==0){
            calendar.setDate(LocalDate.now());
            calendar.checkDate(LocalDate.now());
            }
            else {
                String[] date = splitting(args[0]);
                try{
                    array= parser(date);}
                catch (NumberFormatException e ){
                    System.out.println("Wrong input format! Try dd:mm:yyyy");

            }
        try {
            LocalDate localDate = LocalDate.of(array[2], Month.of(array[1]), array[0]);
                calendar.setDate(localDate);
            calendar.checkDate(localDate);

        }
        catch (ArrayIndexOutOfBoundsException|DateTimeException|NullPointerException e){
            System.out.println("Wrong input format! Or there is no such day... Try - dd:mm:yyyy");

        }
            }


    }
    public static String [] splitting(String incom){
        String [] array = incom.split("[:,.!;\"/]");
        return array;
    }
    public static int[] parser(String[] array) throws NumberFormatException{
        int [] insideArray = new int[array.length];
        for (int i=0;i<array.length;i++) {
            insideArray[i]=Integer.parseInt(array[i]);


        }
        return insideArray;

    }

}
