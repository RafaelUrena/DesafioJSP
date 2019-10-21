/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Método para comprobar si las contraseñas coinciden.
 * @return {undefined}
 */
function comprobarContrasena(){
    
    if(document.getElementById("clave").value !== document.getElementById("rclave").value){
       var boton = document.getElementById("acept");
       var rpass = document.getElementById("rclave");
       
       boton.disabled = true;
       rpass.style.backgroundColor="#FF7A78";
       
       
        alert("Las contraseñas no coinciden");
    }else {
        var boton = document.getElementById("acept");
        var rpass = document.getElementById("rclave");
       
       boton.disabled = false;
       rpass.style.backgroundColor="white";
       
    }
}
