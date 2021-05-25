package com.finalProject.dao;

import java.util.Scanner;

public class GenreDao {

public static Long SelectBookGenre (){

    Scanner scanner = new Scanner(System.in);
    System.out.println("Выбирите жанр из предложенных вариантов");
    System.out.println("""
                1. Жанр не известен\s
                2. Роман\s
                3. Комедия\s
                4. Трагедия\s
                5. Драма\s
                6. Ужас\s
                7. Новелла\s
                8. Фентази\s
                9. Фантастика\s""");


     return scanner.nextLong();
}




}
