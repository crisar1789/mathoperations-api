package com.appgate.mathoperations.api.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class Utility {

	public static ApiInfo apiInfo(String apiName) {
		Contact contact  = new Contact(ConstantsUtil.COMPANY_NAME, 
				ConstantsUtil.COMPANY_WEB_SITE, 
				ConstantsUtil.COMPANY_EMAIL);
		
		return new ApiInfoBuilder().title(ConstantsUtil.COMPANY_NAME)
				.description(apiName + " Api Rest")
				.contact(contact)
				.build();
	}
	
	public static String dateToString(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);  
        String strDate = dateFormat.format(date);  
        return strDate; 
	}
}
