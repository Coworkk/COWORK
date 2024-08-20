package com.COWORK.COWORKING.dtos.responses;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@RequiredArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
