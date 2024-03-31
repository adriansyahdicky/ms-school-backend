package com.ms.coursemanagement.base.searchspec;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericSpecification<T> implements Specification<T>, Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    private transient List<SearchCriteria> searchCriteria;
    private List<SortParameter> sortParameters;

    public GenericSpecification() {
        this.searchCriteria = new ArrayList<>();
        this.sortParameters = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteria.add(criteria);
    }

    public void addSort(SortParameter sortParameter) {
        sortParameters.add(sortParameter);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : searchCriteria) {

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.GREATER_THAN))
                    .ifPresent(searchOperation -> predicates.add(builder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue().toString())));

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.LESS_THAN))
                    .ifPresent(searchOperation -> predicates.add(builder.lessThan(
                            root.get(criteria.getKey()), criteria.getValue().toString())));

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.NOT_EQUAL))
                    .ifPresent(searchOperation -> predicates.add(builder.notEqual(
                            root.get(criteria.getKey()), criteria.getValue().toString())));

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.EQUAL))
                    .ifPresent(searchOperation -> {
                        if (criteria.getValue() instanceof Boolean value) {
                            Optional.of(value)
                                    .filter(Boolean.TRUE::equals)
                                    .ifPresentOrElse(
                                            v -> predicates.add(builder.isTrue(root.get(criteria.getKey()))),
                                            () -> predicates.add(builder.isFalse(root.get(criteria.getKey())))
                                    );
                        } else
                            predicates.add(builder.equal(
                                    root.get(criteria.getKey()), criteria.getValue()));
                    });

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.MATCH))
                    .ifPresent(searchOperation -> predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%")));

            Optional.of(criteria.getOperation())
                    .filter(searchOperation -> searchOperation.equals(SearchOperation.MATCH_END))
                    .ifPresent(searchOperation -> predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%")));
        }


        // Add sorting conditions
        Optional.of(sortParameters)
                .filter(params -> !params.isEmpty())
                .ifPresent(params -> {
                    List<Order> orders = new ArrayList<>();
                    for (SortParameter sortParameter : sortParameters) {
                        String fieldName = sortParameter.getFieldName();
                        boolean ascending = sortParameter.isAscending();

                        if (ascending)
                            orders.add(builder.asc(root.get(fieldName)));
                        else
                            orders.add(builder.desc(root.get(fieldName)));
                    }
                    query.orderBy(orders);
                });

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
