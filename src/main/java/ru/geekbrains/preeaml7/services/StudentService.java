package ru.geekbrains.preeaml7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.preeaml7.entities.Student;
import ru.geekbrains.preeaml7.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public Optional<Student> findById(Long id){
        return studentRepository.findById( id );
    }

    public List<Student> findAll() {
        return  studentRepository.findAll();
    }

    public void deleteById(Long id){
        studentRepository.deleteById( id );
    }

    public Student saveOrUpdate(Student student){
        return studentRepository.save(student);
    }
}
