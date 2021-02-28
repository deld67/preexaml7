package ru.geekbrains.preeaml7.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.preeaml7.entities.Student;
import ru.geekbrains.preeaml7.services.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")

public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String showAllStudents(Model model){
        try {
            model.addAttribute( "students", studentService.findAll());
            model.addAttribute( "s_size", studentService.findAll().size());
        }catch (NullPointerException e){
            model.addAttribute( "students", null);
            model.addAttribute( "s_size", 0);
        }
        return "students";
    }
    @PostMapping("/add")
    public String addStudent(Model model, @RequestBody Student s){
        s.setId(null);
        studentService.saveOrUpdate( s );
        return "redirect:/students";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student s = studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("student", s);
        return "edit_student";
    }
    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Student student) {
        studentService.saveOrUpdate(student);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneStudentById(@PathVariable Long id) {
        studentService.deleteById(id);
        return "ok";
    }

}
