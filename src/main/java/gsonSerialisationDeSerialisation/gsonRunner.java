package gsonSerialisationDeSerialisation;

import com.google.gson.Gson;

public class gsonRunner {

	public static void main(String[] args) {
		Person p = new Person("Rupesh", 30);
		Gson gson = new Gson();
		String person1Data = gson.toJson(p);// serialisation
		System.out.println(person1Data);
		String jsonData = "{\"name\":\"Uday\",\"age\":20}";
		Person p2 =gson.fromJson(jsonData, Person.class);//deserialisation
//		System.out.println(p2);
//		System.out.println(p);
		String input = "RupeshYadav";
		char inputArray[]=input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<inputArray.length;i++) {
			if(Character.isLowerCase(inputArray[i])) {
				sb.append(Character.toUpperCase(inputArray[i]));
				
			}else if(Character.isUpperCase(inputArray[i])) {
				sb.append(Character.toLowerCase(inputArray[i]));
				
			}else {
				sb.append(inputArray[i]);
			}
		}
		System.out.println(sb);

	}

}
