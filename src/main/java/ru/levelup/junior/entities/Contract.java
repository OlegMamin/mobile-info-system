package ru.levelup.junior.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Size(min = 4)
    private String phoneNumber;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Tariff tariff;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Option> options = new ArrayList<>();

    public Contract() {
    }

    public Contract(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contract(@Size(min = 4) String phoneNumber, Tariff tariff) {
        this(phoneNumber);
        this.tariff = tariff;
    }

    public Contract(String phoneNumber, Client client, Tariff tariff) {
        this(phoneNumber, tariff);
        this.client = client;
    }
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
