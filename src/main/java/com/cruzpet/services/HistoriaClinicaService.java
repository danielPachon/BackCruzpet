package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.*;
import com.cruzpet.models.HistoriaClinicaModel;
import com.cruzpet.repositorys.ClienteRepository;
import com.cruzpet.repositorys.FormulasRepository;
import com.cruzpet.repositorys.HistoriaClinicaRepository;
import com.cruzpet.repositorys.VacunasRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("historiaClinicaService")
public class HistoriaClinicaService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("historiaClinicaRepository")
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    @Qualifier("formulasRepository")
    private FormulasRepository formulasRepository;

    @Autowired
    @Qualifier("vacunasRepository")
    private VacunasRepository vacunasRepository;

    @Autowired
    @Qualifier("clienteRepository")
    private ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(HistorialClinicaEntity historiaClinica){

        VacunaEntity vacuna = vacunasRepository.findByIdVacuna(historiaClinica.getVacuna().getIdVacuna());

        ClienteEntity cliente = clienteRepository.findByCedulaCliente(historiaClinica.getHistoriaClinicaCliente().getCedulaCliente());

        historiaClinica.setHistoriaClinicaCliente(cliente);
        historiaClinica.setVacuna(vacuna);

        try{
            historiaClinicaRepository.save(historiaClinica);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void actualizar(HistorialClinicaEntity historiasClinicas){

        try {
            HistorialClinicaEntity historiaClinica = historiaClinicaRepository.findByIdHistoriaClinica(historiasClinicas.getIdHistoriaClinica());
            historiaClinica.setHistoriaClinicaCliente(historiasClinicas.getHistoriaClinicaCliente());
            historiaClinica.setEdad(historiasClinicas.getEdad());
            historiaClinica.setHistoriaClinMascota(historiasClinicas.getHistoriaClinMascota());
            historiaClinica.setIpsaHistoriaClin(historiasClinicas.getIpsaHistoriaClin());
            historiaClinica.setFecha(historiasClinicas.getFecha());
            historiaClinica.setHistoriaClinVeterinario(historiasClinicas.getHistoriaClinVeterinario());
            historiaClinica.setHora(historiasClinicas.getHora());
            historiaClinica.setHistoriaClinicaCliente(historiasClinicas.getHistoriaClinicaCliente());
            historiaClinica.setConclucion(historiasClinicas.getConclucion());
            historiaClinica.setFormula(historiasClinicas.getFormula());
            historiaClinica.setMotivoConsulta(historiasClinicas.getMotivoConsulta());
            historiaClinica.setPeso(historiasClinicas.getPeso());
            historiaClinica.setPulso(historiasClinicas.getPulso());
            historiaClinica.setVacuna(historiasClinicas.getVacuna());
            historiaClinicaRepository.save(historiaClinica);
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }
    }

    public void borrar(Integer idHistoriaClin){
        try{
            HistorialClinicaEntity historiaClinica = historiaClinicaRepository.findByIdHistoriaClinica(idHistoriaClin);
            historiaClinicaRepository.delete(historiaClinica);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public List<HistoriaClinicaModel> obtenerHistoriasClinicas(){

        return convertidor.convertidorListaHistoriaClinica(historiaClinicaRepository.findAll());

    }

    public List<HistoriaClinicaModel> obtenerHistoriasClinicasIpsa(IpsaEntity ipsa){

        return convertidor.convertidorListaHistoriaClinica(historiaClinicaRepository.findByIpsaHistoriaClin_Rut(ipsa.getRut()));

    }

    public List<HistoriaClinicaModel> obtenerHistoriasClinicasVeterinario(VeterinarioEntity veterinario){

        return convertidor.convertidorListaHistoriaClinica(historiaClinicaRepository.findByHistoriaClinVeterinario_CedVeterinario(veterinario.getCedVeterinario().replace(" ", "")));

    }

    public List<HistoriaClinicaModel> obtenerHistoriasClinicasUsuario(ClienteEntity usuario){

        return convertidor.convertidorListaHistoriaClinica(historiaClinicaRepository.findByHistoriaClinicaCliente_CedulaCliente(usuario.getCedulaCliente().replace(" ", "")));

    }

    public List<HistoriaClinicaModel> obtenerHistoriasClinicasMascota(MascotaEntity mascota){

        return convertidor.convertidorListaHistoriaClinica(historiaClinicaRepository.findByHistoriaClinMascota_NumeroIdentidad(mascota.getNumeroIdentidad().replace(" ", "")));

    }

}
