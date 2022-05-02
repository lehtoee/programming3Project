/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Visa
 */
public class CreditsTest {

    public CreditsTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Credits testing...");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Credits tested!");
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMin method, of class Credits.
     */
    @Test
    public void testGetMin() {
        System.out.println("Credits.getMin()");
        Credits instance1 = new Credits("0", "1");
        Credits instance2 = new Credits("1", "2");
        Integer expResult1 = 0;
        Integer expResult2 = 1;
        Integer result1 = instance1.getMin();
        Integer result2 = instance2.getMin();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertNotEquals(expResult1, result2);
        assertNotEquals(expResult2, result1);
    }

    /**
     * Test of getMax method, of class Credits.
     */
    @Test
    public void testGetMax() {
        System.out.println("Credits.getMax()");
        Credits instance1 = new Credits("0", "1");
        Credits instance2 = new Credits("1", "2");
        Integer expResult1 = 1;
        Integer expResult2 = 2;
        Integer result1 = instance1.getMax();
        Integer result2 = instance2.getMax();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertNotEquals(expResult1, result2);
        assertNotEquals(expResult2, result1);
    }

    /**
     * Test of toString method, of class Credits.
     */
    @Test
    public void testToString() {
        System.out.println("Credits.toString()");
        Credits instance1 = new Credits("0", "1");
        Credits instance2 = new Credits("1", "2");
        String expResult1 = "{\n   min: 0\n   max: 1\n}";
        String result1 = instance1.toString();
        String expResult2 = "{\n   min: 1\n   max: 2\n}";
        String result2 = instance2.toString();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

}
