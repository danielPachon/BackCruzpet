package com.cruzpet.converter;

import com.cruzpet.entitys.*;
import com.cruzpet.models.*;
import com.cruzpet.services.DisponibilidadService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//EL CONVERTIDOR NOS SIRVE PARA CONVERTIR LOS DATOS DE LOS ENTITYS A DATOS DE LOS MODELOS PARA ASI SER MOSTRADOS
//EN LAS VISTAS, YA QUE POR BUENAS PRACTICAS, LOS DATOS DE LOS ENTITYS NO SE DEBEN MEZCLAR CON LAS VIEWS.

@Component("convertidor")
public class Convertidor {

    //CONVERTIDOR DEL ENTITY RAZA AL MODELO RAZA
    public List<RazaModel> convertirListaRaza(List<RazaEntity> raza){

        List<RazaModel> razaModels = new ArrayList<>();

        for(RazaEntity razas : raza){

            razaModels.add(new RazaModel(razas));

        }
        return razaModels;

    }

    //CONVERTIDOR DEL ENTITY DEPARTAMENTO AL MODELO DEPARTAMENTO
    public List<DepartamentoModel> convertirListaDepartamento(List<DepartamentoEntity> departamentos) {

        List<DepartamentoModel> departamentoModels = new ArrayList<>();

        for(DepartamentoEntity departamento: departamentos) {
            departamentoModels.add(new DepartamentoModel(departamento));
        }

        return departamentoModels;

    }

    //CONVERTIDOR DEL ENTITY ESPECIALIDAD AL MODELO ESPECIALIDAD
    public  List<EspecialidadModel> convertirListaEspecialidad(List<EspecialidadEntity> especialidades) {
        List<EspecialidadModel> especialidadModels = new ArrayList<>();
        for(EspecialidadEntity especialidad: especialidades) {
            especialidadModels.add(new EspecialidadModel(especialidad));
        }
        return especialidadModels;
    }

    //CONVERTIDOR DEL ENTITY FORMULAS AL MODELO FORMULAS
    public List<FormulasModel> convertidorListaFormulas(List<FormulaEntity> formulas) {

        List<FormulasModel> formulasModels = new ArrayList<>();
        for(FormulaEntity formulaEntity : formulas){
            formulasModels.add(new FormulasModel(formulaEntity));
        }
        return formulasModels;
    }

    //CONVERTIDOR DEL ENTITY GENEROS AL MODELO GENEROS
    public List<GenerosModel> convertidorListaGeneros(List<GeneroEntity> generoEntities) {

        List<GenerosModel> generos = new ArrayList<>();
        for(GeneroEntity genero: generoEntities){
            generos.add(new GenerosModel(genero));
        }
        return generos;
    }

    //CONVERTIDOR DEL ENTITY IPSAS AL MODELO IPSAS
    public List<IpsasModel> convertidorListaIpsas(List<IpsaEntity> ipsaEntity) {

        List<IpsasModel> ipsas = new ArrayList<>();
        for(IpsaEntity ipsa: ipsaEntity){
            ipsas.add(new IpsasModel(ipsa));
        }
        return ipsas;
    }

    public IpsasModel convertirObjetoIpsa(IpsaEntity ipsaEntity){

        IpsasModel ipsas = new IpsasModel(ipsaEntity);
        return ipsas;
    }


    //CONVERTIDOR DEL ENTITY MEDICAMENTO AL MODELO MEDICAMENTO
    public List<MedicamentosModel> convertidorListaMedicamento(List<MedicamentoEntity> medicamentoEntity) {

        List<MedicamentosModel> medicamentos = new ArrayList<>();
        for(MedicamentoEntity medicamento: medicamentoEntity){
            medicamentos.add(new MedicamentosModel(medicamento));
        }
        return medicamentos;
    }

    //CONVERTIDOR DEL ENTITY TIPO MASCOTA AL MODELO TIPO MASCOTA
    public List<TipoMascotaModel> convertidorListaTipoMascota(List<TipoMascotaEntity> tipoMascotaEntity) {

        List<TipoMascotaModel> tipoMascota = new ArrayList<>();
        for(TipoMascotaEntity tipo: tipoMascotaEntity){
            tipoMascota.add(new TipoMascotaModel(tipo));
        }
        return tipoMascota;
    }

    //CONVERTIDOR DEL ENTITY TIPO SANGRE AL MODELO TIPO SANGRE
    public List<TipoSangreModel> convertidorListaTipoSangre(List<TipoSangreEntity> tipoSangreEntity){

        List<TipoSangreModel> tipoSangre = new ArrayList<>();
        for(TipoSangreEntity tipo: tipoSangreEntity){
            tipoSangre.add(new TipoSangreModel(tipo));
        }
        return tipoSangre;

    }

    //CONVERTIDOR DEL ENTITY USUARIO AL MODELO USUARIO
    public List<ClienteModel> convertidorListaClientes(List<ClienteEntity> clienteEntity){

        List<ClienteModel> clientes = new ArrayList<>();
        for(ClienteEntity cliente: clienteEntity){
            clientes.add(new ClienteModel(cliente));
        }
        return clientes;

    }

    public ClienteModel convertirObjetoClientes(ClienteEntity clienteEntity){

        ClienteModel cliente = new ClienteModel(clienteEntity);
        return cliente;
    }



    //CONVERTIDOR DEL ENTITY VACUNAS AL MODELO VACUNAS
    public List<VacunasModel> convertidorListaVacunas(List<VacunaEntity> vacunaEntity){

        List<VacunasModel> vacunas = new ArrayList<>();
        for(VacunaEntity vacuna: vacunaEntity){
            vacunas.add(new VacunasModel(vacuna));
        }
        return vacunas;

    }

    //CONVERTIDOR DEL ENTITY VACUNASDETALLES AL MODELO VACUNASDETALLES
    public List<VacunaDetalleModel> convertidorListaVacunasDetalles(List<VacunaDetalleEntity> vacunaDetalleEntity){

        List<VacunaDetalleModel> vacunasDetalles = new ArrayList<>();
        for(VacunaDetalleEntity vacunaDetalle: vacunaDetalleEntity){
            vacunasDetalles.add(new VacunaDetalleModel(vacunaDetalle));
        }
        return vacunasDetalles;

    }

    //CONVERTIDOR DEL ENTITY CIUDAD AL MODELO CIUDAD
    public List<CiudadModel> convertidorListaCiudad(List<CiudadEntity> ciudadEntity){

        List<CiudadModel> ciudades = new ArrayList<>();
        for(CiudadEntity ciudad: ciudadEntity){
            ciudades.add(new CiudadModel(ciudad));
        }
        return ciudades;
    }

    public CiudadModel convertidorObjetoCiudad(CiudadEntity ciudad){

        CiudadModel ciudadModels = new CiudadModel(ciudad.getIdCiudad(),ciudad.getNombreCiudad(), ciudad.getDepartamentoOrigen());
        return ciudadModels;
    }

    //CONVERTIDOR DEL ENTITY BARRIO AL MODELO BARRIO
    public List<BarrioModel> convertidorListaBarrios(List<BarrioEntity> barrioEntity){

        List<BarrioModel> barrios = new ArrayList<>();
        for(BarrioEntity barrio: barrioEntity){
            barrios.add(new BarrioModel(barrio));
        }
        return barrios;
    }

    public BarrioModel convertidorObjetoBarrios(BarrioEntity barrioEntity){

        BarrioModel barrioModel = new BarrioModel(barrioEntity);
        return barrioModel;
    }

    //CONVERTIDOR DEL ENTITY CITA AL MODELO CITA
    public List<CitaModel> convertidorListaCitas(List<CitaEntity> citaEntity){

        List<CitaModel> citas = new ArrayList<>();
        for(CitaEntity cita: citaEntity){
            citas.add(new CitaModel(cita));
        }
        return citas;
    }

    public CitaModel convertirObjetoCitas(CitaEntity citaEntity){

        CitaModel citas = new CitaModel(citaEntity);
        return citas;
    }

    //CONVERTIDOR DEL ENTITY MASCOTA AL MODELO MASCOTA
    public List<MascotaModel> convertidorListaMascotas(List<MascotaEntity> mascotaEntity){

        List<MascotaModel> mascotas = new ArrayList<>();
        for(MascotaEntity mascota: mascotaEntity){
            mascotas.add(new MascotaModel(mascota));
        }
        return mascotas;
    }

    public MascotaModel convertirObjetoMascota(MascotaEntity mascotaEntity){

        MascotaModel mascotaModel = new MascotaModel(mascotaEntity);
        return mascotaModel;
    }

    //CONVERTIDOR DEL ENTITY VETERINARIO AL MODELO VETERINARIO
    public List<VeterinarioModel> convertidorListaVeterinarios(List<VeterinarioEntity> veterinarioEntity){

        List<VeterinarioModel> veterinarios = new ArrayList<>();
        for(VeterinarioEntity veterinario: veterinarioEntity){
            veterinarios.add(new VeterinarioModel(veterinario));
        }
        return veterinarios;
    }



    //CONVERTIDOR DEL ENTITY HISTORIA CLINICA AL MODELO HISTORIA CLINICA
    public List<HistoriaClinicaModel> convertidorListaHistoriaClinica(List<HistorialClinicaEntity> historiaEntity){

        List<HistoriaClinicaModel> historiasClinicas = new ArrayList<>();
        for(HistorialClinicaEntity historiasClinica: historiaEntity){
            historiasClinicas.add(new HistoriaClinicaModel(historiasClinica));
        }
        return historiasClinicas;
    }

    //CONVERTIDOR DEL ENTITY DIRECCION AL MODELO DIRECCION
    public List<DireccionModel> convertidorListaDireccion(List<DireccionEntity> direccionEntity){

        List<DireccionModel> direcciones = new ArrayList<>();
        for(DireccionEntity direccion: direccionEntity){
            direcciones.add(new DireccionModel(direccion));
        }
        return direcciones;
    }

    //CONVERTIDOR DEL ENTITY POST AL MODELO POST
    public List<PostModel> convertidorListaPost(List<PostEntity> postEntity){

        List<PostModel> posts = new ArrayList<>();
        for(PostEntity post: postEntity){
            posts.add(new PostModel(post.getIdPost(), post.getTituloPost(), post.getFechaPost(), post.getCuerpoPost(), post.getTipoPost(), post.getCedulaCliente(), post.getAdministradorCreador(), post.getImagen(), post.getEstado()));
        }
        return posts;
    }
    public PostModel convertirObjetoPost(PostEntity postEntity){

        PostModel posts = new PostModel(postEntity);
        return posts;
    }

    //CONVERTIDOR DEL ENTITY ADMINISTRADOR AL MODELO ADMINISTRADOR
    public List<AdministradorModel> convertidorListaAdministrador(List<AdministradorEntity> administradorEntity){

        List<AdministradorModel> administradores = new ArrayList<>();
        for(AdministradorEntity administrador: administradorEntity){
            administradores.add(new AdministradorModel(administrador));
        }
        return administradores;
    }

    public AdministradorModel convertirObjetoAdministrador(AdministradorEntity administradorEntity){

        AdministradorModel administrador = new AdministradorModel(administradorEntity);
        return administrador;
    }


    //CONVERTIDOR DEL ENTITY CARNETVACUNCACION AL MODELO CARNETVACUNCACION
    public List<CarnetVacunacionModel> convertidorListaCarnetVacunacion(List<CarnetVacunacionEntity> carnetVacunacionEntity){

        List<CarnetVacunacionModel> carnetVacunacion = new ArrayList<>();
        for(CarnetVacunacionEntity carnetVacunas: carnetVacunacionEntity){
            carnetVacunacion.add(new CarnetVacunacionModel(carnetVacunas));
        }
        return carnetVacunacion;
    }

    //CONVERTIDOR DEL ENTITY SERVICIO AL MODELO SERVICIO
    public List<ServiciosModel> convertidorListaServicio(List<ServiciosEntity> serviciosEntity){

        List<ServiciosModel> servicios = new ArrayList<>();
        for(ServiciosEntity servicio: serviciosEntity){
            servicios.add(new ServiciosModel(servicio));
        }
        return servicios;
    }

    //CONVERTIDOR DEL ENTITY PLANES AL MODELO PLANES
    public List<PlanesModel> convertidorListaPlanes(List<PlanesEntity> planesEntity){

        List<PlanesModel> planes = new ArrayList<>();
        for(PlanesEntity plan: planesEntity){
            planes.add(new PlanesModel(plan));
        }
        return planes;
    }

    public PlanesModel convertirObjetoPlanes(PlanesEntity planesEntity){

        PlanesModel planesModel = new PlanesModel(planesEntity);
        return planesModel;
    }

    //CONVERTIDOR DEL ENTITY PRODUCTOS AL MODELO PRODUCTOS
    public List<ProductosModel> convertidorListaProductos(List<ProductosEntity> productosEntity){

        List<ProductosModel> productosModel = new ArrayList<>();
        for(ProductosEntity productos: productosEntity){
            productosModel.add(new ProductosModel(productos));
        }
        return productosModel;
    }

    public ProductosModel convertirObjetoProductos(ProductosEntity productosEntity){

        ProductosModel productosModel = new ProductosModel(productosEntity);
        return productosModel;
    }

    //CONVERTIDOR DEL ENTITY CALIFICACIONES AL MODELO CALIFICACIONES
    public List<CalificacionesModel> convertidorListaCalificaciones(List<CalificacionEntity> calificacionEntity){

        List<CalificacionesModel> calificacionesModel = new ArrayList<>();
        for(CalificacionEntity calificacion: calificacionEntity){
            calificacionesModel.add(new CalificacionesModel(calificacion));
        }
        return calificacionesModel;
    }



    //CONVERTIDOR DEL ENTITY BENEFICIOS AL MODELO BENEFICIOS
    public List<BeneficiosModel> convertirListaBeneficios(List<BeneficiosEntity> beneficiosEntity){

        List<BeneficiosModel> beneficiosModel = new ArrayList<>();
        for(BeneficiosEntity beneficios: beneficiosEntity){
            beneficiosModel.add(new BeneficiosModel(beneficios));
        }
        return beneficiosModel;
    }

    //CONVERTIDOR DEL ENTITY CODIGO REFERENCIA AL MODELO CODIGO REFERENCIA
    public List<CodigoReferenciaModel> convertirListaCodigoReferencia(List<CodigoReferenciaEntity> codigoReferencia){

        List<CodigoReferenciaModel> codigoReferenciaModel = new ArrayList<>();
        for(CodigoReferenciaEntity codigoReferenciaEntity: codigoReferencia){
            codigoReferenciaModel.add(new CodigoReferenciaModel(codigoReferenciaEntity));
        }
        return codigoReferenciaModel;
    }

    public CodigoReferenciaModel convertirObjetoCodigoReferencia(CodigoReferenciaEntity codigoReferencia){

        CodigoReferenciaModel codigoReferenciaModel = new CodigoReferenciaModel(codigoReferencia);
        return codigoReferenciaModel;
    }

    //CONVERTIDOR DEL ENTITY DISPONIBILIDAD AL MODELO DISPONIBILIDAD
    public List<DisponibilidadModel> convertirListaDisponibilidades(List<DisponibilidadEntity> disponibilidades){

        List<DisponibilidadModel> disponibilidadModels = new ArrayList<>();
        for(DisponibilidadEntity disponibilidadEntity: disponibilidades){
            disponibilidadModels.add(new DisponibilidadModel(disponibilidadEntity));
        }
        return disponibilidadModels;
    }

    public List<DisponibilidadModel> convertirListaDisponibilidadessinintervalosyveterinario(List<DisponibilidadEntity> disponibilidades){

        List<DisponibilidadModel> disponibilidadModels = new ArrayList<>();
        for(DisponibilidadEntity disponibilidadEntity: disponibilidades){
            IpsaEntity ipsaNueva = new IpsaEntity(disponibilidadEntity.getIpsafk().getRut(), disponibilidadEntity.getIpsafk().getNombre(), disponibilidadEntity.getIpsafk().getEstado(), disponibilidadEntity.getIpsafk().getLogoIpsa(), disponibilidadEntity.getIpsafk().getCorreoIpsa());
            disponibilidadModels.add(new DisponibilidadModel(disponibilidadEntity.getIdDisponibilidad(), disponibilidadEntity.getDiaDisponibilidad(), disponibilidadEntity.getMesDisponibilidad(), disponibilidadEntity.getYearDisponibilidad(), disponibilidadEntity.getHoraEntrada(), disponibilidadEntity.getHoraSalida(), ipsaNueva, disponibilidadEntity.getEstado()));
        }
        return disponibilidadModels;
    }

    public List<VeterinarioDisponibilidadesModel> convertirListaDisponibilidadessinintervalos(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadesEntities){

        List<VeterinarioDisponibilidadesModel> veterinarioDisponibilidadesModels = new ArrayList<>();
        for(VeterinarioDisponibilidadesEntity veterinarioDisponibilidadesEntity: veterinarioDisponibilidadesEntities){
            IpsaEntity ipsaNueva = new IpsaEntity(veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getRut(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getNombre(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getEstado(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getLogoIpsa(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIpsafk().getCorreoIpsa());
            DisponibilidadEntity disponibilidadEntity = new DisponibilidadEntity(veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIdDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getDiaDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getMesDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getYearDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getHoraEntrada(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getHoraSalida(), ipsaNueva);
            VeterinarioEntity veterinarioEntity = new VeterinarioEntity(veterinarioDisponibilidadesEntity.getVeterinarioEntity().getCedVeterinario(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getNombres(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getApellidos(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getCelular(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getFoto(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getEspecialidadVeterinario(), veterinarioDisponibilidadesEntity.getVeterinarioEntity().getEstado());
            veterinarioDisponibilidadesModels.add(new VeterinarioDisponibilidadesModel(veterinarioDisponibilidadesEntity.getId(), disponibilidadEntity, veterinarioEntity));
        }
        return veterinarioDisponibilidadesModels;
    }

    public List<VeterinarioDisponibilidadesModel> convertirListaDisponibilidadesconintervalos(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadesEntities){

        List<VeterinarioDisponibilidadesModel> veterinarioDisponibilidadesModels = new ArrayList<>();
        for(VeterinarioDisponibilidadesEntity veterinarioDisponibilidadesEntity: veterinarioDisponibilidadesEntities){
            DisponibilidadEntity disponibilidadEntity = new DisponibilidadEntity(veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIdDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getDiaDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getMesDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getYearDisponibilidad(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getHoraEntrada(), veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getIntervalos(),veterinarioDisponibilidadesEntity.getDisponibilidadEntity().getHoraSalida());
            veterinarioDisponibilidadesModels.add(new VeterinarioDisponibilidadesModel(veterinarioDisponibilidadesEntity.getId(),disponibilidadEntity));
        }
        return veterinarioDisponibilidadesModels;
    }


    //CONVERTIDOR DEL ENTITY COMPRAS AL MODELO COMPRAS
    public List<ComprasModel> convertirListaCompras(List<ComprasEntity> compras){

        List<ComprasModel> comprasModels = new ArrayList<>();
        for(ComprasEntity comprasEntity: compras){
            comprasModels.add(new ComprasModel(comprasEntity));
        }
        return comprasModels;
    }

    public ComprasModel convertirObjetoCompras(ComprasEntity comprasEntity){

        ComprasModel comprasModel = new ComprasModel(comprasEntity);
        return comprasModel;
    }

    //CONVERTIDOR DEL ENTITY VETERINARIODISPONIBILIDADES AL MODELO VETERINARIODISPONIBILIDADES
    public List<VeterinarioDisponibilidadesModel> convertirListaVeterinarioDisponibilidades(List<VeterinarioDisponibilidadesEntity> veterinarioDisponibilidadesEntities){

        List<VeterinarioDisponibilidadesModel> veterinarioDisponibilidadesModels = new ArrayList<>();
        for(VeterinarioDisponibilidadesEntity veterinarioDisponibilidades: veterinarioDisponibilidadesEntities){
            veterinarioDisponibilidadesModels.add(new VeterinarioDisponibilidadesModel(veterinarioDisponibilidades));
        }
        return veterinarioDisponibilidadesModels;
    }



}

