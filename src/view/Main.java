package view;

import java.util.concurrent.Semaphore;

import controll.Bilheteria;

public class Main {
	public static void main(String[] args) {

		Semaphore sem = new Semaphore(1);
		
		for (int i = 1; i <= 300; i++) {
			
			Thread pessoa = new Bilheteria(i , sem);
			pessoa.start();
		}

	}
}
