package com.example.demo.user.service;


import com.example.demo.config.MyUserDetails;
import com.example.demo.item.domain.Item;
import com.example.demo.item.mapper.ItemMapper;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserInfoDto;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfoDto getUserInfo(String username) throws Exception {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(Exception::new));
    }

    @Transactional
    public void deleteUserAccount(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    @Transactional
    public void createUser(UserInfoDto userInfoDto) {
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        User user = userMapper.toModel(userInfoDto);
        user.setRoles("ROLE_USER");
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(String username, Item item) throws Exception {
        User userFromDB = userRepository.findByUsername(username).orElseThrow(Exception::new);
        userFromDB.getItems().add(item);
        userRepository.save(userFromDB);
    }

    @Transactional
    public UserInfoDto updateUser(String username, UserInfoDto userInfoDto) throws Exception {
        User userFromDB = userRepository.findByUsername(username).orElseThrow(Exception::new);
        userFromDB.setUsername(userInfoDto.getUsername());
        userFromDB.setEmail(userInfoDto.getEmail());
        userFromDB.setPhoneNumber(userInfoDto.getPhoneNumber());
        userRepository.save(userFromDB);
        return userInfoDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
