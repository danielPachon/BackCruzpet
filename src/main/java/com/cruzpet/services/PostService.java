package com.cruzpet.services;

import com.cruzpet.converter.Convertidor;
import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.entitys.PostEntity;
import com.cruzpet.entitys.ProductosEntity;
import com.cruzpet.models.CiudadModel;
import com.cruzpet.models.PostModel;
import com.cruzpet.repositorys.PostRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("postService")
public class PostService {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("postRepository")
    private PostRepository postRepository;

    @Autowired
    @Qualifier("convertidor")
    private Convertidor convertidor;

    public void crear(PostEntity postEntity) {

        try {
            postRepository.save(postEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public void actualizar(PostEntity postEntity) {
        try {
            PostEntity posts = postRepository.findByIdPost(postEntity.getIdPost());
            posts.setCuerpoPost(postEntity.getCuerpoPost());
            posts.setFechaPost(postEntity.getFechaPost());
            posts.setTipoPost(postEntity.getTipoPost());
            posts.setCedulaCliente(postEntity.getCedulaCliente());
            posts.setTituloPost(postEntity.getTituloPost());
            posts.setEstado(postEntity.getEstado());
            posts.setAdministradorCreador(postEntity.getAdministradorCreador());
            postRepository.save(posts);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void eliminar(Integer idPost) {

        try {
            PostEntity posts = postRepository.findByIdPost(idPost);
            postRepository.delete(posts);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public List<PostModel> mostrarPosts() {
        return convertidor.convertidorListaPost(postRepository.findAll());
    }

    public List<PostModel> mostrarPostsTipo(char tipoPost){

        return convertidor.convertidorListaPost(postRepository.findByTipoPost(tipoPost));

    }

    public PostModel mostrarPostId(Integer idPost){

        return convertidor.convertirObjetoPost(postRepository.findByIdPost(idPost));

    }

    public void actualizarImagenPosts(PostEntity postEntity){

        try {
            PostEntity posts = postRepository.findByIdPost(postEntity.getIdPost());
            posts.setImagen(postEntity.getImagen());
            postRepository.save(posts);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    public Page<PostEntity> obtenerPostPaginador(Pageable pageable){
        return postRepository.findAll(pageable);
    }

}
