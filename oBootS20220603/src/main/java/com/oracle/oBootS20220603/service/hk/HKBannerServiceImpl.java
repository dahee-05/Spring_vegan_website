package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKBannerDao;
import com.oracle.oBootS20220603.model.Banner;

@Service
public class HKBannerServiceImpl implements HKBannerService {

	@Autowired
	private HKBannerDao bd;
	
	@Override
	public List<Banner> selectAll() {
		System.out.println("HKBannerServiceImpl selectAll Start...");
		List<Banner> ban_list = bd.selectAll();
		
		return ban_list;
	}

	@Override
	public int deleteBanner(int evt_no) {
		System.out.println("HKBannerServiceImpl deleteBanner Start...");
		int result = bd.deleteBanner(evt_no);
		
		return result;
	}

	@Override
	public int updateBannerImg(Banner p_banner) {
		System.out.println("HKBannerServiceImpl updateBannerImg Start...");
		int result = bd.updateBannerImg(p_banner);
		
		return result;
	}

	@Override
	public int updateBannerEvtno(Banner p_banner) {
		System.out.println("HKBannerServiceImpl updateBannerEvtno Start...");
		int result = bd.updateBannerEvtno(p_banner);
		return result;
	}

	@Override
	public int updateBanner(Banner p_banner) {
		System.out.println("updateBannerEvtno updateBanner Start...");
		int result = bd.updateBanner(p_banner);
		
		return result;
	}

	@Override
	public int insertBanner(Banner p_banner) {
		System.out.println("updateBannerEvtno insertBanner Start...");
		int result = bd.insertBanner(p_banner);
		
		return result;
	}

}
