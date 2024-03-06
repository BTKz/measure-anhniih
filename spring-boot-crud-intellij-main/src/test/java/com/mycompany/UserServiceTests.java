package com.mycompany;

//import com.mycompany.user.User;
//import com.mycompany.user.UserNotFoundException;
//import com.mycompany.user.UserRepository;
//import com.mycompany.user.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//@SpringJUnitConfig
//public class UserServiceTests {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    public void testAddNewUser() {
//        // Given
//        User user = mock(User.class);
//        when(userRepository.save(user)).thenReturn(user);
//
//        // When
//        userService.save(user);
//
//        // Then
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testListAllUsers() {
//        // Given
//        Iterable<User> users = Collections.emptyList();
//        when(userRepository.findAll()).thenReturn(users);
//
//        // When
//        List<User> result = userService.listAll();
//
//        // Then
//        assertThat(result).isEqualTo(users);
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testUpdateUserPassword() {
//        // Given
//        Integer userId = 7;
//        User user = mock(User.class);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // When
//        userService.updateUserPassword(userId, "hello2000");
//
//        // Then
//        verify(userRepository, times(1)).findById(userId);
//        verify(user, times(1)).setPassword("hello2000");
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testGetUserById() throws UserNotFoundException {
//        // Given
//        Integer userId = 6;
//        User user = mock(User.class);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // When
//        User result = userService.get(userId);
//
//        // Then
//        assertThat(result).isEqualTo(user);
//        verify(userRepository, times(1)).findById(userId);
//    }
//
//    @Test
//    public void testDeleteUser() throws UserNotFoundException {
//        // Given
//        Integer userId = 18;
//        when(userRepository.countById(userId)).thenReturn(1L);
//
//        // When
//        userService.delete(userId);
//
//        // Then
//        verify(userRepository, times(1)).countById(userId);
//        verify(userRepository, times(1)).deleteById(userId);
//    }
//
//    @Test
//    public void testDeleteNonExistingUser() {
//        // Given
//        Integer userId = 17;
//        when(userRepository.countById(userId)).thenReturn(0L);
//
//        // When and Then
//        assertThatThrownBy(() -> userService.delete(userId))
//                .isInstanceOf(UserNotFoundException.class)
//                .hasMessageContaining("Ийм ID-тай хэрэглэгч олдсонгүй " + userId);
//    }
//
//    @Test
//    public void testEditUser() {
//        // Given
//        Integer userId = 27;
//        User user = mock(User.class);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // When
//        userService.editUserDetails(userId, "newemail@gmail.com", "NewFirstName", "NewLastName");
//
//        // Then
//        verify(userRepository, times(1)).findById(userId);
//        verify(user, times(1)).setEmail("newemail@gmail.com");
//        verify(user, times(1)).setFirstName("NewFirstName");
//        verify(user, times(1)).setLastName("NewLastName");
//        verify(userRepository, times(1)).save(user);
//    }
//
//
//
//    @Test
//    public void testDuplicateEmail() {
//        // Given
//        User user = mock(User.class);
//        when(userRepository.save(user)).thenReturn(user);
//
//        // When
//        userService.save(user);
//
//        // Attempt to save the same user again
//        when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);
//
//        // Then
//        assertThatThrownBy(() -> userService.save(user))
//                .isInstanceOf(DataIntegrityViolationException.class);
//    }
//
//    @Test
//    public void testGetNonExistingUser() {
//        // Given
//        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        // When
//        User result = userService.getUserById(999L);
//
//        // Then
//        assertThat(result).isNull();
//    }
//
//    @Test
//    public void testExistingUser() {
//        // Given
//        Integer existingUserId = 6;
//        User existingUser = mock(User.class);
//        when(existingUser.getId()).thenReturn(existingUserId);
//        when(userRepository.findById(existingUserId)).thenReturn(Optional.of(existingUser));
//
//        // When
//        User result = userService.getUserById(existingUserId);
//
//        // Then
//        assertThat(result).isEqualTo(existingUser);
//        verify(userRepository, times(1)).findById(existingUserId);
//    }
//}
//
//


//endes shine user service test class uusgej bn

import com.mycompany.user.*;
import org.assertj.core.api.Assert;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddNewUser() {
        // Mocking data
        User user = new User();
        user.setEmail("bat@gmail.com");
        user.setPassword("bat123456");
        user.setFirstName("Bat");
        user.setLastName("Dorj");

        // Mocking
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(any(User.class))).thenReturn(user);


        UserService userService = new UserService(userRepository);

        // yg test end ywgdj bn
        User savedUser = userService.addNewUser(user);

        // ene arga zuw argument duudsn esehiig shalgaj bna
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());


        Assertions.assertNotNull(savedUser);
        assertEquals(user, savedUser);


        User capturedUser = userCaptor.getValue();
        assertEquals("bat@gmail.com", capturedUser.getEmail());
        assertEquals("bat123456", capturedUser.getPassword());
        assertEquals("Bat", capturedUser.getFirstName());
        assertEquals("Dorj", capturedUser.getLastName());
    }


//    @Test
//    void testGetUserById() {
//        // Mocking data
//        int userId = 50;
//        User mockUser = new User();
//        mockUser.setFirstName("John Doe");
//        mockUser.setEmail("john.doe@example.com");
//
//        // Mocking UserRepository behavior
//        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));
//
//        // Performing the actual test
//        User result = userService.getUserById(userId);
//
//        // Verifying that the method was called with the correct argument
//        ArgumentCaptor<Integer> userIdCaptor = ArgumentCaptor.forClass(Integer.class);
//        verify(userRepository).findById(userIdCaptor.capture());
//
//        // Asserting that the method was called with the correct argument
//        assertThat(userIdCaptor.getValue()).isEqualTo(userId);
//
//        // Asserting that the result is as expected
//        assertThat(result).isEqualTo(mockUser);
//    }


    @Test
    void testSaveUser() {
        // Mocking data
        User userToSave = new User();


        // test
        userService.save(userToSave);

        verify(userRepository).save(userToSave);
    }

    @Test
    public void testDeleteUser() {
        // Mocking data
        Integer userId = 48;

        // Mocking UserRepository
        when(userRepository.countById(userId)).thenReturn(1L);
        doNothing().when(userRepository).deleteById(userId);

        // test ajiluulj bui hesg
        assertDoesNotThrow(() -> userService.delete(userId));

        // argument zuw duudsn esehiig shalgaj bn
        verify(userRepository).deleteById(userId);
    }


//    @Test
//    public void testEditUser() throws UserNotFoundException {
//        // Given
//        Integer userId = 49;
//        User user = mock(User.class);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        // When
//        userService.editUserDetails(userId, "newemail@gmail.com", "NewFirstName", "NewLastName");
//
//        // Then
//        verify(userRepository, times(1)).findById(userId);
//        verify(user, times(1)).setEmail("newemail@gmail.com");
//        verify(user, times(1)).setFirstName("NewFirstName");
//        verify(user, times(1)).setLastName("NewLastName");
//        verify(userRepository, times(1)).save(user);
//    }


//    @Test
//    public void testEditUserDetails_UserExists() {
//        // Given
//        Integer userId = 49;
//        String newMail = "newemail@gmail.com";
//        String newFirstName = "NewFirstName";
//        String newLastName = "NewLastName";
//
//        User existingUser = new User();
//        existingUser.setId(userId);
//        existingUser.setEmail("Bat@gmail.com");
//        existingUser.setFirstName("Bat");
//        existingUser.setLastName("Dorj");
//
//        // Mock UserRepository behavior
//        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        // When
//        try {
//            userService.editUserDetails(userId, newMail, newFirstName, newLastName);
//        } catch (UserNotFoundException e) {
//            // Handle the exception if needed or rethrow it
//            fail("UserNotFoundException should not be thrown when user exists");
//        }
//
//        // Then
//        verify(userRepository, times(1)).findById(userId);
//
//        // Verify that user details have been updated
//        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
//        verify(userRepository).save(userCaptor.capture());
//
//        User updatedUser = userCaptor.getValue();
//        assertThat(updatedUser.getId()).isEqualTo(userId);
//        assertThat(updatedUser.getEmail()).isEqualTo(newMail);
//        assertThat(updatedUser.getFirstName()).isEqualTo(newFirstName);
//        assertThat(updatedUser.getLastName()).isEqualTo(newLastName);
//    }

//    @Test
//    public void testEditUserDetails_UserExists() {
//        // Given
//        Integer userId = 49;
//        String newMail = "newmail@example.com";
//        String newFirstName = "NewFirstName";
//        String newLastName = "NewLastName";
//
//        User existingUser = new User();
//        existingUser.setId(userId);
//        existingUser.setEmail("Bat@gmail.com");
//        existingUser.setFirstName("Bat");
//        existingUser.setLastName("Dorj");
//
//        // Mock UserRepository behavior
//        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
//
//        // When
//        assertDoesNotThrow(() -> userService.editUserDetails(userId, newMail, newFirstName, newLastName));
//
//        // Then
//        verify(userRepository, times(1)).findById(userId);
//        verify(userRepository, times(1)).save(existingUser);
//        assertEquals(newMail, existingUser.getEmail());
//        assertEquals(newFirstName, existingUser.getFirstName());
//        assertEquals(newLastName, existingUser.getLastName());
//    }

    @Test
    void testGetUserById_UserNotFound() {
        // Mocking data
        Integer userId = 49;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());


        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));


        verify(userRepository).findById(userId);
    }

    @Test
    public void testGetAllUsers() {
        // 1. Mock userRepository
        List<User> expectedUsers = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);


        List<User> actualUsers = userService.getAllUsers();


        verify(userRepository, times(1)).findAll();


        assertEquals(expectedUsers, actualUsers);
         assertSame(expectedUsers, actualUsers);
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
    AssertionsForClassTypes.assertThat(existingUser.getId()).isEqualTo(existingUserId);
    AssertionsForClassTypes.assertThat(existingUser.getEmail()).isEqualTo(existingUserEmail);
    AssertionsForClassTypes.assertThat(existingUser.getPassword()).isEqualTo(existingUserPassword);
    AssertionsForClassTypes.assertThat(existingUser.getFirstName()).isEqualTo(existingUserFirstName);
    AssertionsForClassTypes.assertThat(existingUser.getLastName()).isEqualTo(existingUserLastName);
    AssertionsForClassTypes.assertThat(existingUser.isEnabled()).isTrue(); // logicdoo taaruulaad taaruulj bolno true or false
}

}
