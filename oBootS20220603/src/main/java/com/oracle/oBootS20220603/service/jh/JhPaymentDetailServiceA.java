package com.oracle.oBootS20220603.service.jh;

import java.util.List;

import com.oracle.oBootS20220603.model.Payment;

public interface JhPaymentDetailServiceA {

	// 관리자 메소드 시작 부분 
	List<Payment> listPayed(Payment payment);

	int totalPayed();

	List<Payment> listPayedKey(Payment payment);

	int totalPayedKey(Payment payment);

	int delSeq(int payno);

	void updateDel(Payment payment);

	int totalref();

	List<Payment> listRef(Payment payment);

	int totalRefKey(Payment payment);

	List<Payment> listRefKey(Payment payment);

	void refStsChg(Payment payment1);

	int totalSel();

	List<Payment> listSel(Payment payment);

	int totalSelKey(Payment payment);

	List<Payment> listSelKey(Payment payment);

	int totalCan();

	List<Payment> listCan(Payment payment);

	int totalCanKey(Payment payment);

	List<Payment> listCanKey(Payment payment);
	// 관리자 interface 추가 끝

	int selMoney();

	int selCount();

	int canCount();

	int refCount();

	int subsMemTot();

	int reviewTot();


}
