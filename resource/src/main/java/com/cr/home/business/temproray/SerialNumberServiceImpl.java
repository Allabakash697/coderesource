package com.cr.home.business.temproray;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cr.home.beans.Numbers;
import com.cr.home.beans.temproray.GetSerialNumber;
import com.cr.home.beans.temproray.SerialNumber;

@Component
public class SerialNumberServiceImpl {

	public String getNext(String user){
		
		GetSerialNumber numb=new GetSerialNumber();
		String nextNumber = numb.generateSerialNumber(user);
		
		return nextNumber;
	}

	public List<Numbers> getNumbersList(SerialNumber numbers) {
		List<Numbers> list=new ArrayList<Numbers>();
		GetSerialNumber numb=new GetSerialNumber();
		int rows=numbers.getRows();
		String nextNumber=numbers.getNumber();
		String nextNumbers;
		for(int i=0;i<rows;i++){
			Numbers numbs=new Numbers();
		 nextNumbers = numb.generateSerialNumber(nextNumber);
			numbs.setNextNumber(nextNumbers);
			numbs.setRows(i+1);
			nextNumber=nextNumbers;
			list.add(numbs);
		}
		return list;
	}
}
