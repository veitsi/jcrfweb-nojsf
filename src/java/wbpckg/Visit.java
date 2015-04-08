package wbpckg;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

public class Visit {

    @XmlElement
    public int id;
    @XmlElement
    public int ptn;
    @XmlElement
    public int nmr;
    @XmlElement
    public static final Date dt = new Date();
    @XmlElement
    int ttr;

    public Visit() {
    }

    public Visit(int id, int ptn, int nmr, int ttr) {
        super();
        if (ttr < 300 || ttr > 420) {
            throw new IllegalArgumentException("bad temperature");
        }
        this.id = id;
        this.ptn = ptn;
        this.nmr = nmr;
        this.ttr = ttr;
    }

    public int getId() {
        return id;
    }

    public int getPtn() {
        return ptn;
    }

    public String getPtnS() {
        if (this.id == 0) {
            return "";
        }
        return "" + ptn;
    }

    public String getNmrS() {
        if (this.id == 0) {
            return "";
        }
        return "" + nmr;
    }
    public String getDtS(){
        if (this.id == 0) {
            return "";
        }
        return dt.toString();
    }

    public String getTtrS() {
        if (this.id == 0) {
            return "";
        }
        String tmps = ((Integer) ttr).toString();
        return tmps.substring(0, 2) + "." + tmps.substring(2);
    }

    @Override
    public String toString() {
        return "Visit [ptn=" + ptn + ", nmr=" + nmr + ", ttr=" + getTtrS()
                + ", dt=" + dt + "]";
    }

}
