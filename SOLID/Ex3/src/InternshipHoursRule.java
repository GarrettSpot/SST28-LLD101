public class InternshipHoursRule implements EligibilityRule {
    private final int minHours;

    public InternshipHoursRule(int minHours) {
        this.minHours = minHours;
    }

    @Override
    public String evaluate(StudentProfile profile) {
        return null;
    }
}
