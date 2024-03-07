package org.myungkeun.music_study.repository;

import org.myungkeun.music_study.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity, String > {
}
