package com.caloriescounter.app.repository.mock;

import com.caloriescounter.app.model.User;
import com.caloriescounter.app.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Aleksandr_Shakhov on 24.05.17 21:47.
 */

@Repository
public class MockUserRepositoryImpl implements UserRepository {

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getName).thenComparing(User::getEmail);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        Objects.requireNonNull(user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(USER_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        return repository.values().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
