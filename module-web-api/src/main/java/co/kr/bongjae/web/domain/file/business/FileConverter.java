package co.kr.bongjae.web.domain.file.business;

import co.kr.bongjae.db.model.file.FileEntity;
import co.kr.bongjae.web.common.annotation.Converter;
import co.kr.bongjae.web.domain.file.model.FileDTO;

import java.util.Optional;

@Converter // 컨버터임을 명시해줌
public class FileConverter {

    public FileDTO toDto(FileEntity file) {
        return Optional.ofNullable(file)
                .map(x -> FileDTO.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .originalName(x.getOriginalName())
                        .pathUrl(x.getPathUrl())
                        .size(x.getSize())
                        .extension(x.getExtension())
                        .status(x.getStatus())
                        .registeredAt(x.getRegisteredAt())
                        .build())
                .orElse(null);
    }
}
