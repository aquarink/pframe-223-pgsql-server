package controllers;

import static play.data.Form.form;

import java.io.IOException;

import models.Tbtestserver;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result serverGet() {
    	return ok("GET");
    }
    
    public static Result serverPost() {
    	Form<Tbtestserver> tbtestserver = form(Tbtestserver.class).bindFromRequest();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
//    	int id = declareData.get().getId();
//    	String title = declareData.get().getDescrip();
    	String data = "";
    	
    	if(tbtestserver.hasErrors()) {
    		Logger.debug("declareData hasErrors");
        } else {
        	
        	try {
        		
    			data = objectMapper.writeValueAsString(tbtestserver.get());
    			Logger.debug("Server Data_ : "+data);
    			return ok(data);
    		} catch (JsonGenerationException e) {
    			Logger.debug("JsonGenerationException");
    		} catch (JsonMappingException e) {
    			Logger.debug("JsonMappingException");
    		} catch (IOException e) {
    			Logger.debug("IOException");
    		}
        	
        }
    	
    	
		Logger.debug("Server Data : "+data);
		//return ok((id + titleInt)+"");
		return ok(data);
    }

}
