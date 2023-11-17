package edu.project2.solver;

public final class CFactorySolver {
    private static final String BFS = "BFS";
    private static final String DFS = "DFS";

    private CFactorySolver() {

    }

    public static ISolver getSolver(String solverType) {
        switch (solverType) {
            case BFS -> {
                return new CBFSSolver();
            }
            case DFS -> {
                return new CDFSSolver();
            }
            default -> throw new RuntimeException();
        }
    }
}
