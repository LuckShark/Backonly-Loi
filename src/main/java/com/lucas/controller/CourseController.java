package com.lucas.controller;

import com.lucas.model.Course;
import com.lucas.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses") //É importante ter esse /api para poder diferenciar o que vem do back
@AllArgsConstructor
public class CourseController {

    private CourseRepository courseRepository;

    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll(); //vai fazer um SELECT * FROM table ....
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course){

        return courseRepository.save(course);

    }
    @PutMapping("/{id}") //atenção: além do body, preciso do ID
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(recordFound);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") //isso indica que vou receber essa variável no path (URL)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    //Como retornar o que foi removido??? usa no content
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


