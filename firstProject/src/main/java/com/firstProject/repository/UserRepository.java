package com.firstProject.repository;
// the last part of service dao - repository part
// this part is just an interface uses implementation of spring data jpa

import com.firstProject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// the extends crudrepostory gets all the required crud operations
// required all the types
public interface UserRepository extends JpaRepository<User,String> {
    // for all the crud operations and applicable to all the entities

@Query( "select tempuser from User tempuser WHERE tempuser.username =: name")
    public  User getUser(@Param("name") String id);

}
