package com.oracle.oBootS20220603.service;

import lombok.Getter;
import lombok.Setter;

// 페이징 처리 참고 : https://lionpower.tistory.com/220

@Getter
@Setter
public class Paging {

	private int currentPage = 1;	// 현재 페이지. 페이지 선택을 안하면 1페이지 부터 보여줌. 그래서 초기값 1
	private int rowPage = 10;		// 한 페이지에 보여주는 리스트 개수
	private int pageBlock = 10;		// [이전], [다음] 사이에 보여지는 페이지 넘버 노출 개수
	private int start;				// 한 페이지에서 보여주는 리스트의 시작 번호
	private int startPage;			// 시작 페이지
	private int end;				// 한 페이지에서 보여주는 리스트의 마지막 번호
	private int endPage;			// 마지막 페이지
	private int total;				// 데이터 총 개수
	private int totalPage;			// 전체 페이지 수
	
	public Paging(int total, String currentPage1) {					// currentPage1는 jsp -> Controller -> 여기까지 옴! 파라미터는 무조건 String
		this.total = total;											// 22
		if (currentPage1 != null) {									// currentPage1는 내가 선택한 페이지 번호. 현재 페이지가 null이 아니면
			this.currentPage = Integer.parseInt(currentPage1);		// 2
		}
//		<범위 설정>
//		- 하나의 페이지의 글 번호 시작 값 설정
		start = (currentPage - 1) * rowPage + 1;					// 시작 시 1	11
//		- 하나의 페이지의 글 번호 끝 값 설정
		end   = start + rowPage - 1;								// 시작 시 10	20
//		- 전체 페이지 수 설정
		totalPage = (int) Math.ceil((double)total / rowPage);		// total이 22라서 총 3페이지 필요
//						10		-		10		-9		%10		= 9
		startPage = currentPage - (currentPage - 1) % pageBlock;	// 이전 1 2 ... 10 다음 --> 다음 클릭 시 --> 이전 11 12 ... 20 다음
		endPage   = startPage + pageBlock - 1;						// 위와 동일한 계산
//			  10		 14
		if (endPage > totalPage) {									// 공갈 페이지 없애기 위해 조건문 걸어둠
			endPage = totalPage;									// 토탈페이지가 4페이지밖에 없는데 5 6 7.. 페이지 번호가 보이면 안되기 때문에 설정
		}
	}
	
}
