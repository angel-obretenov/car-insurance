package com.safety.car.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "multicriteria_id")
    private MulticriteriaTable multicriteriaTable;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Price() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public MulticriteriaTable getMulticriteriaTable() {
        return multicriteriaTable;
    }

    public void setMulticriteriaTable(MulticriteriaTable multicriteriaTable) {
        this.multicriteriaTable = multicriteriaTable;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}