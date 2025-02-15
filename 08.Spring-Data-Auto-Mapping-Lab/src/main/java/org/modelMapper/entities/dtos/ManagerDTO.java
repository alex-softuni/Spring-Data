package org.modelMapper.entities.dtos;

import org.modelMapper.entities.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<Employee> staff;

    public ManagerDTO() {}

    public ManagerDTO(String firstName, String lastName, List<Employee> staff) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staff = staff;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Employee> getStaff() {
        return staff;
    }

    public void setStaff(List<Employee> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        String formattedStaff = staff.stream()
                .map(s -> " - " + s.toString())
                .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s",
                firstName, lastName, staff.size(), formattedStaff);
    }
}
