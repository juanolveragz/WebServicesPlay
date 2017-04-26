package controllers;

/*
Juan Carlos Olvera González 151408 juan.olveragz@udlap.mx
Gelio Castro Gracida 150604 gelio.castroga@udlap.mx
*/

//librerias proporcionadas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import play.mvc.*;

import views.html.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.libs.json.*;
import java.util.Scanner;
import java.io.File;

public class HomeController extends Controller {


	//Imprime un hola mundo en la pagina de inicio

    public Result index() {
        return ok(index.render("Hola mundo"));
    }
//Realiza la multiplicación con los números ingresados por el usuario
	public Result getMultiplicacion(int a, int b){
		ObjectNode result = Json.newObject();
		result.put("resultado", a*b );
		return ok(result);
	}

//Revisa que el string proporcionado por el usuario sea palindromo regresando un valor booleano
	public Result getPalindromo(String word){
		ObjectNode result = Json.newObject();
		String myWord = word.replaceAll("\\s+","");
		String reverse = new StringBuffer(myWord).reverse().toString();
		result.put("resultado", reverse.equalsIgnoreCase(myWord));
		return ok(result);
	}


  //Transforma la cantidad ingresada de pesos a dólares en su valor actual de compra.

	public Result getPesos(double dolares){
		double compra = 18.53;
		ObjectNode result = Json.newObject();
		result.put("$" + dolares + " USD = ", "$" + dolares*compra + " M.N");
		return ok(result);

	}


  //Transforma los grados fahrenheit ingresados por el usuario a centigrados
	public Result convertidorTemperatura(double farenheit){
		double centigrados = (farenheit-32) * 0.555555555555555555555556;
		ObjectNode result = Json.newObject();
		result.put(farenheit + "° F = ", centigrados + "° C");
		return ok(result);

	}


  //obtiene las coordenadas de la embajada en un pais europeo, buscandolas en un documento.
	public Result getEmbajada(String pais) throws java.io.FileNotFoundException{
    ObjectNode result=Json.newObject();
    Scanner scanner= new Scanner(new File("/Users/JuanCarlos/Desktop/paisesEmbajada.csv"));
    scanner.useDelimiter(",|\\n"); //termina de leer cuando aparece una coma o salto de linea
    String ubicacionEncontrada=" ";
    while(scanner.hasNext()&&ubicacionEncontrada.isEmpty())//busca en cada renglon
    {
      if (scanner.next().trim().equalsIgnoreCase(pais))//si es el pais que se bussca entra a la condicion
        ubicacionEncontrada=scanner.next();//guarda la ubicacipn
    }
    if (ubicacionEncontrada.isEmpty())
      result.put("resultado", "no se encontro");// no encontro la ubicación
    else
      result.put("resultado", ubicacionEncontrada);//si encontro la ubicacion
    return ok (result);
  }





  //Obtiene la capital de un páis europeo
	public Result getCapital(String pais)throws java.io.FileNotFoundException {
		ObjectNode result = Json.newObject();
    Scanner scanner= new Scanner(new File("/Users/JuanCarlos/Desktop/paisesEuropeos.csv"));
    scanner.useDelimiter(",|\\n"); //termina de leer cuando aparece una coma o salto de linea
    String capitalEncontrada=" ";
    while(scanner.hasNext() && capitalEncontrada.isEmpty()){ //busca en cada renglon
      if (scanner.next().trim().equalsIgnoreCase(pais)){// si lo que lee es el pais que se busca entra a la condición
        capitalEncontrada=scanner.next();// guarda la capital
      }
    }
    if (capitalEncontrada.isEmpty()){// en caso de no encontrar el país
      result.put("resultado","no se encontro");
    }
    else{
      result.put("resultado",capitalEncontrada);//en caso de que se encuentre el país
    }
    return ok (result);
	}
}
