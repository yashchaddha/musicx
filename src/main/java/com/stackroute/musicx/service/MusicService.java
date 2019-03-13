package com.stackroute.musicx.service;

import com.stackroute.musicx.domain.Music;
import com.stackroute.musicx.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicx.exceptions.TrackNotFoundException;
import com.stackroute.musicx.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MusicService implements CommandLineRunner,ApplicationListener<ContextRefreshedEvent> {
    private MusicRepository musicRepository;
@Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music saveTrack(Music music)throws TrackAlreadyExistsException {
    if(musicRepository.existsById(music.getId()))
    {
        throw new TrackAlreadyExistsException("User Already Exists");
    }
    Music savedTrack=musicRepository.save(music);
    if(savedTrack==null)
    {
        throw new TrackAlreadyExistsException("User Already Exists");
    }
    return savedTrack;
    }

    public List<Music> displayTrack() {
        return (List<Music>)musicRepository.displayTrackall();
    }

    public void deleteTrack(int id) {
        musicRepository.deleteById(id);
    }

    public Music updateTrack(Music music, int id)throws TrackNotFoundException {
        if(!musicRepository.existsById(music.getId()))
        {
            throw new TrackNotFoundException("User Not Found");
        }
            Music musicList = musicRepository.findById(id).get();
            musicList.setTrackComments(music.getTrackComments());
        return  musicRepository.save(musicList);
    }

    public Music displayTracksById(int id) {
       return musicRepository.findById(id).get();
    }

    @Value("${name:default}")
    String trackname;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        musicRepository.save(new Music(1,trackname,"fa"));
        musicRepository.save(new Music(2,"sad","sdfkjdskjf"));
    }

    @Override
    public void run(String... args) throws Exception {
        musicRepository.save(new Music(3,"sasdsa","sdassf"));
    }
}
