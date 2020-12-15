package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MailService {


	private ArrayList<Email> emailList;

	public MailService() {
		this.emailList = new ArrayList<Email>();
	}

	public String reset() {
		return "OK";
	}

	public Email add(Email newEmail) {
		emailList.add(newEmail);
		return newEmail;
	}

	public ArrayList<Email> findByEmail(Email Obj) {
		ArrayList<Email> secondList = new ArrayList<Email>();

		for( Email a : emailList) {
			if (a.getEmail().equals(Obj.getEmail())) {
				secondList.add(a);
			}
		}
		return secondList;
	}

	public ArrayList<Email> findBySubj(Email Obj) {
		ArrayList<Email> secondList = new ArrayList<Email>();

		for( Email a : emailList) {
			if (a.getSubj().equals(Obj.getSubj())) {
				secondList.add(a);
			}
		}
		return secondList;
	}

	public String findById(Email Obj) {

		for( Email a : emailList) {
			if (a.getId().equals(Obj.getId())) {
				return "FOUND";
			}
		}
		return "NOT FOUND";
	}

	public String deleteById(Email Obj) {
	int i =0;
		for( Email a : emailList) {
					if (a.getId().equals(Obj.getId())) {
						break;
			}
			i++;
		}
		if(i<emailList.size()) {
			emailList.remove(i);
			return "DELETED";
		}
		else{
			return "Email is not exist";
		}
	}

	public ArrayList<Email> getAll() {
		return emailList;
	}

	public String deleteAll() {
		emailList.clear();
		return "OK";
	}

	public String test() {
		return java.util.UUID.randomUUID().toString();
	}
}

