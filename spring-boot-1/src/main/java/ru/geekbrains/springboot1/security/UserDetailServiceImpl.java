package ru.geekbrains.springboot1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot1.persist.Product;
import ru.geekbrains.springboot1.persist.ProductRepository;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final ProductRepository productRepository;

    @Autowired
    public UserDetailServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String title) throws UsernameNotFoundException {
        return productRepository.findProductByTitle(title)
                .map(user -> new User(
                        user.getTitle(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Use '" + title + "' not found"));
    }
}
