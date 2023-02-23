package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductCustomRepositoryImpl implements ProductCustomRepository{
    final EntityManager entityManager;
    final StoreRepository storeRepository;

    public ProductCustomRepositoryImpl(EntityManager entityManager, StoreRepository storeRepository) {
        this.entityManager = entityManager;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Product> findByFilter(Product filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate storePredicate = null, categoryPredicate = null;

        if(filter.getStoreId() == 0)
            storePredicate = criteriaBuilder.notEqual(productRoot.get("storeId"), filter.getStoreId());
        else
            storePredicate = criteriaBuilder.equal(productRoot.get("storeId"), filter.getStoreId());

        if(filter.getCategoryId() == 0)
            categoryPredicate = criteriaBuilder.notEqual(productRoot.get("categoryId"), filter.getCategoryId());
        else
            categoryPredicate = criteriaBuilder.equal(productRoot.get("categoryId"), filter.getCategoryId());

        criteriaQuery.where(storePredicate, categoryPredicate);

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
