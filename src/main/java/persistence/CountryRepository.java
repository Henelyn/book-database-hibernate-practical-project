package persistence;

import model.Author;
import model.Country;
import util.DbUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepository {
    private EntityManager entityManager;

    public CountryRepository() {
        entityManager = DbUtil.getEntityManager();
    }

    public void saveCountry(Country country) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(country);
            entityManager.getTransaction().commit();
            System.out.println("Country saved");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void updateCountry(Country country) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(country);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteCountry(Country country) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(country));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public Country findCountryById(int countryId) {
        Country country = null;
        try {
            country = entityManager.find(Country.class, countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    public List<Country> listAllCountries() {
        return entityManager.createQuery("FROM Country", Country.class).getResultList();
    }
}
