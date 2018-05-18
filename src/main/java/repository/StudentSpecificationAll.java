package repository;

import model.Student;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class StudentSpecificationAll implements StudentSpecification, StudentHibernateSpecification {


    @Override
    public boolean specified(Student student) {
        return true;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.ge("id", 0);
    }

    @Override
    public boolean equals(Object obj) {

         return (obj instanceof StudentSpecificationAll);
    }
}
