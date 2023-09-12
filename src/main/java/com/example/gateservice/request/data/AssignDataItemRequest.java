package com.example.gateservice.request.data;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssignDataItemRequest {
    private Long dataId;
    private Long userId;
}
