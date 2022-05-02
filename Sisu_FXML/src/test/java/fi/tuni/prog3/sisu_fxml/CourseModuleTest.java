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
public class CourseModuleTest {

    public CourseModuleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("CourseModule testing...");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("CourseModule tested!");
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getName method, of class CourseModule.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        CourseModule instance = new CourseModule("name1", "code1", "courseid1", "0", "1");
        String expResult = "name1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class CourseModule.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        CourseModule instance = new CourseModule("name1", "code1", "courseid1", "0", "1");
        String expResult = "code1";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCourseId method, of class CourseModule.
     */
    @Test
    public void testGetCourseId() {
        System.out.println("getCourseId");
        CourseModule instance = new CourseModule("name1", "code1", "courseid1", "0", "1");
        String expResult = "courseid1";
        String result = instance.getCourseId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCredits method, of class CourseModule.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        CourseModule instance = new CourseModule("name1", "code1", "courseid1", "0", "1");
        Credits expResult = new Credits("0", "1");
        String expString = expResult.toString();
        Credits result = instance.getCredits();
        String resultString = result.toString();
        assertEquals(expString, resultString);
    }

    /**
     * Test of toString method, of class CourseModule.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CourseModule instance = new CourseModule("name1", "code1", "courseid1", "0", "1");
        String expResult = "name1\n course id: courseid1\n code: code1\n credits: {\n   min: 0\n   max: 1\n}\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class CourseModule.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CourseModule instance1 = new CourseModule("name1", "code1", "courseid1", "0", "1");
        CourseModule instance2 = new CourseModule("name2", "code2", "courseid2", "1", "2");
        CourseModule instance3 = new CourseModule("name1", "code2", "courseid2", "1", "2");
        CourseModule instance4 = new CourseModule("name1", "code1", "courseid1", "0", "1");

        assertTrue(instance1.compareTo(instance4) == 0);
        assertTrue(instance1.compareTo(instance2) < 0);
        assertTrue(instance1.compareTo(instance3) < 0);
        assertTrue(instance2.compareTo(instance3) > 0);
    }

}
