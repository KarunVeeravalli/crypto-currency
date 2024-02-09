package com.cc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Ex {

	public static void main(String[] args) {
//		String s = "    this    is karun";
//		StringBuilder n = new StringBuilder();
//		Scanner sc = new Scanner(s);
//		while(sc.hasNext()) {
//			StringBuilder nw = new StringBuilder().append(sc.next()+" ");
//			n = nw.append(n);
//		}
//		System.out.println(n);
//		
//		
//		Double amount = 2052.5867645036565;
//		System.out.println(amount*3);
		
		String dateString = "23-Sep-2022";

        try {
            // Convert the month abbreviation to uppercase
//        	StringBuilder newS = new StringBuilder(dateString);
//            newS.setCharAt(3, Character.toUpperCase(newS.charAt(3)));
//            newS.setCharAt(4, Character.toLowerCase(newS.charAt(4)));
//            newS.setCharAt(5, Character.toLowerCase(newS.charAt(5)));
            
            // Define the date format and locale
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

            // Parse the string to obtain a LocalDate object
            LocalDate parsedDate = LocalDate.parse(dateString, formatter);

            // Print the parsed LocalDate object
            System.out.println("Parsed Date: " + parsedDate);
        } catch (Exception e) {
            // Handle the exception if the date string is not in the expected format
            e.printStackTrace();
        }
    
	}

}
