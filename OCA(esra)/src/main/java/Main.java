import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Model mainData = new Model();

//        Employee employee1 = new Employee(1, "user01","123abc");
        Customer customer1 = new Customer("Smith", 6873434, "smith@smith.com", "kazernalaanstraat 101");
        Employee employee2 = new Employee("saif", "abc123");



        try {

            Model model1 = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);
//            System.out.println(model1.rentals.get(0));
            Scanner scanner = new Scanner(System.in);
            boolean isAuthenticated = false;
            System.out.println("Welcome to Boat Reservation Application");
            while (!isAuthenticated) {
                System.out.print("Please Enter your username: ");
                String userName = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (authenticate(userName, password, model1)) {
                    isAuthenticated = true;
                } else {
                    System.out.println("Incorrect username or password. Please enter your credentials again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("To see the list of boats press L");
        System.out.println("To add new Boat to the system press A");
        System.out.println("To rent a Boat to the system press R");//new
        String choose = scanner.nextLine();
        if (choose.equalsIgnoreCase("R")) {

            while (true) {
                System.out.println("Please enter the date");
                System.out.println("year-month-day (2020-12-1)");
                String date = scanner.nextLine();
                System.out.println("Please enter the time");
                System.out.println("hour-minute (18:30)");
                String time = scanner.nextLine();

                //String to date !!!
                String stringTypeDate = date.concat(";").concat(time);
                System.out.println("estimated duration(minute)");
                int estimatedDuration = scanner.nextInt();
                LocalDateTime dateTimeEntry = stringToLocalDateTime(stringTypeDate);
                LocalDateTime dateTimeExit = dateTimeEntry.plusMinutes(estimatedDuration);

                if(canBeRented()==true) {
                    Rental newRent;

                    Model model2=mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"),Model.class);

                    List<Boat>boats=new ArrayList<>();
                    for (Boat boat1:model2.boats){
                        if (boat1.getBoatId()==2){        //normal,choosed boat id
                            boats.add(0,boat1);
                        }
                    }
                    Customer customer2 = new Customer("Esra",31333755,"esra@email","amsterdam");

                    try {
                        mainData = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);
                        mainData.customers.add(customer2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // Java object to JSON file
                        mapper.writeValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), mainData);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    newRent=new Rental(boats.get(0),customer2,dateTimeEntry,dateTimeExit,estimatedDuration,boats.get(0).getMinimumPrice(),true,true);

                    try {
                        mainData = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);
                        mainData.rentals.add(newRent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        // Java object to JSON file
                        mapper.writeValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), mainData);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                break;
                }


            else{}





            }}
        else if (choose.equalsIgnoreCase("L")) {
            mainData = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);
            for (Boat boatIn : mainData.boats) {
                System.out.println(boatIn);
            }
        }
        else if (choose.equalsIgnoreCase("A")) {
//            System.out.println("To add new Boat to the system press A");
//            String boatAdd = scanner.nextLine();
            int chargingTime = 0;
            int boatSeats = 0;
            double keyInput_boatPrice;
            System.out.println("Please enter boat type: ");
            String keyInput_boatType = scanner.nextLine();
            if (keyInput_boatType.equalsIgnoreCase("ELECTRICALBOAT")) {
                boatSeats = 4;
                chargingTime = 1;
            } else if (keyInput_boatType.equalsIgnoreCase("KAJAK")) {
                boatSeats = 2;
                chargingTime = 0;
            } else if (keyInput_boatType.equalsIgnoreCase("ROWINGBOAT")) {
                boatSeats = 4;
                chargingTime = 0;
            } else {
                boatSeats = 1;
                chargingTime = 0;
            }
            System.out.println("Please enter boat rent price per hour: ");
            keyInput_boatPrice = scanner.nextInt();
            if (keyInput_boatType.equalsIgnoreCase("ELECTRICALBOAT")) {
                PRICE:
                if (keyInput_boatPrice >= 35) {
                    keyInput_boatPrice = Double.parseDouble(scanner.next());
                } else {
                    System.out.println("The renting price should be minimum 35: ");
                    keyInput_boatPrice = scanner.nextInt();
                }
            } else if (keyInput_boatType.equalsIgnoreCase("ROWINGBOAT")) {
                if (keyInput_boatPrice >= 27.5) {
                    keyInput_boatPrice = Double.parseDouble(scanner.next());
                } else {
                    System.out.println("The renting price should be minimum 27.5: ");
                    keyInput_boatPrice = scanner.nextInt();
                }
                if (keyInput_boatType.equalsIgnoreCase("KAJAK")) {
                    if (keyInput_boatPrice >= 23) {
                        keyInput_boatPrice = Double.parseDouble(scanner.next());
                    } else {
                        System.out.println("The renting price should be minimum 23: ");
                        keyInput_boatPrice = scanner.nextInt();
                    }
                }
                if (keyInput_boatType.equalsIgnoreCase("SUPBOARD")) {
                    if (keyInput_boatPrice >= 17.5) {
                        keyInput_boatPrice = Double.parseDouble(scanner.next());
                    } else {
                        System.out.println("The renting price should be minimum 17.5: ");
                        double newPrice = scanner.nextInt();
                        keyInput_boatPrice = newPrice;
                    }
                }
            }
            System.out.println("A new Boat is added successfully.");
            Boat boat1 = new Boat(keyInput_boatType, boatSeats, keyInput_boatPrice, chargingTime);

            try {
                mainData = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);
                mainData.boats.add(boat1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // Java object to JSON file
                mapper.writeValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), mainData);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }



//print employees
//        try{
//            Model model1 = mapper.readValue(new File("c:\\Users\\zgoksu\\temp\\model.json"), Model.class);
//            for(Employee employeesIn : model1.employees){
//                System.out.println(employeesIn);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }


    //        // prints customers
//        try{
//            Model model1 = mapper.readValue(new File("C:\Users\etepebas\IdeaProjects\OCA\model (3).json"), Model.class);
//            for(Customer customerIn : model1.customers){
//                System.out.println(customerIn);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }



//    private static boolean isRented(LocalDateTime entryDate,LocalDateTime exitDate){
//        return true;
//    }
//
    private static boolean authenticate(String userName, String password, Model model1) {
        for (Employee employee : model1.employees) {
            if (employee.getUserName().equals(userName) && employee.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }




//    private static List<Boat> rented(LocalDateTime firstDate, LocalDateTime lastDate) throws IOException {//
//        Model model = mapper.readValue(new File("C:\\Users\\etepebas\\IdeaProjects\\OCA\\model (3).json"), Model.class);//
//        List<Boat> avaibleBoats = model.boats;//
////        for (Rental rental : model.rentals) {
////            if (firstDate.isAfter(rental.getStartTime()) & firstDate.isBefore(rental.getEndTime())) {
////                //Start time between a reserved date
////                avaibleBoats.remove(rental.getBoat());
////            } else if ((firstDate.isBefore(rental.getStartTime()) == true) & ((lastDate.isBefore(rental.getStartTime())) == false)) {
////                //if the beginning time of  the requested is before a beginning of a reserved time,the last time of the requested must be before that reserved time.
////                avaibleBoats.remove(rental.getBoat());
////            } else {
////            }
////    }
//        return avaibleBoats;
//    }


    //if we need to a data ,that is a string, transform the LocalDateTime,
    private static LocalDateTime stringToLocalDateTime(String stringTypeDate){
        String pattern="yyyy-MM-dd;HH:mm";
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime result=LocalDateTime.parse(stringTypeDate,dTF);
        return result;
    }

    private static boolean canBeRented(){
        return true;
    }

}





