package com.cruzpet.repositorys;

import com.cruzpet.entitys.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    PostEntity findByIdPost(Integer idPost);

    List<PostEntity> findByTipoPost(char tipoPost);

}
