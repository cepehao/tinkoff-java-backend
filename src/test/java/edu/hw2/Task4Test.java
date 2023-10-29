package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.CallingInfo;

class Task4Test {

    private static final String CLASS_NAME_STRING_VALUE = "className";
    private static final String METHOD_NAME_STRING_VALUE = "methodName";
    private static final String EQUALS = "=";

    private CallingInfo callingInfo;
    private String actual;

    @Test
    @DisplayName("Вызов метода callingInfo из класса Task4Test, метода callingInfoTest1")
    void callingInfoTest1() {

        callingInfo = Task4.callingInfo();

        actual = CLASS_NAME_STRING_VALUE + EQUALS + callingInfo.className() + ";"
            + METHOD_NAME_STRING_VALUE + EQUALS + callingInfo.methodName();

        Assertions.assertThat(actual).isEqualTo(
            "className=edu.hw2.Task4Test;methodName=callingInfoTest1"
        );
    }

    @Test
    @DisplayName("Вызов метода callingInfo из класса Task4Test$InnerClass, метода innerCallingInfoTest2")
    void callingInfoTest2() {

        callingInfo = InnerClass.innerCallingInfoTest2();

        actual = CLASS_NAME_STRING_VALUE + EQUALS + callingInfo.className() + ";"
            + METHOD_NAME_STRING_VALUE + EQUALS + callingInfo.methodName();

        Assertions.assertThat(actual).isEqualTo(
            "className=edu.hw2.Task4Test$InnerClass;methodName=innerCallingInfoTest2"
        );
    }

    // Utils-класс для второго теста.
    private final static class InnerClass {
        static CallingInfo innerCallingInfoTest2() {
            return Task4.callingInfo();
        }
    }
}
