package com.hanstsai.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee") // 確保這裡的表名與數據庫表名一致
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int emp_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "floor_seat_seq")
    private int floor_seat_seq;

    // Getters and Setters
    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFloor_seat_seq() {
        return floor_seat_seq;
    }

    public void setFloor_seat_seq(int floor_seat_seq) {
        this.floor_seat_seq = floor_seat_seq;
    }
}
