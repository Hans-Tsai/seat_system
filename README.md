# 員工座位系統

## 系統需求
實作人資部門使用的員工座位安排系統，功能需包含以下：
- 顯示各樓層座位
    - 從資料庫讀取各樓層座位資訊。
    - 使用有不同顏色來標示各樓層空位、己佔用座位(需顯示員工編號)。
- 調整座位
    - 每位員工只能佔用一個座位。
    - 使用下拉選單選擇員工(員編長度固定 5 碼)。
    - 選完員工後點選空座位後，原本空位顏色需變成請選擇顏色。
    - 己佔用座位可以被清除為空位。
    - 按[送出]按鈕後將座位資訊寫到資料庫。
- 系統架構要求
    - 使用 Web Server+ Application Server+任一關聯式資料庫的三層式架構。
    - 後端依照需求設計展示層、業務層、資料層以及共用層。 
- 技術要求：
    - 使用 Vue.js 做為前端技術。
    - 使用 Spring Boot 搭建相關應用程式。
    - 使用 RESTful API 風格建立後端服務。
    - 使用 Maven 或 Gradle 做為專案建立的工具。
    - 透過 Stored Procedure 存取資料庫。
    - 需同時異動多個資料表時，請實作 Transaction，避免資料錯亂。
    - 資料庫的 DDL 和 DML 請存放在專案下的\DB 資料夾內提供。
    - 需防止 SQL Injection 以及 XSS 攻擊。

## 資料庫設計
### employee (員工資料表)

|      欄位名稱      |         說明          |
|:---:|:---:|
|     emp_id     |  員編 (Primary Key)   |
|      name      |        員工姓名         |
|     email      |       員工電子郵件        |
| floor_seat_seq | 座位資訊序號(Foreign key) |

### seating_chart (樓層座位表)

|      欄位名稱      |  說明   |
|:---:|:---:|
| floor_seat_seq |  序號   |
|    floor_no    | 樓層編號  |
|    seat_no     | 座位編號  |
|  is_occupied   | 是否已佔用 |

### Stored Procedure
更新員工表、座位表

```sql
USE backend;

DELIMITER //
CREATE PROCEDURE UpdateEmployee(IN emp_id INT, IN new_floor_seat_seq INT)
BEGIN
	UPDATE employee
    SET floor_seat_seq = new_floor_seat_seq
    WHERE emp_id = emp_id;
END //
DELIMITER ;

--
DELIMITER //
CREATE PROCEDURE UpdateOriginSeatingChart(IN origin_floor_seat_seq INT)
BEGIN
	UPDATE seating_chart
    SET is_occupied = 0
    WHERE floor_seat_seq = origin_floor_seat_seq;
END //
DELIMITER ;

-- 
DELIMITER //
CREATE PROCEDURE UpdateNewSeatingChart(IN new_floor_seat_seq INT)
BEGIN
	UPDATE seating_chart
    SET is_occupied = 1
    WHERE floor_seat_seq = new_floor_seat_seq;
END //
DELIMITER ;
```
