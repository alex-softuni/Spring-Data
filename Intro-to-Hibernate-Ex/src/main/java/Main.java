import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        getTownsSizeBiggerThanFive(em);
        em.getTransaction().commit();
        em.close();
    }

    private static void getTownsSizeBiggerThanFive(EntityManager em) {
        em.createQuery("FROM Town WHERE length(name) > 5", Town.class).getResultStream().forEach(town -> System.out.printf("%s%n",town.getName()));
    }
}
