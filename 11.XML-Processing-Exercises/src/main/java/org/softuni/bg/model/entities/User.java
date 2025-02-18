package org.softuni.bg.model.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private int age;

    @ManyToMany
    private Set<User> friends;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> bought;
    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> sold;

    public User() {
    }

    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> bought) {
        this.bought = bought;
    }

    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sold) {
        this.sold = sold;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
