package org.oop;

import org.oop.exceptions.CustomException;
import org.oop.interfaces.User;
import org.oop.interfaces.UserRepository;
import org.oop.interfaces.UserRepositoryImpl;

public class Test {
	public static void main(String []args) throws CustomException {
		int a = 10;
		if(a<18){
			throw new CustomException("Age is less than 18");
		}else{
			System.out.println("Age is valid");
		}
		System.out.println("program ended");
	}
}
