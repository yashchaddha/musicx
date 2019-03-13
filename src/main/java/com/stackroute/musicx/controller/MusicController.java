package com.stackroute.musicx.controller;

import com.stackroute.musicx.domain.Music;
import com.stackroute.musicx.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicx.exceptions.TrackNotFoundException;
import com.stackroute.musicx.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class MusicController {
    private MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService) {

      this.musicService = musicService;
    }
    @PostMapping("/tracks")
    public ResponseEntity<?> saveTrack(@RequestBody Music music) throws TrackAlreadyExistsException {
         RequestEntity requestEntity;
     //    try {
             musicService.saveTrack(music);
             return new ResponseEntity<String>("succefully created",HttpStatus.OK);

//         }catch (TrackAlreadyExistsException ex)
//         {
//             return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//
//         }

    }
    @GetMapping("/tracks")
    public ResponseEntity<?> displayTracks()
    {
        List<Music> musicList=musicService.displayTrack();
        return new ResponseEntity<List<Music>>(musicList,HttpStatus.OK);
    }
    @GetMapping("/tracks/{id}")
    public ResponseEntity<?> displayTracksById(@PathVariable int id)
    {
        Music musicList=musicService.displayTracksById(id);
        return new ResponseEntity<Music>(musicList,HttpStatus.OK);
    }
    @DeleteMapping("/tracks/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id)
    {
        musicService.deleteTrack(id);
        return new ResponseEntity<List<Music>>(musicService.displayTrack(),HttpStatus.OK);
    }
    @PutMapping("/tracks/{id}")
    public ResponseEntity<?> updateTracks(@RequestBody Music music,@PathVariable int id) throws TrackNotFoundException {
      //  try{
        Music musicList=musicService.updateTrack(music,id);
        return new ResponseEntity<Music>(musicList,HttpStatus.OK);}
//        catch (TrackNotFoundException ex)
//        {
//            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
//        }
    }


