package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private long phoneNumber;

    @ManyToOne(optional = false)
    private Client client;

    public Contract() {
    }

    public Contract(long phoneNumber, Client client) {
        this.phoneNumber = phoneNumber;
        this.client = client;
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
