package com.finalProject.service;

import com.finalProject.dao.ApplicationDao;

public class ApplicationService {

    public static void restartProgram() {

        ApplicationDao.repeatProgram();

    }

    public void incorrectDataEntered() {

        System.out.println("Вы ввели неправильную команду.");
        System.out.println("Вас отправляет в главное меню.");
        ApplicationDao.repeatProgram();
    }

}
