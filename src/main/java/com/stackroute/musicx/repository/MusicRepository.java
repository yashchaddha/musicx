package com.stackroute.musicx.repository;

import com.stackroute.musicx.domain.Music;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends CrudRepository<Music,Integer> {
    @Query(value = "select * from Music",nativeQuery = true)
    public List<Music> displayTrackall();
}
