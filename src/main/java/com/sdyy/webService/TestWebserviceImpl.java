package com.sdyy.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.spi.ObjectFactory;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdyy.base.ac.ac_operator.service.IAcOperatorService;

@WebService
@Service
public class TestWebserviceImpl implements ITestWebservice{
	@Autowired
	private IAcOperatorService acOperatorService;
	@WebMethod
	public String testOut(String str) {
		String userId = acOperatorService.get("00000000-0000-0000-0000-000000000001").get("USER_ID").toString();
		return "HELLO!"+str+" by "+userId;
	}
}
