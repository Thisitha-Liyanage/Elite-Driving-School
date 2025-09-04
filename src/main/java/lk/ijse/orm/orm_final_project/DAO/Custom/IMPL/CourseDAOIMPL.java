package lk.ijse.orm.orm_final_project.DAO.Custom.IMPL;

import lk.ijse.orm.orm_final_project.BO.Exceptions.NotFound;
import lk.ijse.orm.orm_final_project.Config.FactoryConfiguration;
import lk.ijse.orm.orm_final_project.DAO.Custom.CourseDAO;
import lk.ijse.orm.orm_final_project.Entity.Course;
import lk.ijse.orm.orm_final_project.Entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseDAOIMPL implements CourseDAO {
    private final FactoryConfiguration factoryConfiguration =FactoryConfiguration.getInstance();

    @Override
    public boolean save(Course course) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            session.persist(course);

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
    public boolean update(Course course) throws SQLException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Course updateCourse = session.get(Course.class , course.getID());

            if(updateCourse == null){
                transaction.rollback();
                return false;
            }



            updateCourse.setName(course.getName());
            updateCourse.setDescription(course.getDescription());
            updateCourse.setDurationType(course.getDurationType());
            updateCourse.setDuration_time(course.getDuration_time());
            updateCourse.setFee(course.getFee());
            session.merge(updateCourse);

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
    public List<Course> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Course> query = session.createQuery(
                    "from Course",
                    Course.class
            );
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String getNextID() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Course c ORDER BY c.id DESC",
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
    public Optional<Course> searchByID(String s) throws SQLException {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Course course = session.get(Course.class, s);

            transaction.commit();

            return Optional.ofNullable(course);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return Optional.empty();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteCourse(String ID) throws SQLException {

        Session session = null;
        Transaction transaction = null;
        try {
            session = factoryConfiguration.getSession();
            transaction = session.beginTransaction();

            Course deleteCourse = session.get(Course.class , ID);


            if(deleteCourse == null){
                transaction.rollback();
                return false;
            }

            session.remove(deleteCourse);

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
}
