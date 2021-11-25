// let base_url = window.location.origin;
// console.log(window.location.origin);
console.log("funciono");

// document.addEventListener("DOMContentLoaded", () => {

cargarHTMLen(document.querySelector("#contenido"));
function cargarHTMLen(div) {
   	// fetch(`${base_url} + "/productos"`)
   	fetch("http://localhost:8081/productos")
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
        item.innerHTML = "cantidad: " + element.cantidad + ", nombre: " + element.nombre + ", monto" + element.monto;
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
    fetch("http://localhost:8081/productos/", {
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

    let inMonto = document.createElement("input");
    let inCantidad = document.createElement("input");
    let inNombre =  document.createElement("input");
    let labMonto = document.createElement("label");
    let labCantidad = document.createElement("label");
    let labNombre = document.createElement("label");
    
    inMonto.setAttribute("type", "number");
    labMonto.setAttribute("for", "edit-monto");
    inMonto.setAttribute("id", "edit-monto");
    inMonto.setAttribute("name", "edit-monto");
    labMonto.innerHTML = "Monto";
    inCantidad.setAttribute("type", "number");
    labCantidad.setAttribute("for", "edit-cantidad");
    inCantidad.setAttribute("id", "edit-cantidad");
    inCantidad.setAttribute("name", "edit-cantidad");
    labCantidad.innerHTML = "Cantidad";
    labNombre.innerHTML = "Nombre";
    labNombre.setAttribute("for", "edit-nombre");
    inNombre.setAttribute("id", "edit-nombre");
    inNombre.setAttribute("name", "edit-nombre");

    let btnEnviar = document.createElement("button");
    btnEnviar.innerHTML = "Guardar Cambios";
    btnEnviar.addEventListener("click", function() {
        let datoNvoJSON = {};
        if (document.querySelector("#edit-nombre").value != ""){
            datoNvoJSON = {
                "nombre" : document.querySelector("#edit-nombre").value,
                "cantidad" : document.querySelector("#edit-cantidad").value,
                "monto" : document.querySelector("#edit-monto").value
            };

        } else {
            datoNvoJSON = {
                "cantidad" : document.querySelector("#edit-cantidad").value,
                "monto" : document.querySelector("#edit-monto").value
            }
        }
        fetch(`http://localhost:8081/productos/${id}`, {
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
    form.appendChild(inMonto);
    form.appendChild(inCantidad);
    form.appendChild(inNombre);
    form.appendChild(labMonto);
    form.appendChild(labCantidad);
    form.appendChild(labNombre);
    form.appendChild(btnEnviar);
}

function  borrarElemento(id){
    fetch(`http://localhost:8081/productos/${id}`, {
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