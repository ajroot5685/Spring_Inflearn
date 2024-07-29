package com.example.demo.user.service;

import com.example.demo.mock.FakeMailSernder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CertificationServiceTest {

    @Test
    public void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트한다() {
        // given
        FakeMailSernder fakeMailSernder = new FakeMailSernder();
        CertificationService certificationService = new CertificationService(fakeMailSernder);

        // when
        certificationService.sendCertificationEmail("kok202@naver.com", 1, "aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa");

        // then
        assertThat(fakeMailSernder.email).isEqualTo("kok202@naver.com");
        assertThat(fakeMailSernder.title).isEqualTo("Please certify your email address");
        assertThat(fakeMailSernder.content).isEqualTo("Please click the following link to certify your email address: http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa");

    }
}