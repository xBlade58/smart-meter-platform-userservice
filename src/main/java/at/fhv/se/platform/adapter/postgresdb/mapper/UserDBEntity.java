package at.fhv.se.platform.adapter.postgresdb.mapper;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * @author Justin Ströhle
 * 07.12.2023
 */

@Entity
@Table(name = "meter_user")
public class UserDBEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;

    public UserDBEntity() {
    }

    public UserDBEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
