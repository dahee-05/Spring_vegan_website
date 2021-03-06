package com.oracle.oBootS20220603.controller.hk;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootS20220603.model.Banner;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.service.hk.HKBannerService;
import com.oracle.oBootS20220603.service.hk.HKEventService;

@Controller
public class HKBannerControllerA {

	private static final Logger logger = LoggerFactory.getLogger(HKPaymentController.class);
	
	@Autowired
	private HKBannerService bs;
	
	@Autowired
	private HKEventService es;
	
	@GetMapping("admin/banner")
	public String bannerManage(Model model, HttpServletRequest request) {
		
		System.out.println("HKBannerControllerA bannerManage Start...");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			List<Banner> banner_list = bs.selectAll();
			List<Event> event_list = es.selectActiveEvt();
			int banner_list_size = banner_list.size();
			System.out.println("banner_list_size->"+banner_list_size);
			
			
			model.addAttribute("banner_list", banner_list);
			model.addAttribute("event_list", event_list);
			model.addAttribute("banner_list_size",banner_list_size);
			model.addAttribute("menu_num", 11);
			
			return "hkBannerManageA";
		}
	}
	
	
	
	
	@PostMapping("/admin/banner/update")
	public String bannerUpdate(HttpServletRequest request, int[] evt_no, MultipartFile[] new_banner_img, Model model) {
		
		System.out.println("HKBannerControllerA bannerUpdate Start...");
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/banner");
		
		List<Banner> banner_list = bs.selectAll();
		int banner_list_size = banner_list.size();
		System.out.println("banner_list_size->"+banner_list_size);
		
		
		for(int i=0;i<3;i++) {
			System.out.println("evt_no->"+evt_no[i]);
			if(!new_banner_img[i].isEmpty()) {
				System.out.println("????????????");
				System.out.println("new_banner_img->"+new_banner_img[i].getOriginalFilename());
			}
		}
		System.out.println("OK");
		
		for(int i=0;i<3;i++) {
			
			if(banner_list_size >= i+1) {
				
				// ????????? ??????
				if(evt_no[i] == 0) {
					try {
						Banner banner = banner_list.get(i);
						int delete_result = bs.deleteBanner(banner_list.get(i).getEvt_no());
						
						String deleteFile1 = uploadPath + "/"+banner.getBanner_img();
						int delFileResult = upFileDelete(deleteFile1);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				// ???????????? ????????? ?????? but ????????? ??????	
				} else if(evt_no[i] == banner_list.get(i).getEvt_no()) {
					if(!new_banner_img[i].isEmpty()) {
						
						try {
							Banner p_banner = banner_list.get(i);
							logger.info("originalName:"+new_banner_img[i].getOriginalFilename());
							logger.info("size: "+new_banner_img[i].getSize());
							logger.info("contentType: "+new_banner_img[i].getContentType());
							logger.info("uploadPath: "+uploadPath);
							
							String savedName1 = uploadFile(new_banner_img[i].getOriginalFilename(), new_banner_img[i].getBytes(), uploadPath);
							logger.info("savedName1:"+ savedName1);
							p_banner.setNew_banner_img(savedName1);
							
							String deleteFile1 = uploadPath + "/"+p_banner.getBanner_img();
							int delFileResult = upFileDelete(deleteFile1);
							
							int update_img_result = bs.updateBannerImg(p_banner);
							if(update_img_result > 0)	System.out.println("?????? ?????????1 ?????? ??????");
							else						System.out.println("?????? ?????????1 ?????? ??????");
							
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				// ????????? ???????????? ??????	
				} else {
					Banner p_banner = banner_list.get(i);
					p_banner.setNew_evt_no(evt_no[i]);
					
					// ??????????????? ???????????? ???????????? ?????? ??????
					if(new_banner_img[i].isEmpty()) {
						int update_evtNo_result = bs.updateBannerEvtno(p_banner);
					} else {
						try {
							logger.info("originalName:"+new_banner_img[i].getOriginalFilename());
							logger.info("size: "+new_banner_img[i].getSize());
							logger.info("contentType: "+new_banner_img[i].getContentType());
							logger.info("uploadPath: "+uploadPath);
							
							String savedName1 = uploadFile(new_banner_img[i].getOriginalFilename(), new_banner_img[i].getBytes(), uploadPath);
							logger.info("savedName1:"+ savedName1);
							p_banner.setNew_banner_img(savedName1);
							
							String deleteFile1 = uploadPath + "/"+p_banner.getBanner_img();
							int delFileResult = upFileDelete(deleteFile1);
							
							int update_result = bs.updateBanner(p_banner);
							if(update_result > 0)	System.out.println("???????????? ??????");
							else					System.out.println("?????? ?????? ??????");
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				} 
				
			// ?????? ????????? 3??? ???????????? ????????? ??????????????? ????????? ?????? ??????	
			} else {
				// ??????
				if(evt_no[i] != 0) {
					try {
						Banner p_banner = new Banner();
						p_banner.setNew_evt_no(evt_no[i]);
						
						logger.info("originalName:"+new_banner_img[i].getOriginalFilename());
						logger.info("size: "+new_banner_img[i].getSize());
						logger.info("contentType: "+new_banner_img[i].getContentType());
						logger.info("uploadPath: "+uploadPath);
						
						String savedName1 = uploadFile(new_banner_img[i].getOriginalFilename(), new_banner_img[i].getBytes(), uploadPath);
						logger.info("savedName1:"+ savedName1);
						p_banner.setNew_banner_img(savedName1);
						
						
						int insert_result = bs.insertBanner(p_banner);
						if(insert_result > 0)	System.out.println("?????? ?????? ??????");
						else					System.out.println("?????? ?????? ??????");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
		}
		model.addAttribute("menu_num", 11);
		
		
		return "redirect:/admin/banner";
	}
	
	
	
	
	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {
		// universally unique identifier(UUID) ??????????????? ????????? ?????????(?????? ??? ??? ?????? ?????????)
		UUID uid = UUID.randomUUID();
		// requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath->"+uploadPath);
		
		//Directory ??????
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("???????????? ?????? ?????? : "+uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		logger.info("savedName: "+savedName);
		File target = new File(uploadPath, savedName);
//		File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target);	// ?????? ???????????? ??????		// org.springframework.util.FileCopyUtils
		
		//Service -> Dao
		
		return savedName;
		
	}
	

	
	private int upFileDelete(String deleteFileName) throws Exception {
		int result = 0;
		logger.info("upFileDelete result->"+deleteFileName);
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("?????? ?????? ??????");
				result = 1;
			}
			else {
				System.out.println("?????? ?????? ??????");
				result = 0;
			}
		}
		else {
			System.out.println("????????? ???????????? ????????????.");
			result = -1;
		}
		
		return result;
	}
	
	
	
}
