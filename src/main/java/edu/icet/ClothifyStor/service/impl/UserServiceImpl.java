package edu.icet.ClothifyStor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.ClothifyStor.entity.UserEntity;
import edu.icet.ClothifyStor.model.User;
import edu.icet.ClothifyStor.repository.UserRepository;
import edu.icet.ClothifyStor.security.JwtUtil;
import edu.icet.ClothifyStor.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final String SECRET_KEY = "cLOTHIFYcreatedBYmrdillGPfromIUHS01BatchINgalleforICET";

    @Autowired
    UserRepository repo;
    @Autowired
    ObjectMapper mapper;
    private final JwtUtil jwtUtil;

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override

    public User save(User user) {
        UserEntity existingUser = repo.findByEmail(user.getEmail());
        if (existingUser!=null) {
            throw new IllegalArgumentException("Email is already registered");
        }
        UserEntity entity = mapper.convertValue(user, UserEntity.class);
        repo.save(entity);
        return mapper.convertValue(entity, User.class);
    }

    @Override
    public List<User> read() {
        Iterable<UserEntity> userList = repo.findAll();
        List<User> userModel = new ArrayList<>();
        userList.forEach(userEntity ->
                userModel.add(mapper.convertValue(userEntity, User.class))
        );
        return userModel;
    }


    public String auth(User loginRequest) {

        UserEntity user = repo.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return generateToken(user);
        }
        return null;
    }
    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String checkToken(String token) {
        if (token == null || !token.startsWith("Bearer")) {
            return null;
        }
        String actualToken = token.substring(7);

        try {
            String email = jwtUtil.extractEmail(actualToken);

            log.info("Extracted email: {}", email);
            return email;

        } catch (Exception e) {
            log.error("Token validation failed", e);
            return null;
        }
    }
}