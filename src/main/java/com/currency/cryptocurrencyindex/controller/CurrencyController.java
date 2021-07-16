package com.currency.cryptocurrencyindex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.currency.cryptocurrencyindex.service.CurrencyService;
import com.example.cryptocurrency.entity.BitCoinEntity;

@Controller
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;
	
	
	@RequestMapping("/")
	private String showHome() {
		return "home";
	}
	
	@RequestMapping("/online")
	private String showOnlineHome(Model theModel) {
		
		List<BitCoinEntity> list = currencyService.getOnlineList();

		BitCoinEntity currentEntityValue = list.get(list.size()-1);

		theModel.addAttribute("bitCoinList",list);
		theModel.addAttribute("currentCoin",currentEntityValue);

		return "data";

	}
	
	@RequestMapping("/file")
	private String showFileHome(Model theModel) {
		
		List<BitCoinEntity> list = currencyService.getFileList();
		BitCoinEntity currentEntityValue = list.get(list.size()-1);

		theModel.addAttribute("bitCoinList",list);
		theModel.addAttribute("currentCoin",currentEntityValue);
		
		return "data";

	}
}
