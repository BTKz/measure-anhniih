package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private UserRepository repo;

    //ene doorhig ustgaj bolohgui test ajillahgui bolohoor nemsn  //this user hesg bolon deerh private finale userrepo holbootoi addnew user
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }


    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Ийм ID-тай хэрэглэгч олдсонгүй " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Ийм ID-тай хэрэглэгч олдсонгүй " + id);
        }
        repo.deleteById(id);
    }

    //ene doorhig ustgaj bolohgui test ashiglah gej nemsn
    public User getUserById(long l) {
        return null;
    }

    //userService test hiihgeed nemev
    public void updateUserPassword(Integer userId, String hello2000) {
    }

    //userService test hiihgeed bichiw
//    public void editUserDetails(Integer userId, String mail, String newFirstName, String newLastName) {
//    }

    //user service test addnew user hesg nemsn
    public User addNewUser(User user) {
        // Your implementation here
        return userRepository.save(user);
    }

    //user service test findid hesg
//    public User getUserById(int userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        return userOptional.orElse(null);
//    }
    public User getUserById(int userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    public void editUserDetails(Integer userId, String mail, String newFirstName, String newLastName) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setEmail(mail);
            existingUser.setFirstName(newFirstName);
            existingUser.setLastName(newLastName);
            userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }



//    public List<User> getAllUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//    public Iterable<User> getAllUsers() {
//
//        return userRepository.findAll();
//    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

//    public void updateUserPassword(Integer userId, String newPassword) {
//        // Implementation for updating user password
//    }


//    public void delete(Integer userId) {
//        userRepository.deleteById(userId);
//    }

}