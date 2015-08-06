package com.test;

public class TestServiceImpl implements TestService{

	@Override
	public void cool(String _msg) {
		System.out.println(_msg);
	}

	public TestServiceImpl() {
	}

}
