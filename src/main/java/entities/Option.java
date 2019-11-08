package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64, unique = true)
    private String name;

    @Column
    private double costOfConnection;

    @Column
    private double monthlyPayment;

    @ManyToOne(optional = false)
    private Contract contract;

    public Option() {
    }

    public Option(String name, double costOfConnection, double monthlyPayment, Contract contract) {
        this.name = name;
        this.costOfConnection = costOfConnection;
        this.monthlyPayment = monthlyPayment;
        this.contract = contract;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
