/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu_fxml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
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
public class StudyTest {

    public StudyTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Study testing...");

    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Study tested!");

    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Study.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        List<StudyGroupModule> sgm = new ArrayList<>();

        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);
        String expResult = "studyid1";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGroupId method, of class Study.
     */
    @Test
    public void testGetGroupId() {
        System.out.println("getGroupId");
        List<StudyGroupModule> sgm = new ArrayList<>();
        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);
        String expResult = "groupid1";
        String result = instance.getGroupId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Study.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        List<StudyGroupModule> sgm = new ArrayList<>();
        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);
        String expResult = "studycode1";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Study.
     */
    @Test
    public void testGetname() {
        System.out.println("getname");
        List<StudyGroupModule> sgm = new ArrayList<>();
        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);

        String expResult = "studyname1";
        String result = instance.getname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPeriodIds method, of class Study.
     */
    @Test
    public void testGetPeriodIds() {
        System.out.println("getPeriodIds");
        List<StudyGroupModule> sgm = new ArrayList<>();
        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);

        String[] expResult = {"1", "2"};
        String[] result = instance.getPeriodIds();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getCredits method, of class Study.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        List<StudyGroupModule> sgm = new ArrayList<>();
        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};

        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);

        String expResult = new Credits("0", "1").toString();
        String result = instance.getCredits().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Study.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        List<StudyGroupModule> sgm = new ArrayList<>();
        TreeMap<String, StudyGroupModule> GroupModules = new TreeMap<>();

        List<CourseModule> cm1 = new ArrayList<>();
        cm1.add(new CourseModule("name1", "code1", "courseid1", "0", "1"));
        cm1.add(new CourseModule("name2", "code2", "courseid2", "1", "2"));
        List<CourseModule> cm2 = new ArrayList<>();
        cm2.add(new CourseModule("name3", "code3", "courseid3", "2", "3"));
        cm2.add(new CourseModule("name4", "code4", "courseid4", "1", "2"));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm1));
        sgm.add(new StudyGroupModule("name1", "module1", "code1", "2", "5", true, cm2));
        String[] sl = {"1", "2"};
        for (StudyGroupModule m : sgm) {
            GroupModules.put(m.getModuleId(), m);
        }
        Study instance = new Study("studyid1", "groupid1", "studycode1", "studyname1", sl, "0", "1", sgm);
        String expResult = "studyname1\n id: studyid1\n groupId: groupid1\n code: studycode1\n curriculumPeriodIds: " + Arrays.toString(sl) + "\n credits: {\n   min: 0\n   max: 1\n}\n GroupModules:\n" + GroupModules.toString();
        String result = instance.toString();

        System.out.println("Expt result lenght: " + expResult.length());
        System.out.println("Real result lenght: " + result.length());
        System.out.println(result.compareTo(expResult));
        assertEquals(expResult, result);
    }
}
