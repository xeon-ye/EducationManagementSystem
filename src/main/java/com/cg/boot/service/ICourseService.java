package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.ChooseCourse;
import com.cg.boot.model.Course;

/**
 * @author Madhuri. This interface contains abstract methods CourseService
 *         class.
 *
 */
public interface ICourseService {

	Course addCourse(Course course);

	Course getCourse(int id);

	List<Course> getAllCourses();

	Course updateCourse(Course course);

	List<Course> deleteCourse(int courseId, int userId);

	List<ChooseCourse> getChoosedCoursesByStudentId(int studentId);

	ChooseCourse getChoosedCourseDetails(int courseId, int studentId);

}
