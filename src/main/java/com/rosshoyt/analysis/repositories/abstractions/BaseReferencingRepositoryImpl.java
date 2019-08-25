package com.rosshoyt.analysis.repositories.abstractions;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

/**
 * see https://www.baeldung.com/spring-data-jpa-method-in-all-repositories
 * @param <T>
 * @param <ID>
 */

public class BaseReferencingRepositoryImpl<T, ID extends  Serializable>
      extends SimpleJpaRepository<T, ID> implements BaseReferencingRepository<T,ID> {
   private EntityManager entityManager;

   public BaseReferencingRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
      super(entityInformation, entityManager);
      this.entityManager = entityManager;

   }
   @Transactional
   public List<T> findByFkMidiFileAnalysisId(Long fkMidiFileAnalysisId){
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
      Root<T> root = criteriaQuery.from(getDomainClass());
      criteriaQuery
            .select(root)
            .where(criteriaBuilder
                  .equal(root.<Long>get("fkMidiFileAnalysisId"), fkMidiFileAnalysisId));
                  //.like(root.<String>get(attributeName), "%" + text + "%"));
      TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
      return query.getResultList();
   }
//   @Transactional
//   public List<T> findByAttributeContainsText(String attributeName, String text) {
//      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//      CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
//      Root<T> root = cQuery.from(getDomainClass());
//      cQuery
//            .select(root)
//            .where(builder
//                  .like(root.<String>get(attributeName), "%" + text + "%"));
//      TypedQuery<T> query = entityManager.createQuery(cQuery);
//      return query.getResultList();
//   }
}
