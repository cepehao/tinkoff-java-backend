package edu.hw2;

/***********************************************************************************************************************
 * ДЗ 2 задание 4. Кто вызвал функцию?
 *
 * Напишите статическую функцию callingInfo, которая возвращает
 *
 * public record CallingInfo(String className, String methodName) {}
 *
 * Для получения доступа к стеку вызова используйте метод Throwable#getStackTrace.
 **********************************************************************************************************************/

final class Task4 {

    private Task4() {

    }

    public static CallingInfo callingInfo() {
        // Получаем стек вызовов
        var stackTrace = new Throwable().getStackTrace();

        // Если длина стека равна 1 => callingInfo вызван из метода main.
        // [0] - Тут хранится информация по последнему методу в стеке т.е. по самому методу callingInfo.
        // [1] - Метод, в котором произошел вызов последнего метода т.е. метода callingInfo
        var callingMethod = stackTrace[1];

        return new CallingInfo(callingMethod.getClassName(), callingMethod.getMethodName());
    }

    public record CallingInfo(String className, String methodName) { }
}
