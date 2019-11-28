package ru.levelup.junior.entities;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64, unique = true)
    @Size(min = 2)
    private String name;

    @Column
    @PositiveOrZero
    private double costOfConnection;

    @Column
    @PositiveOrZero
    private double monthlyPayment;

    public Option() {
    }

    public Option(String name, double costOfConnection, double monthlyPayment) {
        this.name = name;
        this.costOfConnection = costOfConnection;
        this.monthlyPayment = monthlyPayment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != option.id) return false;
        if (Double.compare(option.costOfConnection, costOfConnection) != 0) return false;
        if (Double.compare(option.monthlyPayment, monthlyPayment) != 0) return false;
        return name != null ? name.equals(option.name) : option.name == null;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
