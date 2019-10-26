package com.energyfestival.data;

import java.util.List;

public class DisplayVO {

	private String recordLabel;
	private List<DisplayBandVO> bandList;

	public String getRecordLabel() {
		return recordLabel;
	}

	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}

	public List<DisplayBandVO> getBandList() {
		return bandList;
	}

	public void setBandList(List<DisplayBandVO> bandList) {
		this.bandList = bandList;
	}
}
