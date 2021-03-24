package sample;

public class StudentRecord {

    private String sid;
    private double midterm;
    private double assignments;
    private double final_exam;
    private double final_mark;
    private String letter_grade;

    public StudentRecord(String sid, double assignments, double midterm, double final_exam) {
        this.sid = sid;
        this.midterm = midterm;
        this.assignments = assignments;
        this.final_exam = final_exam;
        final_mark = 0.2*assignments + 0.3*midterm + 0.5*final_exam;

        if (final_mark >= 80) {
            letter_grade = "A";
        } else if (final_mark >= 70) {
            letter_grade = "B";
        } else if (final_mark >= 60) {
            letter_grade = "C";
        } else if (final_mark >= 50) {
            letter_grade = "D";
        } else {
            letter_grade = "F";
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    public double getAssignments() {
        return assignments;
    }

    public void setAssignments(double assignments) {
        this.assignments = assignments;
    }

    public double getFinal_exam() {
        return final_exam;
    }

    public void setFinal_exam(double final_exam) {
        this.final_exam = final_exam;
    }

    public double getFinal_mark() {
        return final_mark;
    }

    public void setFinal_mark(double final_mark) {
        this.final_mark = final_mark;
    }

    public String getLetter_grade() {
        return letter_grade;
    }

    public void setLetter_grade(String letter_grade) {
        this.letter_grade = letter_grade;
    }

}
