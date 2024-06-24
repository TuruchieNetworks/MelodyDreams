package com.turuchie.melodydreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turuchie.melodydreams.models.User;;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	List<User> findAll();
	Optional<User> findById(Long id);
	Optional<User> findByEmail(String email);
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLastName(String lastName);
	boolean existsByEmailAndIdNot(String email, Long userId);   
	Optional<User> findByFirstNameContaining(String firstName);  
    Optional<User> findByLastNameContaining(String lastName);
	Optional<User> findByEmailAndPassword(String email, String password); 
    Optional<User> findByFirstNameContainingIgnoreCase(String partialName);
    Optional<User> findByLastNameContainingIgnoreCase(String partialName);
    Optional<User> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    Optional<User> findByFirstNameContainingOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT p FROM User p WHERE LOWER(p.firstName) LIKE %:partialName% OR LOWER(p.lastName) LIKE %:partialName%")
    List<User> findByPartialName(@Param("partialName") String partialName);
    @Query("SELECT p FROM User p WHERE LOWER(REPLACE(p.firstName, ' ', '')) LIKE %:partialName% OR LOWER(REPLACE(p.lastName, ' ', '')) LIKE %:partialName%")
    List<User> findByPartialNames(@Param("partialName") String partialName);
	List<User> findByDateOfBirthIsNotNull();
	List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
			String trimmedSearchName, String trimmedSearchName2);
}