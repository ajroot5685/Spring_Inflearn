package com.example.pirosquare_spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

// 클래스명만 사용하면 다른 패키지와 충돌이 일어난다.
// 이 함수는 generateBeanName을 오버라이드해서 full package 명으로 Bean 이름이 결정되도록 커스터마이징했다.
public class FullBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return definition.getBeanClassName();
    }
}
