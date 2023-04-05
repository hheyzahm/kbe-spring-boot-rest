package springboot.kberest.web.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Created 03 04 2023 - 9:44 AM
 * @Author Hazeem Hassan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
