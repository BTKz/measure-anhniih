package com.mycompany;


import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import com.mycompany.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;


    //testDeleteNonExistingUser bichihing tulduserrepository bichij ugsn doorh
    private UserRepository userRepository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("bat@gmail.com");
        user.setPassword("bat123456");
        user.setFirstName("Bat");
        user.setLastName("Dorj");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 65;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Integer userId =47;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 64;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

    @Test
    public void testEditUser() {
        // Save a new user to the database
        User user = new User();
        user.setEmail("edit1@gmail.com");
        user.setPassword("edit123456");
        user.setFirstName("Test");
        user.setLastName("Edit");

        User savedUser = repo.save(user);

        // Modify the user details
        savedUser.setEmail("newemail@gmail.com");
        savedUser.setFirstName("NewFirstName");
        savedUser.setLastName("NewLastName");

        // Save the changes
        User updatedUser = repo.save(savedUser);

        // Retrieve the user from the database and assert the changes
        User retrievedUser = repo.findById(savedUser.getId()).orElse(null);
        Assertions.assertThat(retrievedUser).isNotNull();
        Assertions.assertThat(retrievedUser.getEmail()).isEqualTo("newemail@gmail.com");
        Assertions.assertThat(retrievedUser.getFirstName()).isEqualTo("NewFirstName");
        Assertions.assertThat(retrievedUser.getLastName()).isEqualTo("NewLastName");
    }

//    @Test
//    @DirtiesContext
//    public void testDuplicateEmail() {
//        // Create a user with a specific set of values
//        User user1 = new User();
//        user1.setEmail("duplicate@gmail.com");
//        user1.setPassword("password123");
//        user1.setFirstName("John");
//        user1.setLastName("Doe");
//
//        // Mock the UserRepository to return user1 when saving with the specified email
//        when(userRepository.save(any(User.class))).thenReturn(user1);
//
//        // Save the first user
//        userRepository.save(user1);
//
//        // Attempt to save a second user with the same email (duplicate)
//        User user2 = new User();
//        user2.setEmail("duplicate@gmail.com");
//        user2.setPassword("password123");
//        user2.setFirstName("Jane");
//        user2.setLastName("Doe");
//
//        // Mock the UserRepository to throw a DataIntegrityViolationException when saving the second user
//        when(userRepository.save(any(User.class))).thenThrow(new DataIntegrityViolationException("Duplicate entry"));
//
//        // Attempt to save the second user should throw a DataIntegrityViolationException
//        try {
//            userRepository.save(user2);
//        } catch (DataIntegrityViolationException e) {
//            // Handle the exception or assert its presence as needed
//            // For example, you can check the exception message to ensure it's due to duplicate entry
//            // Assertions.assertThat(e.getMessage()).contains("Duplicate entry");
//        }
//    }

//    @Test
//    public void testDuplicateEmail() {
//        // Given
//        String existingEmail = "batkalit@gmail.com";
//        User existingUser = new User();
//        existingUser.setEmail(existingEmail);
//        existingUser.setPassword("hello2000");
//        existingUser.setFirstName("batka");
//        existingUser.setLastName("lit");
//        existingUser.setEnabled(false);
//
//        // Save the existing user to the database
//        userRepository.save(existingUser);
//
//        // When trying to save a user with the same email
//        User duplicateUser = new User();
//        duplicateUser.setEmail(existingEmail);
//        duplicateUser.setPassword("anotherPassword");
//        duplicateUser.setFirstName("Jane");
//        duplicateUser.setLastName("Doe");
//        duplicateUser.setEnabled(true);
//
//        // Then expect a DataIntegrityViolationException
//        assertThatThrownBy(() -> userRepository.save(duplicateUser))
//                .hasMessageContaining("could not execute statement; SQL [n/a]; nested exception is org.hibernate.exception.DataException: could not execute statement")
//                .hasMessageContaining("constraint [users.UK_6dotkott2kjsp8vw4d0m25fb7]");
//    }

    @Test
    public void testGetNonExistingUser() {

//      when().thenReturn();
//      mock test hiih utga

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((int) anyLong())).thenReturn(null); // Assuming findById returns null for non-existing user

        UserService userService = new UserService(userRepository);

        // haanaas ymr idtai hereglegch duudaha bichj bn
        User nonExistingUser = userService.getUserById(999L);

        // Asserttest
        assertNull(nonExistingUser, "Non-existing user should not be found");
    }

    @Test
    public void testExistingUser() {
        // ugugdsn
        Integer existingUserId = 6; // end baigaa id tai hereglegchin dugaarig tawina
        String existingUserEmail = "dorj@gmail.com";
        String existingUserPassword = "asdqwe";
        String existingUserFirstName = "Dorj";
        String existingUserLastName = "Gotow";

        //when
        User existingUser = new User();
        existingUser.setId(existingUserId);
        existingUser.setEmail(existingUserEmail);
        existingUser.setPassword(existingUserPassword);
        existingUser.setFirstName(existingUserFirstName);
        existingUser.setLastName(existingUserLastName);

        //then
        assertThat(existingUser.getId()).isEqualTo(existingUserId);
        assertThat(existingUser.getEmail()).isEqualTo(existingUserEmail);
        assertThat(existingUser.getPassword()).isEqualTo(existingUserPassword);
        assertThat(existingUser.getFirstName()).isEqualTo(existingUserFirstName);
        assertThat(existingUser.getLastName()).isEqualTo(existingUserLastName);
        assertThat(existingUser.isEnabled()).isTrue(); // logicdoo taaruulaad taaruulj bolno true or false
    }

//    @Test
//    public void testDeleteNonExistingUser() {
//        // ugsun
//        User nonExistingUser = new User(/* Set user details for a non-existing user */);
//        nonExistingUser.setEmail("john.doe@example.com");
//        nonExistingUser.setPassword("securePassword");
//        nonExistingUser.setFirstName("John");
//        nonExistingUser.setLastName("Doe");
//        nonExistingUser.setEnabled(true);
//
//        // When
//        // baihgui bga hereglegchin ustgahgej oroldj bn
//        assertThatThrownBy(() -> userRepository.delete(nonExistingUser))
//                .isInstanceOf(EmptyResultDataAccessException.class)
//                .hasMessageContaining("No class " + User.class.getName() + " entity with id ");
//
//        // Then
//        // ene userRepository iim heregkegch bhgui bgag batlah
//        assertThat(userRepository.findById(nonExistingUser.getId())).isEmpty();
//    }




//    @Test
//    void testFindUserByEmail() {
//        // Mocking the dependencies
//        UserRepository userRepositoryMock = mock(UserRepository.class);
//        EmailValidator emailValidatorMock = mock(EmailValidator.class);
//
//        // Creating an instance of the UserService with the mocked dependencies
//        UserService userService = new UserService(userRepositoryMock, emailValidatorMock);
//
//        // Mocking data and behavior
//        String userEmail = "user@gmail.com";
//        User mockUser = new User("John Doe", userEmail);
//        when(emailValidatorMock.isValid(userEmail)).thenReturn(true);
//        when(userRepositoryMock.findUserByEmail(userEmail)).thenReturn(mockUser);
//
//        // Performing the actual test
//        User foundUser = userService.findUserByEmail(userEmail);
//
//        // Verifying that the method was called with the correct arguments
//        verify(emailValidatorMock).isValid(userEmail);
//        verify(userRepositoryMock).findUserByEmail(userEmail);
//
//        // Asserting that the result is as expected
//        assertEquals(mockUser, foundUser);
//    }
}









