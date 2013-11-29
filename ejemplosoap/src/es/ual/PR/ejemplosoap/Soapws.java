package es.ual.PR.ejemplosoap;
/*
 * Clase que implementa un servicio web usando SOAP.
 *  El servicio se trata de la suma de 2 numeros
 *  
 *  Autor: Victor Suarez garcia
 *  
 *  Fecha:07/11/2012
 */
import javax.jws.WebMethod;

/**
 * Clase que implementa un servicio web SOAP
 * @author victor suarez Garcia
 * @version 0.0. version inicial
 */
public class Soapws {
	/**
	 * Metodo que suma 2 numeros
	 * @param a sumando 1
	 * @param b sumando 2
	 * @return
	 */
	@WebMethod(operationName="suma")
	public Integer suma(Integer a,Integer b){
		System.out.println("REcibida peticion SOAP");
		return a+b;
	}
}
