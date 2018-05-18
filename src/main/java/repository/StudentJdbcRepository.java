package repository;

import jdbc.JdbcConnector;
import model.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentJdbcRepository implements StudentRepository {

    private JdbcConnector connector;

    public StudentJdbcRepository(JdbcConnector connector){

        this.connector = connector;
    }

    @Override
    public int addStudent(Student student) {

        String insertQuery = "INSERT INTO students VALUES (?, ?, ?)";

        Connection connection = connector.getConnection();

        if(connection != null){

            try {
                PreparedStatement statement = connection.prepareStatement(insertQuery);

                statement.setInt(1, student.getId());
                statement.setString(2, student.getName());
                statement.setString(3, student.getGroup());

                statement.executeUpdate();

                statement.close();
                connection.close();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }
        return 0;
    }

    @Override
    public int updateStudent(Student student) {

        String updateQuery =
                "UPDATE students SET name = ?, groupp = ? WHERE id = ?";
        Connection connection = connector.getConnection();

        if(connection != null){

            try {
                PreparedStatement statement = connection.prepareStatement(updateQuery);

                statement.setInt(3, student.getId());
                statement.setString(1, student.getName());
                statement.setString(2, student.getGroup());

                statement.executeUpdate();

                statement.close();
                connector.closeConnection();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }
        return 0;
    }

    @Override
    public int removeStudent(Student student) {

        String deleteQuery = "DELETE FROM students WHERE id = ?";
        Connection connection = connector.getConnection();

        if(connection != null){

            try {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);

                statement.setInt(1, student.getId());

                statement.executeUpdate();

                statement.close();
                connector.closeConnection();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return 0;
    }

    public int removeStudent(int id){

        String deleteQuery = "DELETE FROM students WHERE id = ?";
        Connection connection = connector.getConnection();

        if(connection != null){

            try {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);

                statement.setInt(1, id);

                statement.executeUpdate();

                statement.close();
                connector.closeConnection();
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return 0;
    }

    @Override
    public List<Student> query(StudentSpecification specification) {
        Connection connection = connector.getConnection();

        List<Student> students = new LinkedList<>();

        StudentJdbcSpecification jdbcSpecification = (StudentJdbcSpecification) specification;
        if(connection != null) {

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM students " + jdbcSpecification.toSql());

                while (rs.next()){
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("groupp")));

                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }


}
