Use seat_system;

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS seating_chart;

CREATE TABLE seating_chart (
    floor_seat_seq INT AUTO_INCREMENT PRIMARY KEY,
    floor_no INT NOT NULL,
    seat_no INT NOT NULL,
    is_occupied TINYINT(1) DEFAULT 0
);

CREATE TABLE employee (
  emp_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  floor_seat_seq INT,
  FOREIGN KEY (floor_seat_seq) REFERENCES seating_chart(floor_seat_seq)
);

-- 設置 empId 的 AUTO_INCREMENT 起始值為 10000
ALTER TABLE employee AUTO_INCREMENT = 10000;