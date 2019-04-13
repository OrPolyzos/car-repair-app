package misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGeneration {

    static char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] nums = "0123456789".toCharArray();

    static String[] firstNames = {"Glykeria", "Spyros", "Christos", "John", "Nasos", "Giannis", "Stelios", "Alexandros"};
    static String[] lastNames = {"Katsari", "Argyroiliopoulos", "Peristeris", "Vlahopoulos", "Kormaris", "Kotsidimos", "Psarrakos", "Pagonis", "Melakis", "Matthaios"};
    static String[] types = {"User", "User"};

    static String[] addresses = {"Ethnikis Antistaseos", "Oulof Palme", "Papagou", "Xalandriou", "Syggrou", "Athinwn", "Spartis", "Pelika"};
    static int[] addressesNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    static int[] zipCodes = {15772, 15771, 25439, 18975, 48859, 23885, 93489};
    // --------------------------------------------------------------------------------
    static List<Integer> addressIDList = new ArrayList<>();
    static List<String> addressesList = new ArrayList<>();
    static List<Integer> addressesNumbersList = new ArrayList<>();
    static List<Integer> addressesZipCodesList = new ArrayList<>();

    static List<Integer> userIDList = new ArrayList<>();
    static List<String> afmsList = new ArrayList<>();
    static List<String> emailsList = new ArrayList<>();
    static List<String> passwordsList = new ArrayList<>();
    static List<String> typesList = new ArrayList<>();
    static List<String> firstNamesList = new ArrayList<>();
    static List<String> lastNamesList = new ArrayList<>();
    static List<Integer> userAddrIDList = new ArrayList<>();


    public static void main(String[] args) {
        generateUsers();
        generateRepairs();
        // exportRepairsToCSV();

//        for (int i=0; i < firstNamesList.size(); i++){
//            System.out.println(firstNamesList.get(i) + " " + lastNamesList.get(i) + " " +
//            emailsList.get(i) + " " + afmsList.get(i) + " " + passwordsList.get(i) + " " + addressIDList.get(i) + " " + typesList.get(i));
//        }
    }

    static void generateAddresses() {
        int counter = 1;
        for (int i = 0; i < addresses.length; i++) {
            for (int j = 0; j < addressesNumbers.length; j++) {
                for (int k = 0; k < zipCodes.length; k++) {
                    Random random = new Random();
                    double chance = random.nextDouble();
                    if (chance > 0.95) {
                        addressIDList.add(counter);
                        addressesList.add(addresses[i]);
                        addressesNumbersList.add(addressesNumbers[j]);
                        addressesZipCodesList.add(zipCodes[k]);
                        counter++;
                    }
                }
            }
        }

    }

    static void generateUsers() {
        for (int i = 0; i < firstNames.length; i++) {
            for (int j = 0; j < lastNames.length; j++) {
                Random random = new Random();
                double chance = random.nextDouble();
                if (chance > 0.5) {
                    firstNamesList.add(firstNames[i]);
                    lastNamesList.add(lastNames[j]);
                    emailsList.add(firstNames[i] + "@" + lastNames[j] + ".com");
                    passwordsList.add(generatePassword());
                    afmsList.add(generateAFM());
                    typesList.add("User");
                    userAddrIDList.add(random.nextInt(addressIDList.size()));
                }
            }
        }
    }

    private static String generatePassword() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int passwordLength = 0;
        do {
            passwordLength = random.nextInt(16);
        } while (passwordLength < 6);

        for (int i = 0; i < passwordLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static String generateAFM() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            char c = nums[random.nextInt(nums.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static void exportAddressesToCSV() {
        try {
            String filename = "Addresses";
            //Getting Working Directory
            String WorkingDir = System.getProperty("user.dir");
            //Appending filename
            PrintWriter exportFile = new PrintWriter(new File(WorkingDir + "\\" + filename + ".csv"));
            //Initializing a new StringBuilder
            StringBuilder sb = new StringBuilder();
            //Iterating through all the items in plates arraylist
            sb.append("Street,Number,Zip\n");
            for (int i = 0; i < addressIDList.size(); i++) {
                //Appending the plateID
                sb.append(addressesList.get(i));
                sb.append(",");
                sb.append(addressesNumbersList.get(i));
                sb.append(",");
                sb.append(addressesZipCodesList.get(i));
                sb.append("\n");
            }
            //Writing to platesfile the content of the stringbuilder.toString()
            exportFile.write(sb.toString());
            //Closing the files
            exportFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void exportUsersToCSV() {
        try {
            String filename = "Users";
            //Getting Working Directory
            String WorkingDir = System.getProperty("user.dir");
            //Appending filename
            PrintWriter exportFile = new PrintWriter(new File(WorkingDir + "\\" + filename + ".csv"));
            //Initializing a new StringBuilder
            StringBuilder sb = new StringBuilder();
            //Iterating through all the items in plates arraylist
            for (int i = 0; i < 30; i++) {
                //Appending the plateID
                sb.append(afmsList.get(i));
                sb.append(",");
                sb.append(emailsList.get(i));
                sb.append(",");
                sb.append(passwordsList.get(i));
                sb.append(",");
                sb.append(typesList.get(i));
                sb.append(",");
                sb.append(firstNamesList.get(i));
                sb.append(",");
                sb.append(lastNamesList.get(i));
                sb.append(",");
                sb.append(addressesList.get(i));
                sb.append(",");
                sb.append(addressesNumbersList.get(i));
                sb.append(",");
                sb.append(addressesZipCodesList.get(i));
                sb.append("\n");
            }
            //Writing to platesfile the content of the stringbuilder.toString()
            exportFile.write(sb.toString());
            //Closing the files
            exportFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }


    //Data to populate Repairs' Table

    static String[] statuses = {"Completed", "InProgress", "Pending"};
    static Short[] repairTypeIds = {1, 2, 3};
    static String[] tasks = {"task1", "task2", "task3", "task4", "task5"};
    static String[] vehicleIds = {"ABE-123", "BEZ-234", "EZH-345", "ZHI-456", "HIK-567"};


    static List<Date> datesList = new ArrayList<>();
    static List<Time> timeList = new ArrayList<>();
    static List<String> statusList = new ArrayList<>();
    static List<String> tasksList = new ArrayList<>();
    static List<Float> totalCostsList = new ArrayList<>();
    static List<Short> repairTypeIdsList = new ArrayList<>();
    static List<String> vehicleIdsList = new ArrayList<>();


    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate generateDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2017, 2018);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        String date = (gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));
        LocalDate ldate = LocalDate.parse(date, formatter);
        return ldate;
    }

    static void generateRepairs() {
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            double chance = random.nextDouble();
            //datesList.add(generateDates());
            //timeList.add (generateTime);
            statusList.add(statuses[random.nextInt(statuses.length)]);
            tasksList.add(tasks[random.nextInt(tasks.length)]);
            //totalcost
            repairTypeIdsList.add(repairTypeIds[random.nextInt(repairTypeIds.length)]);
            vehicleIdsList.add(vehicleIds[random.nextInt(vehicleIds.length)]);
            //sta vehicle ids tha mporouse na einai
            //repairvehicleIdsList.add(random.next(vehicleIdsList.size ()))
        }
    }

    public static void exportRepairsToCSV() {
        try {
            String filename = "Repairs";
            //Getting Working Directory
            String WorkingDir = System.getProperty("user.dir");
            //Appending filename
            PrintWriter exportFile = new PrintWriter(new File(WorkingDir + "\\" + filename + ".csv"));
            //Initializing a new StringBuilder
            StringBuilder sb = new StringBuilder();
            //Iterating through all the items in plates arraylist
            for (int i = 1; i < 30; i++) {
                Random rand = new Random();
                //Appending the plateID
                sb.append(i);
                sb.append(",");
//                sb.append(datesList.get(i));
//                sb.append(",");
                sb.append(statusList.get(i));
                sb.append(",");
                sb.append(tasksList.get(i));
                sb.append(",");
//                sb.append(timeList.get (i));
//                sb.append (",");
                sb.append(rand.nextInt(150));
                sb.append(",");
                sb.append(repairTypeIdsList.get(i));
                sb.append(",");
                sb.append(vehicleIdsList.get(i));
                sb.append(",");
                sb.append("\n");
            }


            //Writing the content of the stringbuilder.toString()
            exportFile.write(sb.toString());
            //Closing the files
            exportFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
