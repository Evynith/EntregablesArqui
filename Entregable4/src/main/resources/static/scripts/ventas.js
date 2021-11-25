// let base_url = window.location.origin;
// console.log(window.location.origin);
console.log("funciono");

// document.addEventListener("DOMContentLoaded", () => {

cargarHTMLen(document.querySelector("#contenido"));
function cargarHTMLen(div) {
   	// fetch(`${base_url} + "/ventas"`)
   	fetch("http://localhost:8081/ventas")
    .then(response => {
        console.log(response);
        if(response.ok) {
            console.log("entré");
            return response.json();
        } else {
            div.innerHTML = "No hay nada por aquí";
        }
    }).then(contenido => {
        if (contenido) {
            if (contenido.length != 0){
        let lista = document.createElement("ul");
        contenido.forEach(element => {
        let item = document.createElement("li");
        console.log(element.productos);
        item.innerHTML = "fecha: " + element.fechaEmision + ", cliente:" + element.cliente.nombre ;

        let listprod = document.createElement("ul");
        let prodadd = document.createElement("li");
            prodadd.innerHTML = '<form> <label for="nvo-idprod">Nombre</label> <input name="nvo-idprod" id="nvo-idprod"> <label for="nvo-cantidad">Cantidad</label> <input name="nvo-cantidad" id="nvo-cantidad" type="number"> <button id="btn-agregar-subelem">Agregar</button> </form>' ;
       //TODO: btn-agregar-subelem accion
            listprod.appendChild(prodadd);
        element.productos.forEach(
            e=> {
                let prod = document.createElement("li");
                prod.setAttribute("id", e.id);
                prod.innerHTML +=  e.producto.nombre + " " + e.cantidadProducto + "u." + " $" + e.producto.monto + "<div id='edit-subelem'> </div>";

                let btnBorrarSub = document.createElement("button");
                btnBorrarSub.addEventListener("click", function() {
                    let idBorrar = this.parentNode.id;
                    borrarSubelemento(idBorrar);
                });
                btnBorrarSub.innerHTML = "borrar";
                let btnEditarSub = document.createElement("button");
                btnEditarSub.addEventListener("click", function() {
                    let idEditar = this.parentNode.id;
                    editarSubElemento(idEditar);
                });
                listprod.appendChild(prod);
            });
        let btnBorrar = document.createElement("button");
        btnBorrar.addEventListener("click", function() {
            let idBorrar = this.parentNode.id;
            borrarElemento(idBorrar);
        });
        btnBorrar.innerHTML = "borrar";
        let btnEditar = document.createElement("button");
        btnEditar.addEventListener("click", function() {
            let idEditar = this.parentNode.id;
            editarElemento(idEditar);
        });
        btnEditar.innerHTML = "editar";
        item.appendChild(listprod);
        item.appendChild(btnBorrar);
        item.appendChild(btnEditar);
        item.setAttribute("id", element.id);
        lista.appendChild(item);
                    })
                    div.appendChild(lista);
			} else {
			
           div.innerHTML = "No hay nada por aquí";
}
        }
    }).catch(error =>
        div.innerHTML = error)
};

document.querySelector("#btn-agregar").addEventListener("click", agregarElemento);

function agregarElemento(){
    let datoNvoJSON = {};
    if (document.querySelector("#nvo-nombre").value != ""){
        datoNvoJSON = {
            "monto" : document.querySelector("#nvo-monto").value,
            "nombre" : document.querySelector("#nvo-nombre").value,
            "cantidad" : document.querySelector("#nvo-cantidad").value
        };
    } else {
        datoNvoJSON = {
            "monto" : document.querySelector("#nvo-monto").value,
            "cantidad" : document.querySelector("#nvo-cantidad").value
        }
    }
    fetch("http://localhost:8081/ventas/", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          body: JSON.stringify(datoNvoJSON)
    })
    .then(response => {
        if(response.ok) {
            console.log("nvo");
            window.location.reload();
        } else {
           
        }
    }).catch(error =>
        console.log(error)
        // div.innerHTML = error
        )
}

function editarElemento(id){
    console.log("entro a editar");
    let form = document.querySelector("#editar");

    let inCliente = document.createElement("input");
    let labCliente = document.createElement("label");
    
    inCliente.setAttribute("type", "number");
    labCliente.setAttribute("for", "edit-cliente");
    inCliente.setAttribute("id", "edit-cliente");
    inCliente.setAttribute("name", "edit-cliente");
    labCliente.innerHTML = "Cliente";

    let btnEnviar = document.createElement("button");
    btnEnviar.innerHTML = "Guardar Cambios";
    btnEnviar.addEventListener("click", function() {
            datoNvoJSON = {
                "idCliente" : document.querySelector("#edit-cliente").value
        }
        fetch(`http://localhost:8081/ventas/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
              },
              body: JSON.stringify(datoNvoJSON)
        })
        .then(response => {
            if(response.ok) {
                console.log("edito");
                window.location.reload();
            } else {
               
            }
        }).catch(error =>
            console.log(error)
            // div.innerHTML = error
            )
    });
    form.appendChild(inCliente);
    form.appendChild(labCliente);
    form.appendChild(btnEnviar);
}

function  borrarElemento(id){
    fetch(`http://localhost:8081/ventas/${id}`, {
        method: 'DELETE'})
    .then(response => {
        if(response.ok) {
            console.log("borro");
            // console.log( document.querySelector(`#${id}`));
            // document.querySelector(`#${id}`).remove();
            window.location.reload();
        } else {
           
        }
    }).catch(error =>
        console.log(error)
        // div.innerHTML = error
        )
}

// });