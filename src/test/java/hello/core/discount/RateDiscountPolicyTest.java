package hello.core.discount;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicyTest {
	
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP�� 10% ������ ����Ǿ�� �Ѵ�.")
	void vip_o() {
		// given
		Member vip = new Member(1L, "memberVIP", Grade.VIP);
		// when
		int discount = discountPolicy.discount(vip, 10000);
		// then
		assertThat(discount).isEqualTo(1000);
		
	}
	
	@Test
	@DisplayName("VIP�� �ƴϸ� 10% ������ ������� �ʾƾ� �Ѵ�.")
	void vip_x() {
		// given
		Member basic = new Member(1L, "memberBASIC", Grade.BASIC);
		// when
		int discount = discountPolicy.discount(basic, 10000);
		// then
		assertThat(discount).isEqualTo(0);
	}
	
}
