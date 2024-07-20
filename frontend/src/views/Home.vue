<template>
    <div>
        <EmployeeSelector @employee-selected="handleEmployeeSelected" />
        <SeatMap :seatingChart="seatingChart" @seat-selected="handleSeatSelected" />
        <button @click="submitSelection">送出</button>
    </div>
</template>

<script>
import EmployeeSelector from "../components/EmployeeSelector.vue";
import SeatMap from "../components/SeatMap.vue";
import axios from "axios";

export default {
    components: {
        EmployeeSelector,
        SeatMap,
    },
    data() {
        return {
            seatingChart: [],
            selectedEmployee: null,
            selectedSeat: null,
        };
    },
    created() {
        this.fetchSeatingChart();
    },
    methods: {
        fetchSeatingChart() {
            axios.get("/api/seatingchart/all_seating").then((response) => {
                this.seatingChart = response.data;
            });
        },
        handleEmployeeSelected(employee) {
            this.selectedEmployee = employee;
        },
        handleSeatSelected(seat) {
            if (seat.isOccupied && seat.employee.empId !== this.selectedEmployee.empId) {
                alert("座位已被佔用！");
                return;
            }
            this.selectedSeat = seat;
        },
        submitSelection() {
            if (this.selectedEmployee && this.selectedSeat) {
                axios
                    .post("/api/employee/change_seat", {
                        emp_id: this.selectedEmployee.emp_id,
                        new_floor_seat_seq: this.selectedSeat.floor_seq_no,
                    })
                    .then(() => {
                        this.fetchSeatingChart();
                    });
            }
        },
    },
};
</script>
