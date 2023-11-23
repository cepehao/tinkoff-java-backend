package edu.project3;

import static edu.project3.utils.CLogger.LOGGER;

public final class Main {
    private Main() {

    }

    public static void main(String[] args) {
        var app = new CApplication();

        try {
            app.run(args);
        } catch (Exception ex) {
            LOGGER.error("Ошибка во время исполнения программы: " + ex.getMessage());
        }
    }
}
