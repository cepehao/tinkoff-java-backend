package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final HashMap<Integer, String> PORTS_INFO = new HashMap<>();
    private static final int FIRST_PORT = 1;
    private static final int LAST_PORT = 49151;
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String PRINT_CONFIG = "%s%-12s %-8d %-6s";

    private Task6() {

    }

    static {
        PORTS_INFO.put(22, "SSH");
        PORTS_INFO.put(25, "SMTP");
        PORTS_INFO.put(53, "DOMAIN");
        PORTS_INFO.put(80, "HTTP");
    }

    private enum ProtocolType {
        TCP,
        UPD;

        @Override
        public String toString() {
            return switch (this) {
                case TCP -> "TCP";
                case UPD -> "UPD";
            };
        }
    }

    public static void portScanner() {
        LOGGER.info(String.format("%s%-12s %-8s %-6s", ANSI_RESET, "Протокол", "Порт", "Сервис"));

        for (int port = FIRST_PORT; port <= LAST_PORT; port++) {
            var isTCPFree = isPortFree(port, ProtocolType.TCP);
            var isUPDFree = isPortFree(port, ProtocolType.UPD);

            printInfo(isTCPFree, ProtocolType.TCP, port);
            printInfo(isUPDFree, ProtocolType.UPD, port);
        }
    }

    private static boolean isPortFree(int port, ProtocolType protocolType) {
        switch (protocolType) {
            case TCP -> {
                try (var serverSocket = new ServerSocket(port)) {
                    return true;
                } catch (IOException ex) {
                    return false;
                }
            }

            case UPD -> {
                try (var datagramSocket = new DatagramSocket(port)) {
                    return true;
                } catch (IOException ex) {
                    return false;
                }
            }

            default -> throw new IllegalArgumentException();
        }
    }

    // Если порт свободен - зеленый цвет, занят - красный
    private static void printInfo(boolean isFree, ProtocolType protocolType, int port) {
        if (isFree) {
            LOGGER.info(String.format(
                PRINT_CONFIG,
                ANSI_GREEN,
                protocolType.toString(),
                port,
                PORTS_INFO.getOrDefault(port, "")
            ));
        } else {
            LOGGER.info(String.format(
                PRINT_CONFIG,
                ANSI_RED,
                protocolType.toString(),
                port,
                PORTS_INFO.getOrDefault(port, "")
            ));
        }
    }
}
