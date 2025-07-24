package com.smartcart.user_service.service;
import com.smartcart.user_service.entity.Role;
import com.smartcart.user_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;

import com.smartcart.user_service.dto.User;
import com.smartcart.user_service.entity.AccountStatus;
import com.smartcart.user_service.entity.UserEntity;
import com.smartcart.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl  implements  UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    @Autowired
//    private final UserRepository userRepository;
//    @Autowired
//    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public User createUser(User user) {
       if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email Already In Use");
        }
        UserEntity userEntity=modelMapper.map(user, UserEntity.class);
        userEntity.setAccountStatus(AccountStatus.ACTIVE);
        UserEntity savedUser=userRepository.save(userEntity);
        return  modelMapper.map(savedUser,User.class);


    }
    @Override
    public UserEntity updateUser(Long id, User user) {

       UserEntity newUser= userRepository.findById(id)
               .orElseThrow(()->new RuntimeException(" user not found"));
        newUser.setName(user.getName());
        newUser.setId(user.getId());
        newUser.setRole(Role.valueOf(user.getRole()));
        return  modelMapper.map(userRepository.save(newUser), UserEntity.class);
    }
    @Override
    public void softDeleteUser(Long id){

      UserEntity user= userRepository.findById(id).orElseThrow(()-> new RuntimeException("User doesnot exist"));
        user.setAccountStatus(AccountStatus.INACTIVE);
        userRepository.save(user);



    }
    @Override
    public void restoreUser(Long id){
        UserEntity user=userRepository.findById(id).orElseThrow(()->new RuntimeException("no user found"));
        user.setAccountStatus(AccountStatus.ACTIVE);
        userRepository.save(user);

    }
    @Override
    public void deleteUser(Long id){
 userRepository.deleteById(id);
    }
    @Override
    public boolean emailExists(String email){
        return   userRepository.existsByEmail(email);

    }
    @Override
    public UserEntity getUserById(Long id){
       UserEntity user= userRepository.findById(id).orElseThrow(()-> new RuntimeException("No user found"));
        return modelMapper.map(user,UserEntity.class);
    }
    @Override
    public User getUserByEmail(String email){
     UserEntity foundUser=  userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    return modelMapper.map(foundUser,User.class);
    }
    @Override
    public List<UserEntity> filterUsersByRole(String role){
       List<UserEntity> users= userRepository.findByRole(Role.valueOf(role.toUpperCase()));
       List<UserEntity> newUserList= users.stream().map(user ->modelMapper.map(user,UserEntity.class))
               .collect(Collectors.toList());
       return  newUserList;
    }
    @Override
    public List<UserEntity> searchUsers(String keyword){
       List<UserEntity> foundUsers= userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
           List<UserEntity> newUsersList= foundUsers.stream().map(user->modelMapper.map(user,UserEntity.class))
                   .collect(Collectors.toList());
           return newUsersList;

        }

    @Override
    public long countUsers(){
        return  userRepository.count();
    }
}
