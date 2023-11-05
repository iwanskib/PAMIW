package com.yourcompany.taskapi.controller;

import com.yourcompany.taskapi.model.NewEntity;
import com.yourcompany.taskapi.service.NewEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/newentity")
public class NewEntityController {
    private final NewEntityService newEntityService;
    @Autowired
    public NewEntityController(NewEntityService newEntityService) {
        this.newEntityService = newEntityService;
    }
    @GetMapping
    public List<NewEntity> getAllNewEntities() {
        return newEntityService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewEntity> getNewEntityById(@PathVariable Long id) {
        return newEntityService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public NewEntity createNewEntity(@RequestBody NewEntity newEntity) {
        return newEntityService.create(newEntity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewEntity> updateNewEntity(@PathVariable Long id, @RequestBody NewEntity newEntity) {
        try {
            NewEntity updatedEntity = newEntityService.update(id, newEntity);
            return ResponseEntity.ok(updatedEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNewEntity(@PathVariable Long id) {
        try {
            newEntityService.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
