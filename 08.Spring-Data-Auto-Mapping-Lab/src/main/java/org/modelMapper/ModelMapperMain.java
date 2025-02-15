package org.modelMapper;

import org.modelMapper.entities.Employee;
import org.modelMapper.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;


public class ModelMapperMain {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();

        Employee employee = new Employee("Andrew", "Jones", new BigDecimal("123.45"));
        Employee manager = new Employee("John", "Neumann", new BigDecimal("1234.45"));

        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        EmployeeDTO managerDTO = mapper.map(manager, EmployeeDTO.class);

        System.out.println(employeeDTO);
        System.out.println(managerDTO);


    }
}
