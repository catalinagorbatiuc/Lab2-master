package testing.CSV;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Valute")
public class Valute {
    @XStreamAlias("ID")
    @XStreamAsAttribute
    private String id;
    @XStreamAlias("NumCode")
    private String numCode;
    @XStreamAlias("CharCode")
    private String charCode;
    @XStreamAlias("Nominal")
    private int nominal;
    @XStreamAlias("Name")
    private String name;
    @XStreamAlias("Value")
    private double value;


    public String getNumCode() {
        return numCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return ("ID " + id + "\nNumCode " + numCode + "\nCharCode " + charCode + "\nNominal " + nominal + "\nName " + name + "\nValue " + value);
    }

    public Object getСharCode() {
        return charCode;
    }

}
