package com.pets.ad.usecase;

import com.pets.ad.entity.UserAd;
import com.pets.ad.repository.AdRepository;
import com.pets.core.exception.InconsistentPeriodException;
import com.pets.core.exception.StartDatePassedException;
import com.pets.core.repository.DateRepository;
import com.pets.profil.entity.Address;
import com.pets.profil.entity.UserAccount;
import com.pets.profil.usecase.UserAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.pets.profil.entity.Animal.CAT;
import static java.time.Month.JUNE;
import static java.util.Calendar.JULY;
import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAdTest {

    @Mock
    UserAccountRepository userAccountRepository;
    @Mock
    AdRepository adRepository;
    @Mock
    DateRepository dateRepository;

    @InjectMocks
    CreateAd createAd;

    @Test
    void should_create_ad() throws Exception {
        // given
        var userAccount = new UserAccount(new Address());
        var startDate = LocalDate.of(2023, JULY, 2);
        var endDate = LocalDate.of(2023, JULY, 16);
        var animal = CAT;

        when(dateRepository.now()).thenReturn(LocalDate.of(2023, JUNE, 2));
        when(userAccountRepository.fetchUserAccount()).thenReturn(of(userAccount));

        // when
        createAd.execute(animal, startDate, endDate);

        // then
        var expectedUserAd = new UserAd(userAccount, animal, startDate, endDate, 14L);
        verify(adRepository).save(expectedUserAd);
    }

    @Test
    void should_not_create_ad_when_startDate_is_has_passed() {
        // given
        var startDate = LocalDate.of(2023, JULY, 2);
        var endDate = LocalDate.of(2023, JULY, 16);
        var animal = CAT;

        when(dateRepository.now()).thenReturn(LocalDate.of(2023, JULY, 3));

        // when then
        Assertions.assertThatThrownBy(() -> createAd.execute(animal, startDate, endDate))
                .isExactlyInstanceOf(StartDatePassedException.class);
    }

    @Test
    void should_not_create_ad_when_startDate_is_after_endDate() {
        // given
        var startDate = LocalDate.of(2023, JULY, 2);
        var endDate = LocalDate.of(2023, JULY, 1);
        var animal = CAT;

        // when then
        Assertions.assertThatThrownBy(() -> createAd.execute(animal, startDate, endDate))
                .isExactlyInstanceOf(InconsistentPeriodException.class);
    }
}