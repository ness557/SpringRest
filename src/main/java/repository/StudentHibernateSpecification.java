package repository;

import org.hibernate.criterion.Criterion;

public interface StudentHibernateSpecification {

    Criterion toCriteria();
}
