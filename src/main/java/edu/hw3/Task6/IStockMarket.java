package edu.hw3.Task6;

public interface IStockMarket {
    /** Добавить акцию */
    void add(CStock stock);

    /** Удалить акцию */
    void remove(CStock stock);

    /** Самая дорогая акция */
    CStock mostValuableStock();
}
