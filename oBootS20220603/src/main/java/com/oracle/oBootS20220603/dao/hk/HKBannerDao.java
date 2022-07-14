package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Banner;

public interface HKBannerDao {

	List<Banner> selectAll();

	int deleteBanner(int evt_no);

	int updateBannerImg(Banner p_banner);

	int updateBannerEvtno(Banner p_banner);

	int updateBanner(Banner p_banner);

	int insertBanner(Banner p_banner);

}
