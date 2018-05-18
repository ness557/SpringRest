package repository;

import connector.MySessionFactory;
import model.Student;
import org.hibernate.*;

import java.util.LinkedList;
import java.util.List;

public class StudentHibernateRepository implements StudentRepository {

    private SessionFactory sessionFactory;

    public StudentHibernateRepository(SessionFactory mySessionFactory){

        sessionFactory = mySessionFactory;
    }


    @Override
    public int addStudent(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student tmpStud = session.get(Student.class, student.getId());
        try {
            if (tmpStud == null) {

                session.save(student);

                session.getTransaction().commit();
                return 1;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public int updateStudent(Student student) {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.update(student);

            transaction.commit();
            return 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return 0;
    }

    @Override
    public int removeStudent(Student student) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return 0;
    }

    @Override
    public int removeStudent(int id) {

        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Student st = session.load(Student.class, id);
            System.out.println(st);

            session.delete(st);
            transaction.commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return 0;
    }

    @Override
    public List<Student> query(StudentSpecification specification) {

        StudentHibernateSpecification hibernateSpecification =
                (StudentHibernateSpecification) specification;

        try(Session session = sessionFactory.openSession()){

            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(hibernateSpecification.toCriteria());


            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LinkedList<>();


    }
}
