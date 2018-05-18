package repository;

import model.Student;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class StudentSpecificationById implements StudentSpecification, StudentHibernateSpecification{

    private int id;

    public StudentSpecificationById(int id){

       this.id = id;
    }

    @Override
    public boolean specified(Student student) {
        return student.getId() == id;
    }

    @Override
    public Criterion toCriteria() {
        return Restrictions.eq("id", id);
    }

    @Override
    public boolean equals(Object obj) {

        return  (obj instanceof StudentSpecificationById && ((StudentSpecificationById) obj).id == this.id);

    }
}
