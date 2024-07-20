<template>
    <div>
        <label for="employee">選擇員工:</label>
        <select v-model="selectedEmployee" @change="selectEmployee">
            <option v-for="employee in employees" :key="employee.emp_id" :value="employee">
                {{ employee.emp_id }}, {{ employee.name }}
            </option>
        </select>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            employees: [],
            selectedEmployee: null,
        };
    },
    created() {
        this.fetchEmployees();
    },
    methods: {
        fetchEmployees() {
            axios.get("/api/employee/all_employees").then((response) => {
                this.employees = response.data;
                if (this.employees.length > 0) {
                    this.selectedEmployee = this.employees[0];
                    this.$emit("employee-selected", this.selectedEmployee);
                }
            });
        },
        selectEmployee() {
            this.$emit("employee-selected", this.selectedEmployee);
        },
    },
};
</script>
