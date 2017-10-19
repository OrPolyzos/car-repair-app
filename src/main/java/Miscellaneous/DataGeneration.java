package Miscellaneous;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        generateAddresses();
        exportAddressesToCSV();
        generateUsers();
        exportUsersToCSV();
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
        for (int i = 0; i < firstNames.length; i++){
            for (int j = 0; j < lastNames.length; j++){
                Random random = new Random();
                double chance = random.nextDouble();
                if (chance > 0.5){
                    firstNamesList.add(firstNames[i]);
                    lastNamesList.add(lastNames[j]);
                    emailsList.add(firstNames[i]+"@"+lastNames[j]+".com");
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
        do{
            passwordLength = random.nextInt(16);
        } while(passwordLength<6);

        for (int i = 0; i < passwordLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static String generateAFM(){
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
}
