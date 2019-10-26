package com.energyfestival.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.energyfestival.data.DisplayVO;
/**
 * 
 * @author Amy Wang
 *
 */
 
@RestController
public class RestUserController  
{
	@RequestMapping(value="/festivals", produces=MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody List<DisplayVO> getFestivals() {
		List<DisplayVO> ret = null;
		ret = MainUtil.getDisplayList();
		return ret;
	}
	
}
