package com.example.demo.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.form.UserForm;

/**
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean saveUser(UserForm form) {

        UserEntity entity = new UserEntity();
        entity.setDisplayName(form.getDisplayName());
        entity.setName(form.getName());
        entity.setMailaddress(form.getMailaddress());

        UserEntity result = userRepository.save(entity);
        if (Objects.isNull(result)) {
            return false;
        }

        return true;

    }

    public UserEntity selectByIdUser(Integer id) {

        if (id == null) {
            return null;
        }

        UserEntity entity = userRepository.findById(id).orElse(null);
        return entity;

    }

    @Transactional
    public boolean deleteUser(Integer id) {

        UserEntity entity = userRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            return false;
        }

        userRepository.delete(entity);
        return true;

    }
}
