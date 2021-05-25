package com.finalProject.application;

import com.finalProject.service.ApplicationService;
import com.finalProject.service.BookService;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

public class Application {

    Scanner scanner = new Scanner(System.in); //для ввода чисел
    Scanner scanner2 = new Scanner(System.in);//для ввода текста

    BookService bookService = new BookService();
    ApplicationService applicationService = new ApplicationService();


    //метод старт
    public void startProgram() throws InterruptedException, SQLException {
        System.out.println("""
                Добро пожаловать в главное меню\s
                Ваши дальнейшие действия (введите цыфру для дальнейшей работы)\s
                1. Вывод всех книг асортимента.\s
                2. Удаление книги по ID.\s
                3. Добавление книги.\s
                4. Изменить название, автора, жанр.\s
                5. Выход (Окончание работ в системе интернет магазина).\s""");

        //не реализованные методы
        // . Добавление книги (пополнение асортимента).s
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
                        Iterator iterator = bookService.fetchAllBook().iterator();
                        while(iterator.hasNext()){
                            System.out.println(iterator.next());
                        }
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

            case 3://добавление книги.
                System.out.println("""
                        Для ввода книги вам нужно ввести название, IBSN, автора и жанр.\s
                        Если названиеуже существует в база в консоль будет выведена информация по данной книге.\s""");
                System.out.println(ApplicationService.requestAddBook());
                ApplicationService.restartProgram();
                break;

            case 4:
                System.out.println("""
                Выбирите что будет корректироваться:\s
                1. Корректировка названия.\s
                2. Корректировка автора.\s
                3. Корректировка жанра.\s""");
                int exPoint2 = scanner.nextInt();

                System.out.println("Введите ID книги для корректировки:");
                Long bookId = scanner.nextLong();

                switch (exPoint2) {
                    case 1:
                        System.out.println("Введите новое название книги: ");
                        String newTitleBook = scanner2.nextLine();
                        System.out.println(bookService.correctionTitle(bookId, newTitleBook));
                        ApplicationService.restartProgram();
                        break;
                    case 2:
                        System.out.println(bookService.correctionAuthor());
                        break;
                    case 3:
                        System.out.println(bookService.correctionGenre( bookId ));
                        break;
                    default:
                        applicationService.incorrectDataEntered();
                }



                break;


            case 5:
                System.out.println("Выход (Окончание работ в системе интернет магазина).");
                break;

            default:
                applicationService.incorrectDataEntered();

        }
    }
}
