import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hibernate-Code-First-Ex");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.close();
    }
}
