package com.example.PROYECTOv1.Services;

import com.example.PROYECTOv1.Entities.Comment;
import com.example.PROYECTOv1.Entities.Teacher;
import com.example.PROYECTOv1.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher obtenerteacherPorId(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + teacherId));
    }

    public Teacher guardarteacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void eliminarTeacher(Long teacherid) {
        teacherRepository.deleteById(teacherid);
    }

    public List<Teacher> obtenerteachers() {
        return teacherRepository.findAll();
    }

}
