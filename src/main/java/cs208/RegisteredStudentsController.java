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
    @PostMapping(value = "add_student_to_class")
    RegisteredStudentJoinResult create(
            @RequestParam("id") int studentId,
            @RequestParam("full_name") String studentFullName,
            @RequestParam("code") String code,
            @RequestParam("title") String title
    )
    {
        System.out.println("id        = " + studentId);
        System.out.println("full_name = " + studentFullName);
        System.out.println("code      = " + code);
        System.out.println("title     = " + title);
        try
        {
            RegisteredStudentJoinResult enrolledStudent = new RegisteredStudentJoinResult(studentId, studentFullName, code, title);
            Main.database.addNewClass(enrolledStudent);
            return createdClass;
        }
        catch (SQLException e)
        {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, // 422 error code
                    "failed to add new class to the database"
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
