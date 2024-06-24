package com.turuchie.melodydreams.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.turuchie.melodydreams.models.LoginUser;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public UserService() {}
	
	public Iterable<User> findAll() {
	    return userRepo.findAll();
	}

	public User registerUser(User registeringUser) {
	    if (userRepo.findByEmail(registeringUser.getEmail()).isPresent()) {
	        throw new RuntimeException("User with provided email already exists");
	    }

	    String hashedPassword = BCrypt.hashpw(registeringUser.getPassword(), BCrypt.gensalt());
	    registeringUser.setPassword(hashedPassword);
	    return userRepo.save(registeringUser);
	}

	public User getOne(String email) {
		Optional<User> user = userRepo.findByEmail(email);
		return user.isPresent() ? user.get() : null;
	}

	public User getOne(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	
	public User getOneUserByName(String firstName) {
		Optional<User> user = userRepo.findByFirstName(firstName);
		return user.isPresent() ? user.get() : null;
	}

	// Search Resources
	public List<User> getUsersByContainingLetters(String letters) {
	    Iterable<User> allUsers = userRepo.findAll();
	    
	    List<User> filteredUsers = new ArrayList<>();

	    for (User user : allUsers) {
	        String fullName = user.getFirstName() + " " + user.getLastName();
	        if (fullName.contains(letters)) {
	            filteredUsers.add(user);
	        }
	    }

	    return filteredUsers;
	}

	public List<User> getUsersByFilteredLetters(String letters) {
	    Iterable<User> allUsers = userRepo.findAll();
	    
	    List<User> filteredUsers = new ArrayList<>();

	    for (User user : allUsers) {
	        String fullName = user.getFirstName() + " " + user.getLastName();
	        if (fullName.toLowerCase().contains(letters.toLowerCase())) {
	            filteredUsers.add(user);
	        }
	    }

	    return filteredUsers;
	}

    // Search By Characters
    public List<User> searchUsersByCharacters(String searchTerm) {
        List<User> users = userRepo.findAll();
        return users.stream()
                .filter(user ->
                        (user.getFirstName() != null && user.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())) ||
                        (user.getLastName() != null && user.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))
                )
                .collect(Collectors.toList());
    }

	public List<User> getUsersByLetters(String letters) {
	    Iterable<User> allUsers = userRepo.findAll();
	    
	    List<User> filteredUsers = new ArrayList<>();

	    for (User user : allUsers) {
	        String fullName = user.getFirstName() + " " + user.getLastName();
	        if (fullName.contains(letters)) {
	            filteredUsers.add(user);
	        }
	    }

	    return filteredUsers;
	}

	public User getOneUserByFullName(String fullName) {
	    String[] nameParts = StringUtils.split(fullName, ' ');

	    if (nameParts != null && nameParts.length > 1) {
	        // If the name contains a space, assume it's a full name
	        String firstName = nameParts[0].toLowerCase();
	        String lastName = nameParts[1].toLowerCase();
	        return userRepo.findByFirstNameContainingOrLastNameContaining(firstName, lastName).orElse(null);
	    } else {
	        // If there's no space, treat it as a single name
	        String firstName = fullName.toLowerCase();
	        return userRepo.findByFirstName(firstName).orElse(null);
	    }
	}

    public List<User> getAllUsersMatchingSearchTerm(String searchTerm) {
        List<User> users = userRepo.findAll();
        return users.stream()
                .filter(user ->
                        (user.getFirstName() != null && user.getFirstName().contains(searchTerm)) ||
                        (user.getLastName() != null && user.getLastName().contains(searchTerm))
                )
                .collect(Collectors.toList());
    }
	
	public User login(LoginUser loginUser, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		
		User existingUser = getOne(loginUser.getEmail());
		if(existingUser == null) {
			result.rejectValue("email", "Unique" ,"Invalid Login Credentials");
			return null;
		}
		if(!BCrypt.checkpw(loginUser.getPassword(), existingUser.getPassword())) {
			result.rejectValue("password", "Matches" ,"Invalid Login Credentials");
			return null;
		}
		return existingUser;
	}

    // Helper method to set hashed passwords
    public void setHashedPasswords(User user, String password, String confirmPassword) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);

        String hashedConfirmPassword = BCrypt.hashpw(confirmPassword, BCrypt.gensalt());
        user.setConfirmPassword(hashedConfirmPassword);
    }

	@Transactional
	public User update(User userToEdit) {
		try {
			return userRepo.save(userToEdit);
		} catch (Exception e) {
	        e.printStackTrace();
	        // Re-throw the exception or handle it appropriately
	        throw new RuntimeException("Error updating user: " + e.getMessage(), e);
	    }
	}

    public boolean existsByEmailAndIdNot(String email, Long userId) {
        return userRepo.existsByEmailAndIdNot(email, userId);
    }

	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}
