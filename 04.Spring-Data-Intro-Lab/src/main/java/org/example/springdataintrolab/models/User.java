package org.example.springdataintrolab.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Basic
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
        this.accounts = new ArrayList<>();
    }

    public User(String pesho, int age) {
        this.username = pesho;
        this.age = age;
    }

    public User(int id, String username, int age, List<Account> accounts) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
