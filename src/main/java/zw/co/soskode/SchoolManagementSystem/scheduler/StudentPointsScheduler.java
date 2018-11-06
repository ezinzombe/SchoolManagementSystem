package zw.co.soskode.SchoolManagementSystem.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zw.co.soskode.SchoolManagementSystem.model.*;
import zw.co.soskode.SchoolManagementSystem.repository.RecommendedProgrammesRepository;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinzombe on Sep
 */
@Component
public class StudentPointsScheduler {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RecommendedProgrammesRepository recommendedProgrammesRepository;

    @Scheduled(cron = "*/18 * * * * ?")
    public void initializeStudentsPoints() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setPoints(0);
            studentRepository.save(student);
        }
    }

    private int total;
    private int subjectPoints;

    @Scheduled(cron = "*/20 * * * * ?")
    public void configureStudentPoints() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            List<Integer> studentTotalPoints = new ArrayList<>();
            List<String> subjects = new ArrayList<>();
            List<RecomendedProgramme> recomendedProgrammes = new ArrayList<>();

            for (Grades g : student.getGrades()) {
                studentTotalPoints.add(g.getMarkPoints());
                subjects.add(g.getSubject().getName());
            }
            int sum = studentTotalPoints.stream().mapToInt(Integer::intValue).sum();

            Boolean MATHEMATICS = subjects.contains("MATHEMATICS");
            Boolean BIOLOGY = subjects.contains("BIOLOGY");
            Boolean CHEMISTRY = subjects.contains("CHEMISTRY");
            Boolean PHYSICS = subjects.contains("PHYSICS");
            if (sum >= 13 && MATHEMATICS && BIOLOGY && CHEMISTRY) {
                recomendedProgrammes.add(ProgrameNames.medicine);
                recomendedProgrammes.add(ProgrameNames.pharmacy);
                recomendedProgrammes.add(ProgrameNames.compScience);
                for (RecomendedProgramme programme : recomendedProgrammes) {
                    programme.setStudent(student);
                    recommendedProgrammesRepository.save(programme);
                }
            } else if (sum >= 13 && MATHEMATICS && PHYSICS && CHEMISTRY) {
                recomendedProgrammes.add(ProgrameNames.physics);
                recomendedProgrammes.add(ProgrameNames.pharmacy);
                recomendedProgrammes.add(ProgrameNames.compScience);
                for (RecomendedProgramme programme : recomendedProgrammes) {
                    programme.setStudent(student);
                    recommendedProgrammesRepository.save(programme);
                }
            } else {
                recomendedProgrammes.add(ProgrameNames.hr);
                recomendedProgrammes.add(ProgrameNames.bed);
                for (RecomendedProgramme programme : recomendedProgrammes) {
                    programme.setStudent(student);
                    recommendedProgrammesRepository.save(programme);
                }
            }


            student.setPoints(sum);
            student.setProgrammes(recomendedProgrammes);
            System.out.println("-----------------------SUM+++++++++++++++++++++++++" + sum + "POINTS OBTAINED");
            System.out.println("-----------------------PROGRAMMES+++++++++++++++++++++++++" + recomendedProgrammes + "PROGRAMMES OBTAINED");
            studentRepository.save(student);
        }

        System.out.println("==========================CRON RUN CHECK DONE =========================" + students);
    }


//    @Scheduled(cron = "*/25 * * * * ?")
//    public void recommendCUTPrograms() {
//        List<Student> students = studentRepository.findAll();
//        System.out.println("==================INITIALIZING======================");
//        List<RecomendedProgramme> recomendedProgrammes = new ArrayList<>();
//
//
//        for (Student student : students) {
//            List<String> subjects = new ArrayList<>();
//            List<Grades> gradesList = student.getGrades();
//            for (Grades g : gradesList) {
//                subjects.add(g.getSubject().getName());
//                Boolean MATHEMATICS = subjects.contains("MATHEMATICS");
//                Boolean BIOLOGY = subjects.contains("BIOLOGY");
//                Boolean CHEMISTRY = subjects.contains("CHEMISTRY");
//                Boolean PHYSICS = subjects.contains("PHYSICS");
//
//                if (student.getPoints() >= 13 && MATHEMATICS && BIOLOGY && CHEMISTRY) {
//                    recomendedProgrammes.add(ProgrameNames.medicine);
//                    recomendedProgrammes.add(ProgrameNames.pharmacy);
//                    recomendedProgrammes.add(ProgrameNames.compScience);
//                    for (RecomendedProgramme programme : recomendedProgrammes) {
//                        programme.setStudent(student);
//                        recommendedProgrammesRepository.save(programme);
//                    }
//                    student.setProgrammes(recomendedProgrammes);
//                    studentRepository.save(student);
//                    break;
//                }
//                else if (student.getPoints() >= 13 && MATHEMATICS && PHYSICS && CHEMISTRY) {
//                    recomendedProgrammes.add(ProgrameNames.physics);
//                    recomendedProgrammes.add(ProgrameNames.pharmacy);
//                    recomendedProgrammes.add(ProgrameNames.compScience);
//                    for (RecomendedProgramme programme : recomendedProgrammes) {
//                        programme.setStudent(student);
//                        recommendedProgrammesRepository.save(programme);
//                    }
//                    student.setProgrammes(recomendedProgrammes);
//                    studentRepository.save(student);
//                    break;
//                } else {
//                    recomendedProgrammes.add(ProgrameNames.riskManagement);
//                    recomendedProgrammes.add(ProgrameNames.hr);
//                    recomendedProgrammes.add(ProgrameNames.bed);
//                    for (RecomendedProgramme programme : recomendedProgrammes) {
//                        programme.setStudent(student);
//                        recommendedProgrammesRepository.save(programme);
//                    }
//                    student.setProgrammes(recomendedProgrammes);
//                    studentRepository.save(student);
//                    break;
//                }
//            }
//        }
//
//
//        System.out.println("==========================PROGRAMMES ADDED =========================" + recomendedProgrammes);
//    }


}
