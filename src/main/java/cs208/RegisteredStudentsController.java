package cs208;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;


@RestController
public class RegisteredStudentsController
{

    /**
     * GET /registered_students
     *
     * @return a list of registered students (extracted from a join between
     * registered_students, students and classes tables in the database) as JSON
     */
    @GetMapping(value = "/registered_students", produces = MediaType.APPLICATION_JSON_VALUE)
    ArrayList<RegisteredStudentJoinResult> registered_students()
    {
        ArrayList<RegisteredStudentJoinResult> listOfRegisteredStudentJoinResults = Main.database.listAllRegisteredStudents();

        return listOfRegisteredStudentJoinResults;
    }


    /**
     * POST /add_student_to_class
     * with the following form parameters:
     *      studentId
     *      classId
     *
     * The parameters passed in the body of the POST request will be inserted
     * into the registered_students table in the database.
     */
    // TODO: implement this route
    @PostMapping(value = "/add_student_to_class")
    void registerStudent(
        @RequestParam("studentId") int studentId,
        @RequestParam("classId") int classId
    )
    {
        Student studentToEnroll = Main.database.getStudentWithId(studentId);
        if (studentToEnroll == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "failed to enroll the student with id = " + studentId + " in the database because it does not exist"
            );
        }
        Class classToEnroll = Main.database.getClassWithId(classId);
        if (classToEnroll == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "failed to enroll the class with id = " + classId + " in the database because it does not exist"
            );
        }
    }

    
    /**
     * DELETE /drop_student_from_class
     * with the following form parameters:
     *      studentId
     *      classId
     *
     * Deletes the student with id = {studentId} from the class with id = {classId}
     * from the registered_students in the database.
     *
     * @throws ResponseStatusException: a 404 status code if the student with id = {studentId} does not exist
     * @throws ResponseStatusException: a 404 status code if the class with id = {classId} does not exist
     */
    // TODO: implement this route
    @DeleteMapping(value = "/drop_student_from_class")
    void dropStudent(
        @RequestParam("studentId") int studentId,
        @RequestParam("classId") int classId
    )
    {
        System.out.println("studentId = " + studentId);
        System.out.println("classId = " + classId);
        
        try{
            Student studentToEnroll = Main.database.getStudentWithId(studentId);
            if (studentToEnroll == null) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "failed to drop the student with id = " + studentId + " in the database because it does not exist"
                );
            }
            Class classToEnroll = Main.database.getClassWithId(classId);
            if (classToEnroll == null) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "failed to drop the class with id = " + classId + " in the database because it does not exist"
                );
            }
            Main.database.dropStudent(studentId, classId);
        }
        catch (SQLException e)
        {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, // 422 error code
                "failed to drop the student with id = " + studentId + " and class id = " + classId + " from the database"
            );
        }
    }

    /**
     * GET /students_taking_class/{classCode}
     *
     * @return a list of registered students (extracted from a join between
     * registered_students, students and classes tables in the database) as JSON
     * that are taking the class {classCode}
     */
    // TODO: implement this route



    /**
     * GET /classes_in_which_student_is_enrolled/{studentId}
     *
     * @return a list of all classes (extracted from a join between
     * registered_students, students and classes tables in the database) as JSON
     * in which the student with id = {studentId} is enrolled
     *
     * @throws ResponseStatusException: a 404 status code if the student with id = {studentId} does not exist
     */
    // TODO: implement this route

}
