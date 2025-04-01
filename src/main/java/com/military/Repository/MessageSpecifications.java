package com.military.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.military.Entity.Message;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class MessageSpecifications {                     
	
//	Filter by user ID
	public static Specification<Message> hasUserId(Integer userId){
		return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				-> userId == null ? null : cb.equal(root.get("recipientId"), userId);                                	
	}
	
//	Filter by keyword in message content
	public static Specification<Message> containsKeyword(String keyword){
		return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				-> (keyword == null || keyword.isEmpty()) ? null : cb.like(root.get("messageContent"), "%" + keyword + "%");        
	}
	
//	Filter by date range (StartDate - endDate)
	public static Specification<Message> isBetweenDates(LocalDateTime startDate, LocalDateTime endDate){
		return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				-> {
					Predicate predicate = cb.conjunction();
					if(startDate != null)
						predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("timestamp"), startDate));
					if(endDate != null)
						predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("timestamp"), endDate));
					return predicate;
				};
	}
	
//	Filter by read status
	public static Specification<Message> hasStatus(Message.Status status){
		return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				-> status == null ? null : cb.equal(root.get("status"), status); 
	}
	
}
 