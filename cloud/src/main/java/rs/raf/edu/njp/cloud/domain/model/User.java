package rs.raf.edu.njp.cloud.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public User() {
    }

    public User(String username, String pasword, String firstName, String lastName) {
        this.username = username;
        this.password = pasword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String username, String password, String firstName, String lastName) {
        this(username, password, firstName, lastName);
        this.id = id;
    }


}
