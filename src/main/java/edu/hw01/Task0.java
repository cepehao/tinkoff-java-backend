package edu.hw01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**************************************************************************************************************
 * ДЗ 1 задача 0. Привет, мир!
 * <p>
 * Напишите функцию, которая выводит в консоль фразу "Привет, мир!" при помощи метода LOGGER.info().
 *************************************************************************************************************/

public final class Task0 {
    private final static Logger LOGGER = LogManager.getLogger();

    public void sayHello() {
        LOGGER.info("Привет, мир!");
    }
}
