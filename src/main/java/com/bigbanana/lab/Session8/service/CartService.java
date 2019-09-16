package com.bigbanana.lab.Session8.service;

import com.bigbanana.lab.Session8.request.AddCartRequest;
import org.springframework.stereotype.Service;

@Service
public class CartService {


	public void addCart(AddCartRequest addCartRequest){
		System.out.println("cart service");

	}

}
