package com.agency.sm360.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.agency.sm360.entity.Listing;
import com.agency.sm360.entity.ListingStatus;

@Repository
public class ListingRepositoryImpl implements ListingRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Listing> findByDealer(String dealerId, ListingStatus listingState) {
		return entityManager.createQuery("FROM Listing l WHERE l.dealer.id = :dealerId AND l.status = :status")
				.setParameter("dealerId", dealerId).setParameter("status", listingState).getResultList();
	}

	@Override
	public Listing findById(String id) {
		try {
			return (Listing) entityManager.createQuery("FROM Listing l WHERE l.id = :id").setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public void update(Listing entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(String id) {
		entityManager.remove(id);
	}

	@Override
	public Listing add(Listing entity) {
		entityManager.persist(entity);
		return entity;
	}

}
