package dao;

import entity.Semester;
import entity.Student;
import entity.StudyModule;
import global.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dao {

    static Connection con = DatabaseConnection.getConnection();
    Logger logger = Logger.getLogger(Dao.class.getName());

    // STUDENT
    private Student createStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM student WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return createStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    public Student getStudentByLastName(String name) {
        String query = "SELECT * FROM student WHERE last_name = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return createStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    public int addStudent(Student student) {
        String query = "INSERT INTO student(id, first_name, last_name) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());

            logger.fine("Student '" + student + "' added.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }

    public int updateStudent(Student student) {
        String query = "UPDATE student SET first_name = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, student.getFirstName());
            ps.setInt(2, student.getId());

            logger.fine("Student '" + student + "' updated.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }

    // SEMESTER
    private Semester createSemesterFromResultSet(ResultSet rs) throws SQLException {
        Semester semester = new Semester();
        semester.setId(rs.getString("id"));
        semester.setName(rs.getString("name"));
        return semester;
    }

    public Semester getSemesterById(String id) {
        String query = "SELECT * FROM semester WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Semester semester = new Semester();
                semester.setId(rs.getString("id"));
                semester.setName(rs.getString("name"));
                return semester;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    public int addSemester(Semester semester) {
        String query = "INSERT INTO semester(id, name) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, semester.getId());
            ps.setString(2, semester.getName());

            logger.fine("Semester with id '" + semester + "' added.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }

    public Semester getSemesterByName(String name) {
        String query = "SELECT * FROM semester WHERE name = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return createSemesterFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    // MODULE
    private StudyModule createStudyModuleFromResultSet(ResultSet rs) throws SQLException {
        StudyModule module = new StudyModule();
        module.setId(rs.getString("id"));
        module.setName(rs.getString("name"));
        return module;
    }

    public StudyModule getStudyModuleById(String id) {
        String query = "SELECT * FROM module WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                StudyModule module = new StudyModule();
                module.setId(rs.getString("id"));
                module.setName(rs.getString("name"));
                return module;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    public int addStudyModule(StudyModule module) {
        String query = "INSERT INTO module(id, name) VALUES (?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, module.getId());
            ps.setString(2, module.getName());

            logger.fine("StudyModule '" + module + "' added.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }

    public StudyModule getStudyModuleByName(String name) {
        String query = "SELECT * FROM module WHERE name = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return createStudyModuleFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return null;
    }

    // STUDENT_SEMESTER
    public int addStudentSemester(Student student, Semester semester) {
        String query = "INSERT IGNORE INTO student_semester VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, student.getId());
            ps.setString(2, semester.getId());

            logger.fine("Student '" + student + "' enrolled in Semester '" + semester + "'.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }

    public int deleteStudentSemester(Student student, Semester semester) {
        String query = "DELETE FROM student_semester WHERE student_id = ? AND semester_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, student.getId());
            ps.setString(2, semester.getId());

            logger.fine("Student '" + student + "' unsubscribed from Semester '" + semester + "'.");
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "", e);
        }
        return 0;
    }
}
