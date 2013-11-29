package es.ual.PR.ejemploRest.ws;
/*
 * Clase que implementa un servicio web usando REST
 * 
 * Autor: Victor Suarez garcia
 * Fecha: 07/11/2012
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa un servicio web de suma de 2 numeros.
 * @author victor suarez garcia
 * @version 0.0. version inicial
 */
//Para acceder a este servicio hay que pasar por la ruta /suma
@Path("/suma")
public class Suma {
	/**
	 * Realiza la suma de 2 numeros
	 * @param a sumando 1
	 * @param b sumando 2
	 * @return suma de los 2 numeros
	 */
	//Para pasarle los parametros se hace a traves de la direccion web
	@Path("/{a}/{b}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Integer sumar(@PathParam("a")Integer a,@PathParam("b")Integer b){
		System.out.println("Recibida peticion REST");
		Integer r = a+b;
		return r;
	}
}
