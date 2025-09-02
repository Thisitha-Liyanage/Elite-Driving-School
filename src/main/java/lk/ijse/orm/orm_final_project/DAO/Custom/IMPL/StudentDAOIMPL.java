package lk.ijse.orm.orm_final_project.DAO.Custom.IMPL;

import org.hibernate.query.Query;
import lk.ijse.orm.orm_final_project.Config.FactoryConfiguration;
import lk.ijse.orm.orm_final_project.DAO.Custom.StudentDAO;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentDAOIMPL implements StudentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean delete(String id ) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Student deleteStudent = session.get(Student.class , id);


            if(deleteStudent == null){
                transaction.rollback();
                return false;
            }

            session.remove(deleteStudent);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean save(Student student) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Student student) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Student updateStudent = session.get(Student.class , student.getID());

            if(updateStudent == null){
                transaction.rollback();
                return false;
            }



            updateStudent.setName(student.getName());
            updateStudent.setDOB(student.getDOB());
            updateStudent.setContactNo(student.getContactNo());
            session.merge(updateStudent);

            transaction.commit();
            System.out.println("DAO true");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("DAO false");
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Student> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getNextID() throws SQLException {

        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT s.id FROM Student s ORDER BY s.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> list = query.list();
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Student> searchByID(String s) throws SQLException {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            String hql = "FROM Student WHERE ID = :id";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("id", s);

            Student student = query.uniqueResult();
            return Optional.ofNullable(student);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
