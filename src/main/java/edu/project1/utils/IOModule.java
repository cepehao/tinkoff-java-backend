package edu.project1.utils;

import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class IOModule {
    public final static Logger LOGGER = LogManager.getLogger();
    public final static Scanner SCANNER = new Scanner(System.in);
    public final static Pattern PATTERN = Pattern.compile("^[a-zA-Z]$");

    private IOModule() {

    }
}
