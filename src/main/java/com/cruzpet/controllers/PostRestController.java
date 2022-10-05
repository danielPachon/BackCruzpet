package com.cruzpet.controllers;

import com.cruzpet.entitys.CiudadEntity;
import com.cruzpet.entitys.MascotaEntity;
import com.cruzpet.entitys.PostEntity;
import com.cruzpet.entitys.RazaEntity;
import com.cruzpet.models.CiudadModel;
import com.cruzpet.models.PostModel;
import com.cruzpet.services.PostService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1.0/posts")
public class PostRestController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    @PostMapping("/crear")
    public ResponseEntity<String> agregarPost(@RequestBody PostEntity postEntity){
        try{
            postService.crear(postEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("El post fue registrado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al registrar el post");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPost(@RequestBody PostEntity postEntity){
        try{
            postService.actualizar(postEntity);
            return ResponseEntity.status(HttpStatus.OK).body("El post fue actualizado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al actualizar el post");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> borrarPost(@PathVariable("id") Integer idPost){
        try{
            postService.eliminar(idPost);
            return ResponseEntity.status(HttpStatus.OK).body("El post fue eliminado con exito");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrio un error al eliminar el post");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PostModel>> obtenerPosts(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.mostrarPosts());
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los posts", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tipopost/{tipopost}")
    public ResponseEntity<List<PostModel>> mostrarPost(@PathVariable("tipopost") char tipopost){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.mostrarPostsTipo(tipopost));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los posts", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/tipopost/actualizarimagenpost")
    public ResponseEntity<String> actualizarImagenPost(@RequestBody PostEntity postEntity){

        try{
            postService.actualizarImagenPosts(postEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo la imagen correctamente");
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los posts", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/traerpost")
    public ResponseEntity<PostModel> mostrarPostId(@RequestBody PostEntity postEntity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.mostrarPostId(postEntity.getIdPost()));
        }catch (Exception ex){

            log.error(ex.getMessage());
            return new ResponseEntity("No se pudieron traer los posts", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/traerpostpaginador")
    public ResponseEntity<Page<PostEntity>> obtenerPostsPaginador(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6")int size){

        Page<PostEntity> posts = postService.obtenerPostPaginador(
                PageRequest.of(page, size));
        return new ResponseEntity<Page<PostEntity>>(posts, HttpStatus.OK);
    }

}
