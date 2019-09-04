import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocket {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("127.0.0.1",9999);
			
			// Ler
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println("O servidor disse: "+mensagem);
						}
					} catch (IOException e) {
						System.out.println("Impossivel ler a mesagem do server");
						e.printStackTrace();
					}
				};
			}.start();
			
			// Escrever
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream());
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String mensagemTerminal = leitorTerminal.readLine();
				escritor.println(mensagemTerminal);
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Endreco invalido!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Servidor fora do ar");
			e.printStackTrace();
		}
	}

}
