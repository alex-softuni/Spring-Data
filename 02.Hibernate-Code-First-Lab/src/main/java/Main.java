import entities.Bike;
import entities.Car;
import entities.Plane;
import entities.Truck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicle_hierarchy");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Bike bike = new Bike();
        Car car = new Car();
        Plane plane = new Plane();
        Truck truck = new Truck();

        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
