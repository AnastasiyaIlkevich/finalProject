package com.finalProject.application;

import com.finalProject.service.ApplicationService;
import com.finalProject.service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    Scanner scanner = new Scanner(System.in);

    BookService bookService = new BookService();
    ApplicationService applicationService = new ApplicationService();

    //метод старт
    public void startProgram() throws InterruptedException {
        System.out.println("""
                Добро пожаловать в главное меню\s
                Ваши дальнейшие действия (введите цыфру для дальнейшей работы)\s
                1. Вывод всех книг асортимента.\s
                2. Удаление книги по ID.\s
                3. Выход (Окончание работ в системе интернет магазина).\s""");

        //не реализованные методы
        // . Добавление книги (пополнение асортимента).s
        // . Удаление книги.s
        // . Редоктирование книги.s


        int programMode = scanner.nextInt();//обработать ввод не цифр и неправильный ввод

        switch (programMode) {
            case 1://Вывод всех книг асортимента.
                System.out.println("""
                        Ваши дальнейшие действия (введите цыфру для дальнейшей работы)\s
                        1. Вывод всех книг асортимента.\s
                        2. Вывод книги из асортимента по ID.\s""");
                int exPoint = scanner.nextInt();
                if (exPoint == 1) {
                    try {
                        System.out.println(bookService.fetchAllBook());
                        ApplicationService.restartProgram();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else if (exPoint == 2) {

                    System.out.println("ВВедите ID книги.");
                    Long idBook = scanner.nextLong();
                    System.out.println(bookService.fetchBookById(idBook));
                    ApplicationService.restartProgram();
                } else {
                    applicationService.incorrectDataEntered();
                }

                break;

            case 2://Удаление книги.
                System.out.println("Введите ID для удаления книги.");
                Long idDel = scanner.nextLong();
                bookService.deleteBook(idDel);
                ApplicationService.restartProgram();
                break;


            case 3:
                System.out.println("Выход (Окончание работ в системе интернет магазина).");
                break;

            default:
                applicationService.incorrectDataEntered();

        }


    }


}
