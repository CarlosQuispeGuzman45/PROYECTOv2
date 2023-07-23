package com.example.PROYECTOv1.Controllers;

import com.example.PROYECTOv1.Entities.Comment;
import com.example.PROYECTOv1.Entities.Teacher;
import com.example.PROYECTOv1.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/profesor")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping
    public Teacher guardarTeacher(@RequestBody Teacher teacher) {
        return teacherService.guardarteacher(teacher);
    }

    @GetMapping
    public List<Teacher> optenerTeachers() {
        return teacherService.obtenerteachers();
    }

    @DeleteMapping("/{id}")
    public void eliminarById(@PathVariable("id") Long id) {
        teacherService.eliminarTeacher(id);
    }

    @PutMapping
    public void actualizarTeacher(@RequestBody Teacher teacher) {
        teacherService.guardarteacher(teacher);
    }

    @GetMapping("/{id}")
    public Teacher listarId(@PathVariable("id") Long id) {
        return teacherService.obtenerteacherPorId(id);
    }


}
