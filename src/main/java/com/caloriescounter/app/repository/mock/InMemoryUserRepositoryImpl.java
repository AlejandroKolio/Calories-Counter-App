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
 * Created by Aleksandr_Shakhov on 13.05.17 13:18.
 */

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getName).thenComparing(User::getEmail);

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        Objects.requireNonNull(user);
        if(user.isNew()) {
            user.setId(counter.incrementAndGet());
        } else {
            repository.put(user.getId(), user);
        }
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
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        return getAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(USER_COMPARATOR)
                .collect(Collectors.toList());
    }
}
