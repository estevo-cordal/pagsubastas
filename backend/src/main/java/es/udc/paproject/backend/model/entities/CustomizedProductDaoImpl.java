package es.udc.paproject.backend.model.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedProductDaoImpl implements CustomizedProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    private String[] getTokens(String keywords) {

        if (keywords == null || keywords.length() == 0) {
            return new String[0];
        } else {
            return keywords.split("\\s");
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Product> find(Long categoryId, String keywords, int page, int size) {

        String[] tokens = getTokens(keywords);
        String queryString = "SELECT p FROM Product p";

        queryString += " WHERE p.expirationDate > NOW() ";


        if (categoryId != null) {
            queryString += " AND p.category.id = :categoryId";
        }

        if (tokens.length != 0) {


            queryString += " AND ";


            for (int i = 0; i<tokens.length-1; i++) {
                queryString += "LOWER(p.name) LIKE LOWER(:token" + i + ") AND ";
            }

            queryString += "LOWER(p.name) LIKE LOWER(:token" + (tokens.length-1) + ")";


        }
        queryString += " ORDER BY p.name";

        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        if (tokens.length != 0) {
            for (int i = 0; i<tokens.length; i++) {
                query.setParameter("token" + i, '%' + tokens[i] + '%');
            }

        }

        List<Product> products = query.getResultList();
        boolean hasNext = products.size() == (size+1);

        if (hasNext) {
            products.remove(products.size()-1);
        }

        return new SliceImpl<>(products, PageRequest.of(page, size), hasNext);

    }

}
