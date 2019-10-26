package com.energyfestival.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.energyfestival.data.BandRecordVO;
import com.energyfestival.data.FestivalBandVO;
import com.energyfestival.data.DisplayBandVO;
import com.energyfestival.data.DisplayVO;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
/**
 * 
 * @author Amy Wang
 *
 */

public class MainUtil {

	private static RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
	private static final String URL = "http://eacodingtest.digital.energyaustralia.com.au/api/v1/festival";

	/**
	 * set for request timeout
	 * @return
	 */
	private static ClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(5 * 1000);
		clientHttpRequestFactory.setReadTimeout(20 * 1000);
		return clientHttpRequestFactory;
	}

	/**
	 * display data to (recordLabel,(band,festival))
	 * @return
	 */
	public static List<DisplayVO> getDisplayList() {
		List<DisplayVO> displayList = processData(retriveDataFromServer(URL));
		return displayList;
	}

	/**
	 * sort value list for Map
	 * @param toSortMap
	 * @return
	 */
	public static HashMap<String, List<String>> sortValueList(HashMap<String, List<String>> toSortMap) {
		for (Entry<String, List<String>> entry : toSortMap.entrySet()) {
			List<String> listToSort = entry.getValue();
			List<String> sortedList = listToSort.stream().sorted().collect(Collectors.toList());
			toSortMap.put(entry.getKey(), sortedList);
		}
		return toSortMap;
	}

	/**
	 * sort Map by key
	 * @param toSortMap
	 * @return
	 */
	public static HashMap<String, List<String>> sortMapByKey(HashMap<String, List<String>> toSortMap) {
		HashMap<String, List<String>> sortedMap = toSortMap.entrySet().stream().sorted(comparingByKey())
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		return sortedMap;
	}

	/**
	 * retrieve data from server using restTemplate
	 * @param url
	 * @return 
	 */
	public static String retriveDataFromServer(String url) {
		String responseStr = null;
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			if (response != null) {
				responseStr = response.getBody();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseStr;
	}


	/**
	 * process data from (festival,(band,recordLabel) to (recordLabel,(band,festival))
	 * @param responseBody
	 * @return
	 */
	public static List<DisplayVO> processData(String responseBody) {
		HashMap<String, List<String>> recordBandMap = new HashMap<String, List<String>>(); // <recordLabel,band>
		HashMap<String, List<String>> bandFestivalMap = new HashMap<String, List<String>>(); // <band,festival>

		List<FestivalBandVO> allList = new ArrayList<FestivalBandVO>();
		List<DisplayVO> displayList = new ArrayList<DisplayVO>();
		JSONArray resultAry = null;

		if (responseBody != null && responseBody.length() > 0 && responseBody.trim().startsWith("[")) {
			resultAry = new JSONArray(responseBody);
		} else {
			return displayList;
		}

		if (resultAry == null || resultAry.length() == 0) {
			return displayList;
		}
		// json data, iterator all possible lists
		ObjectMapper mapper = new ObjectMapper();
		FestivalBandVO value = new FestivalBandVO();
		for (Object data : resultAry) {
			try {
				value = mapper.readValue(data.toString(), FestivalBandVO.class);
				if (value != null && value.getBands() != null && value.getBands().size() > 0) {
					// System.out.println("festival name:" + value.getName());
					for (BandRecordVO svo : value.getBands()) {
						List<String> bandList = new ArrayList<String>();
						List<String> festivalList = new ArrayList<String>();
						if (svo.getRecordLabel() != null && svo.getRecordLabel().length() > 0) {
							// make (recordLabel, bandList) map
							if (recordBandMap.containsKey(svo.getRecordLabel())) {
								bandList = recordBandMap.get(svo.getRecordLabel());
							}
							bandList.add(svo.getName());
							recordBandMap.put(svo.getRecordLabel(), bandList);
							// make (band, festivalList) map
							if (value.getName() != null) {
								if (bandFestivalMap.containsKey(svo.getName())) {
									festivalList = bandFestivalMap.get(svo.getName());
								}
								festivalList.add(value.getName());
								bandFestivalMap.put(svo.getName(), festivalList);
							}
						}
					}
				}
				allList.add(value);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// sort value-bandList for (recordLabel, bandList) map
			recordBandMap = sortValueList(recordBandMap);

			// sort value-festivalList for (band, festivalList) map
			bandFestivalMap = sortValueList(bandFestivalMap);

		}
		// sort (recordLabel, bandList) map by key-recordLabel
		Map<String, List<String>> sortedRecordBandMap = sortMapByKey(recordBandMap);

		// sort (band, festivalList) map by key-band
		Map<String, List<String>> sortedBandFestivalMap = sortMapByKey(bandFestivalMap);

		// make lists - (recordLabel, (band, festival)) to display the data in
		// web page
		DisplayVO dvo = null;
		DisplayBandVO dbvo = null;
		List<DisplayBandVO> dbvoList = null;
		for (Entry<String, List<String>> entry : sortedRecordBandMap.entrySet()) {
			// make lists - (band, festival)
			dbvoList = new ArrayList<DisplayBandVO>();
			if (entry.getValue() != null) {
				for (String bandname : entry.getValue()) {
					dbvo = new DisplayBandVO();
					dbvo.setBandName(bandname);
					dbvo.setFestival(sortedBandFestivalMap.get(bandname));
					dbvoList.add(dbvo);
				}
			}

			dvo = new DisplayVO();
			dvo.setRecordLabel(entry.getKey());
			dvo.setBandList(dbvoList);
			displayList.add(dvo);

		}
		return displayList;
	}

}
