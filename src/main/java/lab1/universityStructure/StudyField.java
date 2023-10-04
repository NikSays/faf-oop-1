package lab1.universityStructure;

public enum StudyField {
    MECHANICAL_ENGINEERING("Mechanical Engineering"),
    SOFTWARE_ENGINEERING("Software Engineering"),
    FOOD_TECHNOLOGY("Food Technology"),
    URBANISM_ARCHITECTURE("Urbanism and Architecture"),
    VETERINARY_MEDICINE("Veterinary Medicine");

    
    private final String displayName;

    StudyField(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.displayName;
    }
}
