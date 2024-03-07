package org.myungkeun.music_study.service;

import org.myungkeun.music_study.payload.MusicDto;
import org.myungkeun.music_study.payload.MusicResponseDto;

public interface MusicService {
    MusicDto createMusic(MusicDto musicDto);
    MusicResponseDto getAllMusic();
    MusicDto getMusicById(String id);
    MusicDto updateMusicById(String id, MusicDto musicDto);
    String deleteMusicById(String id);
}
