package com.example.dockerize.springbootapp.repository;


import com.example.dockerize.springbootapp.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, Integer> {
}
