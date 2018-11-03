package zw.co.soskode.SchoolManagementSystem.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zw.co.soskode.SchoolManagementSystem.model.Grades;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zinzombe on Sep
 */
@Component
public class StudentPointsScheduler {

    @Autowired
    private StudentRepository studentRepository;

    @Scheduled(cron = "*/9 * * * * ?")
    public void initializeStudentsPoints() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setPoints(0);
            studentRepository.save(student);
        }
    }

    private int total;
    private int subjectPoints;

    @Scheduled(cron = "*/10 * * * * ?")
    public void configureStudentPoints() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            List<Integer> studentTotalPoints = new ArrayList<>();
            for (Grades g : student.getGrades()) {
                studentTotalPoints.add(g.getMarkPoints());
            }
            int sum = studentTotalPoints.stream().mapToInt(Integer::intValue).sum();

            student.setPoints(sum);
            System.out.println("-----------------------SUM+++++++++++++++++++++++++" + sum + "POINTS OBTAINED");
            studentRepository.save(student);
        }

        System.out.println("==========================CRON RUN CHECK DONE =========================" + students);
    }
}
