package info;

public class Topic {
    private String subject;
    private String teacher;
    private String classnumber;

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }
    public String toString() {
        return " "+ subject + " " + teacher + " "+classnumber+" \n";
    }
}
