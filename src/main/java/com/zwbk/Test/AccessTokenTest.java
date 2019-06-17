package com.zwbk.Test;

import org.junit.Test;

import com.zwbk.WxService.WxService;

public class AccessTokenTest {
	
	@Test
	public void testgetoken() {
		System.out.println(WxService.getAccessToken());
		//System.out.println();
	}
}
