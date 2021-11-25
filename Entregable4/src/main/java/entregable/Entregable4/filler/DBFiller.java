package entregable.Entregable4.filler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import entregable.Entregable4.entidades.Cliente;
import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.entidades.Ticket;
import entregable.Entregable4.entidades.TicketProducto;
import entregable.Entregable4.repositorios.RepositorioCliente;
import entregable.Entregable4.repositorios.RepositorioProducto;
import entregable.Entregable4.repositorios.RepositorioTicket;

@Configuration
public class DBFiller {

	@Bean
	public CommandLineRunner initDb(RepositorioCliente cliente, RepositorioProducto producto, RepositorioTicket ticket) {
		return args -> {
			IntStream.range(0,10).forEach(i-> {
//				List<TicketProducto> tp = new ArrayList<>();
//				List<Ticket> tt = new ArrayList<>();
//				tp.add(new TicketProducto(new Producto("Producto" + i, i*10), i));
//				tt.add(new Ticket(tp));
//				Cliente c = new Cliente(i+i, "nombre" +i, tt);
//				
//				cliente.save(c);
				
				Cliente c = new Cliente(i+i, "nombre" +i);
//				cliente.save(c);
				
			});
		};
	}
}
