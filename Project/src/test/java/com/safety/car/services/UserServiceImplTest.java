package com.safety.car.services;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.exceptions.UnauthorizedException;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDetailsRepository mockRepo;

    @InjectMocks
    private UserServiceImpl service;

    private final UserDetails user = new UserDetails(1,
            "test@test.tt",
            "firstName",
            "firstName",
            888888888,
            new Address(1, "testovi"),
            true
    );

    @Test
    void getById_ShouldThrow_WhenNoUserWithId() {
        when(mockRepo.getById(7)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> service.getById(7));
    }

    @Test
    void getById_ShouldReturn_User() {
        when(mockRepo.getById(1)).thenReturn(user);

        UserDetails test = service.getById(1);

        assertEquals(user, test);
    }

    @Test
    void create_ShouldBeRanOnce_WhenInvoked() {
        service.create(user);

        verify(mockRepo, times(1)).create(user);
    }

    @Test
    void create_ShouldThrow_IfUserEmailExists() {
        when(mockRepo.emailExists(user.getEmail())).thenReturn(true);

        assertThrows(DuplicateException.class,
                () -> service.create(user));
    }

    @Test
    void update_ShouldThrow_WhenEmailChanged() {
        when(mockRepo.getById(1)).thenReturn(user);

        UserDetails changedUser = new UserDetails(user.getId(),
                "drug@test.com",
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getEnabled());

        assertThrows(UnauthorizedException.class,
                () -> service.update(changedUser));
    }

    @Test
    void update_Should_WhenInvoked() {
        when(mockRepo.getById(1)).thenReturn(user);

        UserDetails changedUser = new UserDetails(user.getId(),
                user.getEmail(),
                "novo",
                "ime",
                user.getPhoneNumber(),
                user.getAddress(),
                user.getEnabled());

        service.update(changedUser);

        verify(mockRepo, times(1)).update(changedUser);
    }

    @Test
    void getByEmail_ShouldReturnUser_IfExists() {
        when(mockRepo.getByEmail(user.getEmail()))
                .thenReturn(user);

        UserDetails userTest = service.getByEmail(user.getEmail());

        assertEquals(user, userTest);
    }

    @Test
    void getByEmail_ShouldThrow_WhenUserDoesntExists() {
        when(mockRepo.getByEmail(user.getEmail()))
                .thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class,
                () -> service.getByEmail(user.getEmail()));
    }

    @Test
    public void getAll_ShouldReturn_List() {
        //arrange
        List<UserDetails> mockList = new ArrayList<>();
        mockList.add(user);
        Mockito.when(mockRepo.getAll()).thenReturn(mockList);
        // act
        List<UserDetails> getListFromService = mockRepo.getAll();
        //assert
        assertEquals(mockList, getListFromService);
    }
}