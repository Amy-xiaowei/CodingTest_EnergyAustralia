package com.energyfestival.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.energyfestival.controller.MainUtil;
import com.energyfestival.data.DisplayBandVO;
import com.energyfestival.data.DisplayVO;

public class MainTest {
	MainUtil mu = new MainUtil();
	public String responseBody = "";
	List<DisplayVO> resData = new ArrayList<DisplayVO>();
	public HashMap<String, List<String>> testMap = new HashMap<>();
	public HashMap<String, List<String>> sortedValueMap= new HashMap<>();
	public HashMap<String, List<String>> sortedKeyMap= new HashMap<>();
	@Before
	public void makeTestData(){
		
			 responseBody = "[\n"
					  +"{\n"
					    +"\"name\": \"Trainerella\",\n"
					    +"\"bands\": [\n"
					      +"{\n"
					      +"  \"name\": \"Wild Antelope\",\n"
						  +"\"recordLabel\": \"Still Bottom Records\"\n"
					      +"},\n"
					      +"{\n"
					       +" \"name\": \"Manish Ditch\",\n"
					       +" \"recordLabel\": \"ACR\"\n"
					      +"}\n"
					  +"]},\n"
					    +"{\n"
					    +"\"name\": \"LOL-palooza\",\n"
					    +"\"bands\": [\n"
					     +" {\n"
					     +"  \"name\": \"YOUKRANE\",\n"	
					     +"   \"recordLabel\": \"ACR\"\n"
					      +"}\n"
					    +"]\n"
					  +"}\n"
					+"]\n";
			System.out.println("------responseBody--"+responseBody);
			 
	}
	@Before
	public void makeResData(){
		List<String> festList1 = new ArrayList<>();
		festList1.add("Trainerella");
		DisplayBandVO dbvo1 = new DisplayBandVO();
		dbvo1.setBandName("Wild Antelope");
		dbvo1.setFestival(festList1);
		
		List<String> festList2 = new ArrayList<>();
		festList2.add("Trainerella");
		DisplayBandVO dbvo2 = new DisplayBandVO();
		dbvo2.setBandName("Manish Ditch");
		dbvo2.setFestival(festList2);
		
		List<String> festList3 = new ArrayList<>();
		festList3.add("LOL-palooza");
		DisplayBandVO dbvo3 = new DisplayBandVO();
		dbvo3.setBandName("YOUKRANE");
		dbvo3.setFestival(festList3);

		List<DisplayBandVO> dbvoList1 = new ArrayList<DisplayBandVO>();
		dbvoList1.add(dbvo2);
		dbvoList1.add(dbvo3);
		List<DisplayBandVO> dbvoList2 = new ArrayList<DisplayBandVO>();
		dbvoList2.add(dbvo1);
		
		DisplayVO dvo1 = new DisplayVO();
		dvo1.setRecordLabel("ACR");
		dvo1.setBandList(dbvoList1);
		
		DisplayVO dvo2 = new DisplayVO();
		dvo2.setRecordLabel("Still Bottom Records");
		dvo2.setBandList(dbvoList2);	
		
		
		resData.add(dvo1);
		resData.add(dvo2);
	}
	@Before
	public void makeMap(){
		String[] strArr1 = {"abc","zdf","dbz","bya","baz"};
		String[] strArr2 = {"6ss","6rr","7rd","bya","2ry"};
		String[] strArr3 = {"Aes","6rr","brr","dop","Drt"};
		List<String> list1 = Arrays.asList(strArr1);
		List<String> list2 = Arrays.asList(strArr2);
		List<String> list3 = Arrays.asList(strArr3);
		testMap.put("de", list1);
		testMap.put("bf", list2);
		testMap.put("bz", list3);
	}
	@Before
	public void sortValueMap(){
		String[] strArr1 = {"abc","zdf","dbz","bya","baz"};
		String[] strArr2 = {"6ss","6rr","7rd","bya","2ry"};
		String[] strArr3 = {"Aes","6rr","brr","dop","Drt"};
		List<String> list1 = Arrays.asList(strArr1);
		Collections.sort(list1);
		System.out.println("list1--"+list1);
		List<String> list2 = Arrays.asList(strArr2);
		Collections.sort(list2);
		System.out.println("list2--"+list2);
		List<String> list3 = Arrays.asList(strArr3);
		Collections.sort(list3);
		System.out.println("list3--"+list3);
		sortedValueMap.put("de", list1);
		sortedValueMap.put("bf", list2);
		sortedValueMap.put("bz", list3);
	
	}
	@Before
	public void sortKeyMap(){
		String[] strArr1 = {"abc","zdf","dbz","bya","baz"};
		String[] strArr2 = {"6ss","6rr","7rd","bya","2ry"};
		String[] strArr3 = {"Aes","6rr","brr","dop","Drt"};
		List<String> list1 = Arrays.asList(strArr1);
		System.out.println("list1--"+list1);
		List<String> list2 = Arrays.asList(strArr2);
		System.out.println("list2--"+list2);
		List<String> list3 = Arrays.asList(strArr3);
		System.out.println("list3--"+list3);
		
		sortedKeyMap.put("bf", list2);
		sortedKeyMap.put("bz", list3);
		sortedKeyMap.put("de", list1);
		
	}

	
	@Test
	public void testSortValueListMap(){

		HashMap<String, List<String>> resMap = MainUtil.sortValueList(testMap);
		assertNotNull(resMap);
		assertEquals(sortedValueMap,resMap);
	}
	@Test
	public void testSortMapByKey(){

		HashMap<String, List<String>> resMap = MainUtil.sortMapByKey(testMap);
		assertNotNull(sortedKeyMap);
		assertEquals(sortedKeyMap,resMap);
	}
	@Test
	public void testProcessData(){
		
		List<DisplayVO> displayList = MainUtil.processData(responseBody);
		assertNotNull(responseBody);
		assertEquals(resData.size(),displayList.size());
		for(int i=0; i< resData.size();i++){
			
			assertEquals(resData.get(i).getRecordLabel(),displayList.get(i).getRecordLabel());
			for(int j=0; j<resData.get(i).getBandList().size();j++){
				assertEquals(resData.get(i).getBandList().get(j).getBandName(),displayList.get(i).getBandList().get(j).getBandName());
				System.out.println("--resData.get(i).getBandList().get(j).getBandName(),displayList.get(i)--");
				System.out.println(resData.get(i).getBandList().get(j).getBandName());
				assertEquals(resData.get(i).getBandList().get(j).getFestival(),displayList.get(i).getBandList().get(j).getFestival());
			}
		}
	}

}
