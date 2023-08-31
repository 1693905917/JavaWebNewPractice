package com.hzp.test;

import com.hzp.fruit.dao.FruitDAO;
import com.hzp.fruit.dao.impl.FruitDAOImpl;
import com.hzp.fruit.pojo.Fruit;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FruitDAOTest {

    @Test
    public void getFruitList() {
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        fruitDAO.getFruitList().forEach(System.out::println);

    }

    @Test
    public void addFruit() {
    }

    @Test
    public void updateFruit() {
    }

    @Test
    public void getFruitByFname() {
    }

    @Test
    public void delFruit() {
    }
}