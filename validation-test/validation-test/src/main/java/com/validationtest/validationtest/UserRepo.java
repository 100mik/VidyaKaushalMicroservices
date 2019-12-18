package com.validationtest.validationtest;

import java.io.Serializable;
import lombok.Data;;

@Data
public class UserRepo implements Serializable{
	String name;
	String email;
	String phone;
	String test;
}
