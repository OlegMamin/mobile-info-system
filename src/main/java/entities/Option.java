package entities;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 64, unique = true)
    private String name;

    @Column
    private double costOfConnection;

    @Column
    private double monthlyPayment;

    @ManyToOne(optional = false)
    private Tariff tariff;

    public Option() {
    }

    public Option(String name, double costOfConnection, double monthlyPayment) {
        this.name = name;
        this.costOfConnection = costOfConnection;
        this.monthlyPayment = monthlyPayment;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostOfConnection() {
        return costOfConnection;
    }

    public void setCostOfConnection(double costOfConnection) {
        this.costOfConnection = costOfConnection;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
