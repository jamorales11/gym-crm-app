package com.epam.gymcrm;

import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;


public class ApplicationRunner implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Welcome to the Gym CRM App");
            System.out.println("Write '1' if you want to create a profile. Write '2' to log in. Write '0' to exit.");

            String input = scanner.nextLine();
            boolean goBack = false;

            switch (input){
                case "1":

                    while( !goBack){
                        System.out.println("Write '1' if you want to create a Trainer. Write '2' if you want to create a Trainee. Write '0' for previous menu");
                        input = scanner.nextLine();
                        switch (input){
                            case "1":
                                System.out.println("Enter id:");
                                int id = scanner.nextInt();
                                System.out.println("Enter specialization:");
                                String specialization = scanner.nextLine();

                                //gymFacade.createProfile(new TrainerDto(id, specialization));
                                break;
                            case "2":
                                break;
                            case "0":
                                goBack = true;
                                break;
                            default:
                                System.out.println("Write a valid option");
                                break;
                        }

                    }

                    break;
                case "0":
                    System.exit(0);

                default:
                    System.out.println("Write a valid option");
                    break;
            }

        }


    }
}
