import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeClientes extends Thread {
	
	private Socket cliente;
	private String nomeCliente;
	
	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		start();
	}			
	
	@Override
	public void run() {
		 try {
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(),true);
			escritor.println("Nome do usuario: ");
			String mensagem = leitor.readLine();
			this.nomeCliente = mensagem;
			
			while(true) {
				mensagem = leitor.readLine();
				escritor.println(this.nomeCliente+" disse: "+mensagem);
			}
			
		 } catch (IOException e) {
			System.out.println("Cliente finalizou a conexao");
			e.printStackTrace();
		}
	}

}
