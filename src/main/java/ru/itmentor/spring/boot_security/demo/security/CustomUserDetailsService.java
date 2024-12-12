package ru.itmentor.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.models.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Конструктор для внедрения зависимости UserRepository
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает пользователя из базы данных по имени пользователя.
     * @param username Имя пользователя для поиска
     * @return объект UserDetails, который используется Spring Security
     * @throws UsernameNotFoundException если пользователь не найден
     */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // Если пользователь не найден, выбрасывается исключение
        User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " not found")
        );

        // Принудительная инициализация коллекции ролей пользователя
        user.getRoles().size();

        // Возвращаем объект User (реализующий UserDetails)
        return user;
    }
}