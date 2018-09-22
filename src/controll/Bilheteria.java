package controll;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bilheteria extends Thread {

	private int num_pessoa;
	private static int quant_ingresso = 100;
	private int quant_venda;

	Semaphore semaforo;

	Random r = new Random();

	public Bilheteria(int num_pessoa, Semaphore semaforo) {

		this.num_pessoa = num_pessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {

		Login();

		try {

			semaforo.acquire();
			compra();

			validacao();

		} catch (InterruptedException e) {

			e.printStackTrace();

		} finally {

			semaforo.release();
		}

	}

	public void tempo(int intervalo) {

		try {
			Thread.sleep(intervalo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean Login() {

		int intervalo = r.nextInt(1951) + 50;

		tempo(intervalo);

		if (intervalo > 1000) {
			System.out
					.println("Pessoa " + num_pessoa + " Login não Efetuado " + "Tempo de Execução " + intervalo + "ms");
			return false;

		} else {

			System.out.println("Pessoa " + num_pessoa + " Login Efetuado!!!");
			return true;
		}
	}

	public void compra() {

		int intervalo = r.nextInt(2001) + 1000;

		tempo(intervalo);

		if (intervalo > 2500) {

			System.out.println(
					"Pessoa " + num_pessoa + " Venda não Concretizada " + " Tempo de Execução: " + intervalo + "ms");

		} else {

			quant_venda = r.nextInt(4) + 1;
			comprar(quant_venda);

		}
	}

	public void comprar(int quant_venda) {

		if (quant_ingresso >= quant_venda) {

			System.out.println("Pessoa " + num_pessoa + " Comprou " + quant_venda + " Ingressos");
			quant_ingresso = quant_ingresso - quant_venda;
			System.out.println("Restam: " + quant_ingresso + " Ingressos");
		}
	}

	public void validacao() {

		if (quant_ingresso <= 0) {
			
			System.out.println("Pessoa " + num_pessoa + " Tentou Comprar...Os Ingressos estão Esgotados");
		}
	}
}
