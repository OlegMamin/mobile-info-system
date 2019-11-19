package ru.levelup.junior.entities;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Positive
    private long phoneNumber;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne
    private Tariff tariff;

    @ManyToMany
    private Set<Option> options;

    public Contract() {
    }

    public Contract(long phoneNumber, Client client, Tariff tariff) {
        this.phoneNumber = phoneNumber;
        this.client = client;
        this.tariff = tariff;
        this.options = new HashSet<>();
    }
    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
