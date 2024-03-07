package org.myungkeun.music_study.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicResponseDto {
    private List<MusicDto> content;
}
