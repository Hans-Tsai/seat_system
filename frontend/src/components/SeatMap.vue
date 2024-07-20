<template>
    <div class="seat-map">
        <div class="seat-map">
            <div class="button-row" v-for="(group, index) in groupedSeatingChart" :key="index">
                <b-button class="b-button"
                    v-for="(seat, index) in group" 
                    :key="index" 
                    :variant="getButtonVariant(seat)"
                    @click="selectSeat(seat.floor_seat_seq)">
                    {{ seat.floor_no }}樓: 座位{{ seat.seat_no }}
                    <span v-if="seat.is_occupied">[員編: {{ findEmployeeName(seat.floor_seat_seq) }}]</span>
                </b-button>
            </div>
        </div>
        
        <div class="legend">
            
            <div class="legend-item">
                <div class="seat empty"></div>
                空位
            </div>
            <div class="legend-item">
                <div class="seat occupied"></div>
                已佔用
            </div>
            <div class="legend-item">
                <div class="seat selected"></div>
                請選擇
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            seatingChart: [],
            employees: [],
            selectedSeatSeq: null,
        };
    },
    created() {
        this.fetchSeats();
        this.fetchEmployees();
    },
    methods: {
        fetchSeats() {
            axios.get("/api/seatingchart/all_seating").then((response) => {
                this.seatingChart = response.data;
            });
        },
        fetchEmployees() {
            axios.get("/api/employee/all_employees").then((response) => {
                this.employees = response.data;
            });
        },
        findEmployeeName(floor_seat_seq) {
            const employee = this.employees.find(emp => emp.floor_seat_seq == floor_seat_seq);
            return employee ? employee.emp_id : '未知';
        },
        selectSeat(floor_seat_seq) {
            this.selectedSeatSeq = floor_seat_seq;
        },
        getButtonVariant(seat) {
            if (this.selectedSeatSeq === seat.floor_seat_seq) {
                return 'success'; // 被選中的按鈕
            }
            
            return seat.is_occupied ? 'danger' : ''; // 根據是否被佔用設定顏色
        },
    },
    computed: {
        groupedSeatingChart() {
            let result = [];
            let group = [];
            this.seatingChart.forEach((seat, index) => {
                group.push(seat);
                if ((index + 1) % 4 === 0) {
                    result.push(group);
                    group = [];
                }
            });
            if (group.length > 0) {
                result.push(group);
            }
            
            return result;
        },
    },
};
</script>

<style>
.button-row {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 20px; /* 調整按鈕組之間的間距 */
}
.b-button {
  margin-right: 20px; /* 調整按鈕之間的間距 */
}


.seat-map {
    display: grid;
}
.seat {
    width: 100px;
    height: 50px;
    margin: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}
.empty {
    background-color: darkgray;
}
.occupied {
    background-color: red;
}
.selected {
    background-color: green;
}
.legend {
    margin-top: 20px;
}
.legend-item {
    display: flex;
    align-items: center;
    margin-right: 10px;
}
</style>
