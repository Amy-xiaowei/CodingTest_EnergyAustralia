package com.energyfestival.data;

import java.util.List;

public class DisplayBandVO {
	private String bandName;
	private List<String> festival;

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public List<String> getFestival() {
		return festival;
	}

	public void setFestival(List<String> festival) {
		this.festival = festival;
	}
}
