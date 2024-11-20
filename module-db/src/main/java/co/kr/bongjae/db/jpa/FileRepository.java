package co.kr.bongjae.db.jpa;

import co.kr.bongjae.db.model.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
