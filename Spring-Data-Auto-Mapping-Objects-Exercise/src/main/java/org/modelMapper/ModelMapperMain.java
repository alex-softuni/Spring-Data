package org.modelMapper;

import org.modelMapper.entities.Employee;
import org.modelMapper.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;

public class ModelMapperMain {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();

        Employee employee = new Employee("Alex","Atanasov",123.34);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        modelMapper.map(employee, employeeDTO);

        System.out.println(employeeDTO);
    }
}
