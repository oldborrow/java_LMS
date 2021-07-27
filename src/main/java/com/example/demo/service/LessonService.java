package com.example.demo.service;

import com.example.demo.dao.LessonRepository;
import com.example.demo.domain.Lesson;
import com.example.demo.dto.LessonDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonService {

    private final LessonRepository lessonRepository;

    public CourseService courseService;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findAllById(Long id) {
        return lessonRepository.findAllById(id);
    }

    public LessonDto findById(Long id) {
        Lesson lesson = findLessonById(id);
        return new LessonDto(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getText(),
                lesson.getCourse().getId());
    }

    public void save(LessonDto lessonDto) {
        Lesson lesson = new Lesson(lessonDto.getId(), lessonDto.getTitle(), lessonDto.getText());
        lesson.setCourse(courseService.findById(lessonDto.getCourseId()));
        lessonRepository.save(lesson);
    }

    public List<LessonDto> findAllForLessonIdWithoutText(@Param("id") long id) {
        return lessonRepository.findAllForLessonIdWithoutText(id);
    }

    public void delete(Long id) {
        lessonRepository.delete(findLessonById(id));
    }

    private Lesson findLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }
}
