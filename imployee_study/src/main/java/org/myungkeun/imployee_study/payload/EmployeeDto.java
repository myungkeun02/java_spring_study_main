package org.myungkeun.imployee_study.payload;

import lombok.Data;

@Data
public class EmployeeDto {
    private String id;
    private String name;
    private int age;
    private String sex;
    private String position;
}
