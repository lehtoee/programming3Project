package fi.tuni.prog3.sisu_fxml;

import java.util.List;
import java.util.TreeMap;

    /**
     * 
     * @author Visa
     */
public class StudyGroupModule {

    TreeMap<String, CourseModule> courseModules = new TreeMap<>();
    private String name;
    private String moduleId;
    private String code;
    private Credits credits;
    private boolean allMandatory;

    /**
     * 
     * @param name
     * @param moduleId
     * @param code
     * @param min
     * @param max
     * @param allMandatory
     * @param courses 
     */
    public StudyGroupModule(String name, String moduleId, String code, String min, String max, boolean allMandatory, List<CourseModule> courses) {
        this.name = name;
        this.moduleId = moduleId;
        this.code = code;
        this.credits = new Credits(min, max);
        this.allMandatory = allMandatory;
        for (CourseModule m : courses) {
            courseModules.put(m.getName(), m);
        }
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return 
     */
    public String getModuleId() {
        return this.moduleId;
    }

    /**
     * 
     * @return 
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 
     * @return 
     */
    public boolean getAllMandatory() {
        return this.allMandatory;
    }

    /**
     * 
     * @return 
     */
    public Credits getCredits() {
        return this.credits;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String mand = "No";
        if (this.allMandatory) {
            mand = "Yes";
        }
        return String.format(
                "%s\n id: %s\n code: %s\n credits: %s\n mandatory: %s",
                this.name,
                this.moduleId,
                this.code,
                this.credits.toString(),
                mand
        );
    }
}
