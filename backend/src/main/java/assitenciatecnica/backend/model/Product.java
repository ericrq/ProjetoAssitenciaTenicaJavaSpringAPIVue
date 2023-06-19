package assitenciatecnica.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Pattern(regexp = "^[a-zA-ZÀ-ú ]+$", message = "o nome do produto deve conter apenas letras e espaços")
    @Size(min = 4, max = 32, message = "o nome do produto deve conter entre 4 e 32 caracteres")
    private String product_type;
}
