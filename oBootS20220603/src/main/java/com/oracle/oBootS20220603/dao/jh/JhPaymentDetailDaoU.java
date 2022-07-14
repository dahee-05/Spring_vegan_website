package com.oracle.oBootS20220603.dao.jh;

import java.util.List;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;

public interface JhPaymentDetailDaoU {

	int memCount(String id);

	List<Payment> orderList(String id);

	String findName(String id);

	void chSelCanU(Payment payment);

	void chSelFixU(Payment payment);

	void chDelFixU(Payment payment);

	void chDelRefInfoU(Payment payment);

	void subSts(Member member);

	List<Payment> myOrderDetailU(int payno);

	Payment detailListC(int payno);

	CouponMaster dcAmount(int payno);

	int selCount(String id);

	int canCount(String id);

	int refingCount(String id);

	int refCount(String id);

	int delCount(String id);

	int selFixCount(String id);

	int coupCount(String id);

	int mileTot(String id);

	int loginIdCheck(Member member);

	int findPwIdCheck(String id);

	String findEmail(String id);

	int chgPwTemp(Member member);

	int joinSuc(Member member);

	Member finduserinfo(String id);

	int myinfoupdate(Member member);

}
