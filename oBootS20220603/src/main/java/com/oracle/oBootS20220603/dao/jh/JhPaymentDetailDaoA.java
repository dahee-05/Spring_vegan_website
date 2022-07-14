package com.oracle.oBootS20220603.dao.jh;

import java.util.List;

import com.oracle.oBootS20220603.model.Payment;

public interface JhPaymentDetailDaoA {

	// 관리자 메소드 시작 부분 
	int totalPayed();

	List<Payment> listPayed(Payment payment);

	List<Payment> listPayedKey(Payment payment);

	int totalPayedKey(Payment payment);

	int delSeq(int payno);

	void updateDel(Payment payment);

	int totalref();

	List<Payment> listRef(Payment payment);

	int totalRefKey(Payment payment);

	List<Payment> listRefKey(Payment payment);

	void refStsChg(Payment payment1);

	int totSel();

	List<Payment> listSel(Payment payment);

	int totSelKey(Payment payment);

	List<Payment> listSelKey(Payment payment);

	int totalCan();

	List<Payment> listCan(Payment payment);

	int totCanKey(Payment payment);

	List<Payment> listCanKey(Payment payment);
	// 관리자 interface 추가 끝

	int selMoney();

	int selCount();

	int canCount();

	int refCount();

	int subsMemTot();

	int reviewTot();

}
