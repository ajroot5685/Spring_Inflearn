package com.example.demo.user.service;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.mock.FakeMailSernder;
import com.example.demo.mock.FakeUserRepository;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserCreate;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserServiceTest {

    private UserServiceImpl userService;

    @BeforeEach
    void init() {
        FakeMailSernder fakeMailSernder = new FakeMailSernder();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        this.userService = UserServiceImpl.builder()
                .uuidHolder(new TestUuidHolder("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa"))
                .clockHolder(new TestClockHolder(1678530673958L))
                .userRepository(fakeUserRepository)
                .certificationService(new CertificationService(fakeMailSernder))
                .build();
        fakeUserRepository.save(User.builder()
                .id(1L)
                .email("kok202@naver.com")
                .address("Seoul")
                .nickname("kok202")
                .certificationCode("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build());
        fakeUserRepository.save(User.builder()
                .id(2L)
                .email("kok203@naver.com")
                .address("Seoul")
                .nickname("kok203")
                .certificationCode("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaab")
                .status(UserStatus.PENDING)
                .lastLoginAt(0L)
                .build());
    }

    @Test
    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        String email = "kok202@naver.com";

        // when
        User result = userService.getByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    void getByEmail은_PENDING_상태인_유저를_찾아올_수_없다() {
        // given
        String email = "kok203@naver.com";

        // when

        // then
        assertThatThrownBy(() -> {
            User result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void getById은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        // when
        User result = userService.getById(1);

        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    void getById은_PENDING_상태인_유저를_찾아올_수_없다() {
        // given
        // when

        // then
        assertThatThrownBy(() -> {
            User result = userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void UserCreateDto_를_이용하여_유저를_생성할_수_있다() {
        // given
        UserCreate userCreateDto = UserCreate.builder()
                .email("kok202@kakao.com")
                .address("Gyeongi")
                .nickname("kok202-k")
                .build();

        // when
        User result = userService.create(userCreateDto);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(result.getCertificationCode()).isEqualTo("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa");
    }

    @Test
    void UserUpdateDto_를_이용하여_유저를_수정할_수_있다() {
        // given
        UserUpdate userUpdateDto = UserUpdate.builder()
                .address("Incheon")
                .nickname("kok202-n")
                .build();

        // when
        userService.update(1, userUpdateDto);

        // then
        User user = userService.getById(1);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getAddress()).isEqualTo("Incheon");
        assertThat(user.getNickname()).isEqualTo("kok202-n");
    }

    @Test
    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given
        // when
        userService.login(1);

        // then
        User user = userService.getById(1);
        assertThat(user.getLastLoginAt()).isEqualTo(1678530673958L);
    }

    @Test
    void PENDING_상태의_사용자는_인증_코드로_ACTIVE_시킬_수_있다() {
        // given
        // when
        userService.verifyEmail(2, "aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaab");

        // then
        User user = userService.getById(2);
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void PENDING_상태의_사용자는_잘못된_인증_코드를_받으면_에러를_던진다() {
        // given
        // when

        // then
        assertThatThrownBy(() -> {
            userService.verifyEmail(2, "aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaac");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}