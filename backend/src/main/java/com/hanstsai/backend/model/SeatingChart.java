package com.hanstsai.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seating_chart") // 確保這裡的表名與數據庫表名一致
public class SeatingChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floor_seat_seq")
    private int floor_seat_seq;

    @Column(name = "floor_no")
    private int floor_no;

    @Column(name = "seat_no")
    private int seat_no;

    @Column(name = "is_occupied")
    private int is_occupied;

    // Getters and Setters
    public int getFloor_seat_seq() {
        return floor_seat_seq;
    }

    public void setFloor_seat_seq(int floor_seat_seq) {
        this.floor_seat_seq = floor_seat_seq;
    }

    public int getFloor_no() {
        return floor_no;
    }

    public void setFloor_no(int floor_no) {
        this.floor_no = floor_no;
    }

    public int getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }

    public int getIs_occupied() {
        return is_occupied;
    }

    public void setIs_occupied(int is_occupied) {
        this.is_occupied = is_occupied;
    }
}
