package com.finalProject.dao;

import com.finalProject.application.Application;
import com.finalProject.service.ApplicationService;

import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationDao {


    private static Application application = new Application();
    private static ApplicationService applicationService = new ApplicationService();
    private static Scanner scanner = new Scanner(System.in);

    public static void repeatProgram() {


        System.out.println("""
                Желаете ли продолжить?\s
                1. Да.\s
                2. Нет\s""");
        int command = scanner.nextInt();
        if (command == 1) {
            try {
                application.startProgram();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        } else if (command == 2){
            finish();
        }else {
            applicationService.incorrectDataEntered();
        }

    }

    private static void finish() {
        System.out.println("Выход (Окончание работ в системе интернет магазина).");
    }
}
