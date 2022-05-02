/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import java.util.ArrayList;
import java.util.List;
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
public class StudyGroupModuleTest {

  public StudyGroupModuleTest() {
  }

  @BeforeAll
  public static void setUpClass() {
    System.out.println("StudyGroupModule testing...");
  }

  @AfterAll
  public static void tearDownClass() {
    System.out.println("StudyGroupModule tested!");
  }

  @BeforeEach
  public void setUp() {
  }

  @AfterEach
  public void tearDown() {
  }

  /**
   * Test of getName method, of class StudyGroupModule.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    String expResult = "name1";
    String result = instance.getName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getModuleId method, of class StudyGroupModule.
   */
  @Test
  public void testGetModuleId() {
    System.out.println("getModuleId");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    String expResult = "module1";
    String result = instance.getModuleId();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCode method, of class StudyGroupModule.
   */
  @Test
  public void testGetCode() {
    System.out.println("getCode");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    String expResult = "code1";
    String result = instance.getCode();
    assertEquals(expResult, result);
  }

  /**
   * Test of getAllMandatory method, of class StudyGroupModule.
   */
  @Test
  public void testGetAllMandatory() {
    System.out.println("getAllMandatory");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance1 = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    StudyGroupModule instance2 = new StudyGroupModule("name1", "module1", "code1", "0", "1", false, c);
    boolean expResult1 = true;
    boolean expResult2 = false;
    boolean result1 = instance1.getAllMandatory();
    boolean result2 = instance2.getAllMandatory();
    assertEquals(expResult1, result1);
    assertEquals(expResult2, result2);
  }

  /**
   * Test of getCredits method, of class StudyGroupModule.
   */
  @Test
  public void testGetCredits() {
    System.out.println("getCredits");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    String expResult = new Credits("0", "1").toString();
    String result = instance.getCredits().toString();
    assertEquals(expResult, result);
  }

  /**
   * Test of toString method, of class StudyGroupModule.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    List<CourseModule> c = new ArrayList<>();
    c.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
    c.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
    c.add(new CourseModule("name3", "code1", "courseid1", "0", "1"));

    StudyGroupModule instance = new StudyGroupModule("name1", "module1", "code1", "0", "1", true, c);
    String expResult = "name1\n id: module1\n code: code1\n credits: {\n   min: 0\n   max: 1\n}\n mandatory: Yes";
    String result = instance.toString();
    assertEquals(expResult, result);
  }

}
