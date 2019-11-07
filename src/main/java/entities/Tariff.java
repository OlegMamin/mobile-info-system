package entities;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class Tariff {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 32)
    private String name;

    @Column
    private double price;

    @OneToOne(optional = false)
    private Contract contract;

    public Tariff() {
    }

    public Tariff(String name, double price, Contract contract) {
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
