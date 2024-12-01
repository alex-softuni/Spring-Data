import entities.Address;
import entities.Employee;
import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        newAddress(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
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



