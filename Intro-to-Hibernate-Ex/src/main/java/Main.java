import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        findEmployeesMaximumSalaries(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void findEmployeesMaximumSalaries(EntityManager entityManager) {
        entityManager.createQuery("SELECT d.name,MAX(e.salary) FROM Department as d" +
                        " JOIN d.employees e" +
                        " GROUP BY d.name" +
                        " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList().forEach(o -> System.out.printf("%s %s%n", o[0], o[1]));
    }

    private static void findEmployeesByFirstNamePattern(EntityManager entityManager) {
        String pattern = SCANNER.nextLine() + "%";

        entityManager
                .createQuery("FROM Employee WHERE firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

    }

    private static void increaseSalaries(EntityManager entityManager) {
        List<Employee> employees = entityManager.createQuery("FROM Employee " +
                " WHERE department.name " +
                " IN('Engineering','Tool Design','Marketing','Information Services')", Employee.class).getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
            entityManager.persist(employee);
        }
    }

    private static void getLastTenProjects(EntityManager entityManager) {
        entityManager.createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC ", Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(p -> System.out.printf("Project name: %s%n" +
                        "\tProject Description: %s%n" +
                        "\tProject Start Date: %s%n" +
                        "\tProject End Date: %s%n", p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));
    }
    //2005-09-01 00:00:00.0

    private static void getEmployeeProjects(EntityManager entityManager) {
        long id = SCANNER.nextLong();
        StringBuilder sb = new StringBuilder();
        Employee employee = entityManager.find(Employee.class, id);
        sb.append(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle() + "\n");
        employee.getProjects().forEach(p -> sb.append("\t" + p.getName() + "\n"));

        System.out.println(sb.toString());
    }

    private static void addressesWithEmployeeCount(EntityManager entityManager) {
        entityManager.createQuery("SELECT a FROM Address a ORDER BY size(employees) DESC ", Address.class)
                .setMaxResults(10)
                .getResultList().forEach(a -> System.out.printf("%s,%s - %d employees%n", a.getText(), a.getTown().getName(), a.getEmployees().size()));

    }

    private static void newAddress(EntityManager entityManager) {
        /*
        1.Persisting new Address in the database
        2.Changing employee address by last name
         */
        String lastName = SCANNER.nextLine();
        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        Employee employee = entityManager.createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName).getSingleResult();
        employee.setAddress(address);
        entityManager.persist(employee);

    }

    private static void employeesFromDepartment(EntityManager entityManager) {

        String department = "Research and Development";
        entityManager.createQuery("FROM Employee  WHERE department.name = :department ORDER BY salary ASC", Employee.class)
                .setParameter("department", department)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s from %s - $%.2f%n", e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));
    }

    private static void employeesWithSalaryOver50000(EntityManager em) {
        em.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.println(e.getFirstName()));
    }

    private static void containsEmployee(EntityManager em) {
        String firstName = SCANNER.nextLine();
        String lastName = SCANNER.nextLine();

        TypedQuery<Employee> query = em.createQuery("FROM Employee WHERE firstName = :firstName AND lastName = :lastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);

        List<Employee> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            System.out.println("Employee " + firstName + " " + lastName + " not found");
        } else {
            System.out.println("Employee " + firstName + " " + lastName + " found");
        }
    }

    private static void getTownsSizeBiggerThanFive(EntityManager em) {
        em.createQuery("FROM Town WHERE length(name) > 5", Town.class).getResultStream().forEach(town -> System.out.printf("%s%n", town.getName()));
    }
}



