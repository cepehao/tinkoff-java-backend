package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final String METHOD_NAME = "name";

    @SuppressWarnings({"MagicNumber", "UncommentedMain"})
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function lambdaFunction;

    @Setup
    @SneakyThrows
    public void setup() {
        student = new Student("Sergey", "Semenov");

        method = Student.class.getMethod(METHOD_NAME);

        methodHandle = MethodHandles.lookup().findGetter(Student.class, METHOD_NAME, String.class);

        CallSite callSite = LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            MethodHandles.lookup().findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class)),
            MethodType.methodType(String.class, Student.class)
        );

        lambdaFunction = (Function) callSite.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void reflectionAccess(Blackhole bh) {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void methodHandleAccess(Blackhole bh) {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    @SneakyThrows
    public void lambdaFunctionAccess(Blackhole bh) {
        String name = (String) lambdaFunction.apply(student);
        bh.consume(name);
    }

    private record Student(String name, String surname) {

    }
}
