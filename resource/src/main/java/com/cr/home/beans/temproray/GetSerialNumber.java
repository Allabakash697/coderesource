package com.cr.home.beans.temproray;

public class GetSerialNumber {

	public String generateSerialNumber(String numb) {
		char[] defaultArray ={'0','1','2','3','4','5','6','7','8','9',
				        'a','b','c','d','e','f','g','h','i','j',
				        'k','l','m','n','o','p','q','r','s','t',
				        'u','v','w','x','y','z'};
		
		int defaultLength=defaultArray.length;
		int length=numb.length();
char[] newInput=new char[100];
char[] input={};
input=numb.toCharArray();
for(int i=0;i<length;i++){
	newInput[i]=input[i];
}
	boolean check=true;
	for(int i=length-1;i>=0;i--){
		for(int j=0;j<defaultLength;j++){
		if(defaultArray[j]>newInput[i]){
			newInput[i]=defaultArray[j];
			check=false;
		break;
		}else if(defaultLength==(j+1)){
			
			if(i==0){
				//if number has only z or zz or zzz
				newInput[i]=defaultArray[1];
				newInput[length]=defaultArray[0];
				break;
			}else{
				newInput[i]=defaultArray[0];
			}
			if(defaultArray[0]>newInput[i-1]){
				check=false;
				break;
				
			}else if(defaultArray[0]==newInput[i-1]){
				newInput[i-1]=defaultArray[1];
				check=false;
				break;
			}else{
				continue;
			}
		}
		}
		if(!check){
			break;
		}
}
	/*String str=String.valueOf(newInput);*/
	String str="";
	for (Character c : newInput){
		if(c!='\u0000'){
	    str += c.toString();
		}else{
			break;
		}
	}
	return str;
}
	
}