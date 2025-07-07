package noemibaglieri.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long userId;

    private String name;
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;


    //costruttore vuoto
    public User() {
    }

    //costruttore
    public User(Long userId, String name, String surname, LocalDate dateOfBirth) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


    public Long getId() {
        return userId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", card=" + card +
                '}';
    }
}
