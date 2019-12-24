package info;

public class Student {
    private  String xm;
    private  String tm;
    private  String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getId() {
        return id;
    }

    public String getXm() {
        return xm;
    }

    public String getTm() {
        return tm;
    }

    public String toString() {
        return " "+ xm + " " + tm + " "+ id +" \n";
    }
}
