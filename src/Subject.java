public class Subject {
    private String subjectName;
    private String facultyName;
    private int lecturesPerWeek;
    private boolean lab;

    public Subject(String subjectName, String facultyName, int lecturesPerWeek, boolean lab) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be null or empty.");
        }
        if (facultyName == null || facultyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Faculty name cannot be null or empty.");
        }
        if (lecturesPerWeek <= 0) {
            throw new IllegalArgumentException("Lectures per week must be greater than 0.");
        }

        this.subjectName = subjectName;
        this.facultyName = facultyName;
        this.lecturesPerWeek = lecturesPerWeek;
        this.lab = lab;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public int getLecturesPerWeek() {
        return lecturesPerWeek;
    }

    public boolean isLab() {
        return lab;
    }

    public String toString() {
        return "Subject Name: " + subjectName + ", Faculty Name: " + facultyName + ", Lectures Per Week: " + lecturesPerWeek + ", Lab: " + lab;
    }
}
