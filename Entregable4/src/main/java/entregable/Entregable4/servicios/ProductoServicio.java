package entregable.Entregable4.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entregable.Entregable4.entidades.Producto;
import entregable.Entregable4.repositorios.RepositorioProducto;

@Service
public class ProductoServicio {

	@Autowired
	private RepositorioProducto producto;
	
	public List<Producto> getProductos() {
		return this.producto.findAll();
	}

	@Transactional
	public boolean addProducto(Producto c) {
		this.producto.save(c);
		return true;
	}

	@Transactional
	public Optional<Producto> getProducto(int idProducto) {
		return this.producto.findById(idProducto);
	}

	@Transactional
	public boolean putProducto(Producto producto2) {
		this.producto.flush();
		return true;
	}

	@Transactional
	public void deleteProducto(int id) {
		this.producto.deleteById(id);
	}
	
	@Transactional
	public boolean actualizaStockIngreso(Producto p,int cantidad) {
//		if (p.getCantidad() >= cantidad) {
			p.setCantidad(p.getCantidad() + cantidad);
			this.producto.flush();
		return true;
//		} else {
//			return false;
//		}
	}
	
	@Transactional
	public boolean actualizaStockEgreso(Producto p,int cantidad) {
		if (p.getCantidad() >= cantidad) {
			p.setCantidad(p.getCantidad() - cantidad);
			this.producto.flush();
		return true;
		} else {
			return false;
		}
	}

}
