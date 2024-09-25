package com.example.springboot.hibernatedemo.dao;

import com.example.springboot.hibernatedemo.entity.Course;
import com.example.springboot.hibernatedemo.entity.Instructor;
import com.example.springboot.hibernatedemo.entity.InstructorDetail;
import com.example.springboot.hibernatedemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findByInstructorId(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findByInstructorDetailId(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId (int theId);

    Instructor findInstructorByJoinFetch(int theId);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentByCourseId(int theId);

    Student findStudentAndCourseByStudentId(int theId);

    void update(Student student);

    void deleteStudnetById(int theId);
}
