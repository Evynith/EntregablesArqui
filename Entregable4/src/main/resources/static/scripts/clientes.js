// let base_url = window.location.origin;
// console.log(window.location.origin);
console.log("funciono");

// document.addEventListener("DOMContentLoaded", () => {

cargarHTMLen(document.querySelector("#contenido"));
function cargarHTMLen(div) {
   	// fetch(`${base_url} + "/clientes"`)
   	fetch("http://localhost:8081/clientes")
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
        item.innerHTML = "DNI: " + element.dni + ", nombre: " + element.nombre;
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
            "dni" : document.querySelector("#nvo-dni").value,
            "nombre" : document.querySelector("#nvo-nombre").value
        };

    } else {
        datoNvoJSON = {
            "dni" : document.querySelector("#nvo-dni").value
        }
    }
    fetch("http://localhost:8081/clientes/", {
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

    let inDNI = document.createElement("input");
    let inNombre =  document.createElement("input");
    let labDNI = document.createElement("label");
    let labNombre = document.createElement("label");
    
    inDNI.setAttribute("type", "number");
    labDNI.setAttribute("for", "edit-dni");
    labDNI.innerHTML = "DNI";
    inDNI.setAttribute("id", "edit-dni");
    inDNI.setAttribute("name", "edit-dni");
    labNombre.setAttribute("for", "edit-nombre");
    labNombre.innerHTML = "Nombre";
    inNombre.setAttribute("id", "edit-nombre");
    inNombre.setAttribute("name", "edit-nombre");

    let btnEnviar = document.createElement("button");
    btnEnviar.innerHTML = "Guardar Cambios";
    btnEnviar.addEventListener("click", function() {
        let datoNvoJSON = {};
        if (document.querySelector("#edit-nombre").value != ""){
            datoNvoJSON = {
                "dni" : document.querySelector("#edit-dni").value,
                "nombre" : document.querySelector("#edit-nombre").value
            };

        } else {
            datoNvoJSON = {
                "dni" : document.querySelector("#edit-dni").value
            }
        }
        fetch(`http://localhost:8081/clientes/${id}`, {
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
    form.appendChild(inDNI);
    form.appendChild(inNombre);
    form.appendChild(labDNI);
    form.appendChild(labNombre);
    form.appendChild(btnEnviar);
}

function  borrarElemento(id){
    fetch(`http://localhost:8081/clientes/${id}`, {
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