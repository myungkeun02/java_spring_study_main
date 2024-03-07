package org.myungkeun.music_study.controller;

import org.myungkeun.music_study.payload.MusicDto;
import org.myungkeun.music_study.payload.MusicResponseDto;
import org.myungkeun.music_study.service.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/music/api")
public class MusicController {
    public MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/create")
    public ResponseEntity<MusicDto> createMusic(@RequestBody MusicDto musicDto) {
        return new ResponseEntity<>(musicService.createMusic(musicDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<MusicResponseDto> getAllMusic() {
        return new ResponseEntity<>(musicService.getAllMusic(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> getMusicById(@PathVariable String id) {
        return new ResponseEntity<>(musicService.getMusicById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusicById(@PathVariable String id) {
        return new ResponseEntity<>(musicService.deleteMusicById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicDto> updateMusicById(@PathVariable String id, @RequestBody MusicDto musicDto) {
        return new ResponseEntity<>(musicService.updateMusicById(id, musicDto), HttpStatus.OK);
    }

}
