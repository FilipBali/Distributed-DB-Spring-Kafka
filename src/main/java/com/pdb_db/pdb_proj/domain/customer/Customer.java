package com.pdb_db.pdb_proj.domain.customer;


import com.pdb_db.pdb_proj.utilities.rest_operationType;
import javax.persistence.*;

@Entity
@Table
public class Customer implements Customerlnterface {

  @Id
  @SequenceGenerator(
          name = "customer_sequence",
          sequenceName = "customer_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "customer_sequence"
  )
  private Integer id;
  private String name;
  private String surname;
  private String email;
  private String phone_number;
  private String state;
  private String city;
  private String street_name;
  private Integer house_number;
  private Integer postcode;

  // CRUD operation
  @Transient
  private rest_operationType rest_operation;


  public Customer(){}
  public Customer(Integer id){
    this.id=id;
  }
  public Customer(Integer id,
                  String name,
                  String surname,
                  String email,
                  String phone_number,
                  String state,
                  String city,
                  String street_name,
                  Integer house_number,
                  Integer postcode){
    this.id=id;
    this.name = name;
    this.surname = surname;
    this.email=email;
    this.phone_number = phone_number;
    this.state = state;
    this.city = city;
    this.street_name = street_name;
    this.house_number = house_number;
    this.postcode = postcode;
  }

  public Customer(String name,
                  String surname,
                  String email,
                  String phone_number,
                  String state,
                  String city,
                  String street_name,
                  Integer house_number,
                  Integer postcode){
    this.name = name;
    this.surname = surname;
    this.email=email;
    this.phone_number = phone_number;
    this.state = state;
    this.city = city;
    this.street_name = street_name;
    this.house_number = house_number;
    this.postcode = postcode;
  }

  public Customer(String name,
                  String surname,
                  String email,
                  String state,
                  String city,
                  String street_name,
                  Integer house_number,
                  Integer postcode){
    this.name = name;
    this.surname = surname;
    this.email=email;
    this.state = state;
    this.city = city;
    this.street_name = street_name;
    this.house_number = house_number;
    this.postcode = postcode;
  }


  @Override
  public Integer getId() {
    return id;
  }
  @Override
  public void setId(Integer id) {
    this.id = id;
  }


  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String getSurname() {
    return surname;
  }
  @Override
  public void setSurname(String surname) {
    this.surname = surname;
  }


  @Override
  public String getEmail() {
    return email;
  }
  @Override
  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public String getPhone_number() {
    return phone_number;
  }
  @Override
  public void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }


  @Override
  public String getState() {
    return state;
  }
  @Override
  public void setState(String state) {
    this.state = state;
  }


  @Override
  public String getCity() {
    return city;
  }
  @Override
  public void setCity(String city) {
    this.city = city;
  }


  @Override
  public String getStreet_name() {
    return street_name;
  }
  @Override
  public void setStreet_name(String street_name) {
    this.street_name = street_name;
  }


  @Override
  public Integer getHouse_number() {
    return house_number;
  }
  @Override
  public void setHouse_number(Integer house_number) {
    this.house_number = house_number;
  }


  @Override
  public Integer getPostcode() {
    return postcode;
  }
  @Override
  public void setPostcode(Integer postcode) {
    this.postcode = postcode;
  }

  public rest_operationType getOperation(){ return rest_operation; }
  public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }

}
