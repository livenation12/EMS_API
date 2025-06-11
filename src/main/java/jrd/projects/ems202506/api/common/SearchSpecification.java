package jrd.projects.ems202506.api.common;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.Attribute;

public class SearchSpecification {

	public static <E> Specification<E> searchByField(String searchBy, String keyword) {
		return (root, query, builder) -> {
			if (searchBy == null || searchBy.isBlank()) {
				// If searchBy is null or empty, search across all attributes
				Set<Attribute<? super E, ?>> attributes = root.getModel().getAttributes();
				Predicate predicate = builder.conjunction();

				// Iterate over all attributes and create a "LIKE" condition for each one
				for (Attribute<? super E, ?> attribute : attributes) {
					if (attribute.getJavaType().equals(String.class)) { // Search only on String-type fields
						predicate = builder.or(
								predicate,
								builder.like(builder.lower(root.get(attribute.getName())), "%" + keyword.toLowerCase() + "%")
								);
					}
				}

				return predicate;
			} else {
				// If searchBy is not null, search only on the specified attribute
				return builder.like(
						builder.lower(root.get(searchBy)),
						"%" + keyword.toLowerCase() + "%"
						);
			}
		};
	}
}
