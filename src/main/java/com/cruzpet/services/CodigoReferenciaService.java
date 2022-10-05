package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.BarrioEntity;
import com.cruzpet.entitys.CodigoReferenciaEntity;
import com.cruzpet.models.BarrioModel;
import com.cruzpet.models.CodigoReferenciaModel;
import com.cruzpet.repositorys.CodigoReferenciaRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.util.List;

@Service("codigoreferenciaservice")
public class CodigoReferenciaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    @Autowired
    @Qualifier("codigoReferenciaRepository")
    private CodigoReferenciaRepository codigoReferenciaRepository;

    public CodigoReferenciaModel crear(CodigoReferenciaEntity codigoReferenciaEntity){

        Random random = new Random();

        List<CodigoReferenciaEntity> codigoReferenciaEntities = codigoReferenciaRepository.findAll();

        String cadena = "";

        int repetido = 0;

        try{
            while(true){
                cadena = "";
                for(int i = 0; i<5; i++){

                    cadena += (char)(random.nextInt(26) + 'a');
                    cadena += (random.nextInt(10));

                }

                for(CodigoReferenciaEntity codigoReferencia:codigoReferenciaEntities){
                    if(cadena.toUpperCase().equals(codigoReferencia)){
                        repetido++;
                        break;
                    }
                }

                if(repetido == 0){
                    break;
                }

            }
            codigoReferenciaEntity.setCodigoReferencia(cadena.toUpperCase());
            codigoReferenciaRepository.save(codigoReferenciaEntity);
            return convertidor.convertirObjetoCodigoReferencia(codigoReferenciaEntity);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public void actualizar(CodigoReferenciaEntity codigoReferenciaEntity){
        try{
            CodigoReferenciaEntity codigoReferencia = codigoReferenciaRepository.findByCodigoReferencia(codigoReferenciaEntity.getCodigoReferencia());
            codigoReferencia.setCodigoReferencia(codigoReferenciaEntity.getCodigoReferencia());
            codigoReferencia.setCodigoReferenciaCliente(codigoReferenciaEntity.getCodigoReferenciaCliente());
            codigoReferenciaRepository.save(codigoReferencia);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<CodigoReferenciaModel> mostrarCodigosReferencia(){
        return convertidor.convertirListaCodigoReferencia(codigoReferenciaRepository.findAll());
    }

    public List<CodigoReferenciaModel> mostrarCodigosRerenciaCliente(String cedula){

        return convertidor.convertirListaCodigoReferencia(codigoReferenciaRepository.findByCodigoReferenciaCliente_CedulaCliente(cedula));

    }

}
