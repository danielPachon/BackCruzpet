package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.DepartamentoEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.DepartamentoModel;
import com.cruzpet.models.ProductosModel;
import com.cruzpet.repositorys.DepartamentoRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//LOS SERVICIOS NOS SERVIRAN PARA REALIZAR LA LOGICA DE NEGOCIO A NUESTRO PROGRAMA, AQUI SE MANIPULARA
//LOS DATOS DE LAS ENTITYS Y DE LOS MODELOS SEGUN COMO LO NECESITE NUESTRO PROGRAMA

@Service("departamentoService") //INDICAMOS QUE LA CLASE SERA UN SERVICIO CON EL NOMBRE "departamentoService"
public class DepartamentoService {

    //EL LOGGER NOS SERVIRA PARA MOSTRAR INFORMACION EN LA CONSOLA DE SPRING BOOT, EFICIENTE PARA VER ERRORES
    Log logger = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("departamentoRepository")
    private DepartamentoRepository departamentoRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    //LA LOGICA ES DEFINIDA POR MEDIO POR MEDIO DE METODOS, DENTRO DE ESTOS ESTARA TODA LA LOGICA QUE SE NECESITARA
    //PARA DICHO METODO

    public void crear(DepartamentoEntity departamentoEntity) {
        //SE UTILIZA LOS TRY CATCH PARA LA MANIPULACION DE ERRORES DURANTE EL FUNCIONAMIENTO DEL METODO
        try{
            logger.info(departamentoEntity);
            departamentoRepository.save(departamentoEntity); //INDICAMOS QUE NOS GUARDE DICHA ENTIDAD EN LA BD

        }catch (Exception e){

            //ESTE LOGGER IMPRIME EL ERROR QUE SE GENERO EN LA CONSOLA DE SPRING BOOT, DICHO ERROR ES DE NIVEL .error
            logger.error(e.getMessage());
        }
    }

    public void actualizar(DepartamentoEntity departamentoEntity) {
        try{
            //.findByIdDepartamento ES UNA CONSULTA SQL PERSONALIZADA QUE NOS SERVIRA PARA TRAER UN
            //USUARIO POR MEDIO DEL "idDepartamento" DE LA ENTIDAD
            DepartamentoEntity departamento = departamentoRepository.findByIdDepartamento(departamentoEntity.getIdDepartamento());
            //AQUI SE HACEN LAS MODIFICACIONES PARA LA ACTUALIZACION
            departamento.setIdDepartamento(departamentoEntity.getIdDepartamento());
            departamento.setNombreDepartamento(departamentoEntity.getNombreDepartamento());
            departamento.setAdministradorCreador(departamentoEntity.getAdministradorCreador());
            departamentoRepository.save(departamento); //SE GUARDA LA ENTIDAD MODIFICADA, TAMBIEN EL
            //.save SIRVE PARA ACTUALIZAR

        }catch (Exception e){

            logger.error(e.getMessage());
        }
    }

    public void eliminar(Integer idDepartamento){
        try{
            DepartamentoEntity departamento = departamentoRepository.findByIdDepartamento(idDepartamento);
            departamentoRepository.delete(departamento); //PERMITE ELIMINAR REGISTROS DE LA BD
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<DepartamentoModel> obtenerDepartamentos(){
        //AQUI SE CONVIERTE DE ENTITYS A MODELOS PARA YA SER MOSTRADOS EN LAS VIEWS
        //.findAll() SIRVE PARA VER TODOS LOS REGISTROS QUE HAY EN LA TABLA DE LA BD
        return convertidor.convertirListaDepartamento(departamentoRepository.findAll());
    }

    public Page<DepartamentoEntity> obtenerDepartamentosPaginador(Pageable pageable){
        return departamentoRepository.findAll(pageable);
    }

    public DepartamentoEntity obtenerDepartamento(String nombreDepartamento) {

        return departamentoRepository.findByNombreDepartamento(nombreDepartamento);

    }

    public DepartamentoEntity obtenerDepartamentoId(DepartamentoEntity departamentoEntity) {

        return departamentoRepository.findByIdDepartamento(departamentoEntity.getIdDepartamento());

    }

    public List<DepartamentoEntity> mostrarDepartamentosPalabra(String palabraDepartamento){

        int espacios = 0;

        int indice = 0;

        for(int i = 0; i<palabraDepartamento.length(); i++ ){

            if(palabraDepartamento.charAt(i) == ' '){

                indice=i-1;
                espacios++;
                break;

            }

        }

        String primeraPalabra = palabraDepartamento.substring(0, indice+1);

        List<DepartamentoEntity> departamentos = departamentoRepository.findAll();

        List<DepartamentoEntity> departamentosMostrar = new ArrayList<>();

        if(espacios > 0){

            for(DepartamentoEntity departamento: departamentos){

                String palabraClave = String.valueOf(departamento.getNombreDepartamento().charAt(0));

                for(int i =1; i<departamento.getNombreDepartamento().length(); i++){

                    if(departamento.getNombreDepartamento().charAt(i) != ' '){

                        palabraClave += departamento.getNombreDepartamento().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraDepartamento.toLowerCase())){

                            if(!departamentosMostrar.contains(departamento)){
                                departamentosMostrar.add(departamento);
                                palabraClave = "";

                            }

                        }

                    }else{

                        if(palabraClave.toLowerCase().equals(primeraPalabra.toLowerCase())){


                            for(int a = indice+2; a<palabraDepartamento.length(); a++){

                                if (palabraDepartamento.charAt(a) == ' ' || palabraDepartamento.charAt(a) == palabraDepartamento.charAt(palabraDepartamento.length()-1)){

                                    primeraPalabra += " " + palabraDepartamento.substring(indice+2, a+1);
                                    palabraClave += " ";
                                    break;
                                }
                            }

                        }else{

                            palabraClave = "";

                        }

                    }

                }

                for(int j = 0; j<palabraDepartamento.length(); j++ ){

                    if(palabraDepartamento.charAt(j) == ' '){

                        indice=j-1;
                        espacios++;
                        break;

                    }

                }

                primeraPalabra = palabraDepartamento.substring(0, indice+1);

            }


        }else{

            for (DepartamentoEntity departamento: departamentos){

                String palabraClave = "";

                for(int i = 0; i < departamento.getNombreDepartamento().length(); i++){

                    if(departamento.getNombreDepartamento().charAt(i) != ' '){

                        palabraClave += departamento.getNombreDepartamento().charAt(i);
                        if(palabraClave.toLowerCase().equals(palabraDepartamento.toLowerCase())){

                            if(!departamentosMostrar.contains(departamento)){
                                departamentosMostrar.add(departamento);
                                palabraClave = "";
                            }

                        }

                    }else{

                        palabraClave = "";

                    }


                }

            }

        }


        return departamentosMostrar;

    }

}
