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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // getTownsSizeBiggerThanFive(em);
        containsEmployee(em);

        em.getTransaction().commit();
        em.close();
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



