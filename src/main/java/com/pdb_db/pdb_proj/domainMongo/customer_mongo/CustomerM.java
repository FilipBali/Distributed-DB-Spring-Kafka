package com.pdb_db.pdb_proj.domainMongo.customer_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class CustomerM
{
    @Id
    private Integer id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String email;
    private String phone_number;
    private String state;
    private String city;
    private String street_name;
    private Integer house_number;
    private Integer postcode;

    public CustomerM(){};
    public CustomerM(Integer id){
        this.id=id;
    };
    public CustomerM(Integer id, String name, String surname, String email, String phone_number, String state,
                     String city, String street_name, Integer house_number, Integer postcode) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.state = state;
        this.city = city;
        this.street_name = street_name;
        this.house_number = house_number;
        this.postcode = postcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public Integer getHouse_number() {
        return house_number;
    }

    public void setHouse_number(Integer house_number) {
        this.house_number = house_number;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

}
