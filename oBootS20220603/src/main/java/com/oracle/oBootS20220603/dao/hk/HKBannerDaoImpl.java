package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Banner;

@Repository
public class HKBannerDaoImpl implements HKBannerDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Banner> selectAll() {
		System.out.println("HKBannerDaoImpl selectAll Start...");
		List<Banner> ban_list = null;
		
		try {
			ban_list = session.selectList("HKBannerSelectAll");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ban_list;
	}

	@Override
	public int deleteBanner(int evt_no) {
		System.out.println("HKBannerDaoImpl deleteBanner Start...");
		int result = 0;
		try {
			result = session.delete("HKDeleteBanner", evt_no);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateBannerImg(Banner p_banner) {
		System.out.println("HKBannerDaoImpl updateBannerImg Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateBannerImg", p_banner);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateBannerEvtno(Banner p_banner) {
		System.out.println("HKBannerDaoImpl updateBannerEvtno Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateBannerEvtno", p_banner);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateBanner(Banner p_banner) {
		System.out.println("HKBannerDaoImpl updateBanner Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateBanner", p_banner);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int insertBanner(Banner p_banner) {
		System.out.println("HKBannerDaoImpl insertBanner Start...");
		int result = 0;
		
		try {
			result = session.insert("HKInsertBanner", p_banner);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

}
