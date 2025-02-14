package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.CarBusiness;
import fr.eql.ai116.proj2.tim.entity.Car;
import fr.eql.ai116.proj2.tim.entity.dto.CarDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless //classe SpaceController est un EJB (Enterprise JavaBean) sans état
@Path("/car") //le chemin d'accès pour cette classe est /car
public class CarController {



        @EJB //injecte une dépendance vers le composant CarBusiness
        CarBusiness carBusiness;

        @GET // la méthode qui suit répondra à des requêtes HTTP de type GET pour récupérer
        // des données, dans ce cas, la liste des "voitures" d'un propriétaire spécifié.
        @Path("/owner/{id}/cars") ///{id} dans le chemin est un paramètre dynamique qui représente l'identifiant du propriétaire
        @Produces(MediaType.APPLICATION_JSON) // cette méthode produira des résultats sous le format JSON
        public Response fetchUserCars(@PathParam("id") long id) { //Cette méthode prend l'ID du propriétaire (extraite de l'URL) en tant que paramètre.
            List<Car> cars = carBusiness.findUserCar(id);
            return Response.ok(cars).build();
        }

        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addCar(CarDto carDto) {
            carBusiness.addCar(carDto);
            return Response.ok().build();
        }
}
