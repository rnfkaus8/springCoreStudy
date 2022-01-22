package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
	
	@Test
	@DisplayName("Ÿ������ ��ȸ �� ���� Ÿ���� 2�� �̻� ������, �ߺ� ������ �߻��Ѵ�.")
	void findBeanByTypeDuplicate() {
//		ac.getBean(MemberRepository.class);
		assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(MemberRepository.class));
	}
	
	@Test
	@DisplayName("Ÿ������ ��ȸ �� ���� Ÿ���� 2�� �̻� ������, �� �̸��� �����ϸ� �ȴ�")
	void findBeanByName() {
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}
	
	@Test
	@DisplayName("Ư�� Ÿ���� ��� ��ȸ�ϱ�")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + ", value = " + beansOfType.get(key));
		}
		System.err.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);
	}
	
	@Configuration
	static class SameBeanConfig {
		
		@Bean
		public MemberRepository memberRepository() {
			return new MemoryMemberRepository();
		}
		
		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
		
	}
}
