package com.yourcompany.taskapi.service;

import com.yourcompany.taskapi.model.NewEntity;
import com.yourcompany.taskapi.repository.NewEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NewEntityService {
    private final NewEntityRepository newEntityRepository;
    @Autowired
    public NewEntityService(NewEntityRepository newEntityRepository) {
        this.newEntityRepository = newEntityRepository;
    }
    public List<NewEntity> findAll() {
        return newEntityRepository.findAll();
    }
    public Optional<NewEntity> findById(Long id) {
        return newEntityRepository.findById(id);
    }
    @Transactional
    public NewEntity create(NewEntity newEntity) {
        return newEntityRepository.save(newEntity);
    }
    @Transactional
    public NewEntity update(Long id, NewEntity newEntityDetails) {
        Optional<NewEntity> optionalEntity = findById(id);
        if (optionalEntity.isPresent()) {
            NewEntity existingEntity = optionalEntity.get();
            return newEntityRepository.save(existingEntity);
        } else {
            throw new IllegalArgumentException("Entity with id " + id + " not found");
        }
    }
    @Transactional
    public void delete(Long id) {
        Optional<NewEntity> entity = findById(id);
        entity.ifPresent(newEntityRepository::delete);
    }
}
