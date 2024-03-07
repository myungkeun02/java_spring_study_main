package org.myungkeun.music_study.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MusicDto {
    private String title;
    private String artist;
    private String category;
    private String description;
}
