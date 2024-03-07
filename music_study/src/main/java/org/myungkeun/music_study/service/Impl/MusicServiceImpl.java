package org.myungkeun.music_study.service.Impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.music_study.entity.MusicEntity;
import org.myungkeun.music_study.exception.ResourceNotFoundException;
import org.myungkeun.music_study.payload.MusicDto;
import org.myungkeun.music_study.payload.MusicResponseDto;
import org.myungkeun.music_study.repository.MusicRepository;
import org.myungkeun.music_study.service.MusicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {
    private MusicRepository musicRepository;
    private ModelMapper modelMapper;

    public MusicServiceImpl(MusicRepository musicRepository, ModelMapper modelMapper) {
        this.musicRepository = musicRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public MusicDto createMusic(MusicDto musicDto) {
        MusicEntity music = mapToEntity(musicDto);
        MusicEntity newMusic = musicRepository.save(music);
        MusicDto responseMusic = mapToDto(newMusic);
        return responseMusic;
    }

    @Override
    public MusicResponseDto getAllMusic() {
        List<MusicEntity> musicList = musicRepository.findAll();
        List<MusicDto> contents = musicList.stream()
                .map(music -> mapToDto(music))
                .collect(Collectors.toList());
        MusicResponseDto musicResponse = new MusicResponseDto();
        musicResponse.setContent(contents);
        return musicResponse;
    }

    @Override
    public MusicDto getMusicById(String id) {
        MusicEntity music = musicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
        return mapToDto(music);
    }

    @Override
    public MusicDto updateMusicById(String id, MusicDto musicDto) {
        MusicEntity oldMusic = musicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
        oldMusic.setTitle(musicDto.getTitle());
        oldMusic.setArtist(musicDto.getArtist());
        oldMusic.setCategory(musicDto.getCategory());
        oldMusic.setDescription(musicDto.getDescription());
        MusicEntity updateMusic = musicRepository.save(oldMusic);
        return mapToDto(updateMusic);
    }

    @Override
    public String deleteMusicById(String id) {
        MusicEntity music = musicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
        musicRepository.delete(music);
        return "deleted";
    }

    private MusicDto mapToDto(MusicEntity musicEntity) {
        MusicDto musicDto = modelMapper.map(musicEntity, MusicDto.class);
        return musicDto;
    }

    private MusicEntity mapToEntity(MusicDto musicDto) {
        MusicEntity musicEntity = modelMapper.map(musicDto, MusicEntity.class);
        return musicEntity;
    }
}
