package com.energyfestival.data;

import java.util.List;

public class FestivalBandVO {

	private String name;
	private List<BandRecordVO> bands;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BandRecordVO> getBands() {
		return bands;
	}

	public void setBands(List<BandRecordVO> bands) {
		this.bands = bands;
	}
}
