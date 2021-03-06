package io.github.putme2yourheart.cleanarchitecture.data.repository;

import javax.inject.Inject;

import io.github.putme2yourheart.cleanarchitecture.data.mapper.UserEntityDataMapper;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStore;
import io.github.putme2yourheart.cleanarchitecture.data.repository.datasource.UserDataStoreFactory;
import io.github.putme2yourheart.cleanarchitecture.domain.User;
import io.github.putme2yourheart.cleanarchitecture.domain.repository.UserRepository;
import io.reactivex.Observable;

public class UserDataRepository implements UserRepository {
    private UserDataStoreFactory userDataStoreFactory;

    @Inject
    public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
    }

    @Override
    public Observable<User> user(String user) {
        UserDataStore userDataStore =  userDataStoreFactory.create(user);
        return userDataStore.userEntityDetails(user).map(new UserEntityDataMapper());
    }
}
