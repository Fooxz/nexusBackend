package org.nexus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ComentarioResponse {
    private UUID id;
    private String content;
    private OffsetDateTime createdAt;
    private ComentarioAuthorResponse author;
    private List<RespuestaResponse> replies;
}
