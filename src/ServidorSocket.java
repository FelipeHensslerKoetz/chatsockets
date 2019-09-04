import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	public static void main(String[] args) {
		ServerSocket servidor = null;
		try {
			System.out.println("Iniciando o servidor... :)");
			servidor = new ServerSocket(9999);
			System.out.println("Servidor inicializado com sucesso!");
			while (true) {
				Socket cliente = servidor.accept();
				new GerenciadorDeClientes(cliente);
			}
		} catch (IOException e) {
			try {
				if (servidor != null) {
					servidor.close();
				}
			} catch (IOException e1) {
				System.out.println("Nao foi possivel finalizar o servidor!");
				e1.printStackTrace();
			}
			System.err.println("Porta ocupada ou servidor foi finalizado!");
			e.printStackTrace();
		}
	}
}
