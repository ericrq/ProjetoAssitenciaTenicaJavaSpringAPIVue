package assitenciatecnica.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name é obrigatório")
    @Pattern(regexp = "^[a-zA-Záàâãéèêíïóôõöúçñ ]{4,32}$", message = "o nome deve conter apenas letras, espaços e assentos, e no maximo 3 nomes")
    @Size(min = 4, max = 32, message = "o nome deve conter entre 4 e 32 caracteres")
    private String name;

    @NotBlank(message = "telefone é obrigatório")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "o telefone deve estar no formato: (XX) XXXXX-XXXX")
    private String phone;

    @NotBlank(message = "Email é obrigatório")
    @Column(nullable = false)
    @Email(message = "Email precisa estar no formato: example@example.com")
    private String email;

    @NotBlank(message = "endereço é obrigatório")
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Záàâãéèêíïóôõöúçñ0-9 -]+$", message = "o endereço deve conter apenas letras, números, espaços e hífen, e acentos")
    @Size(max = 128, message = "o endereço deve conter no máximo 128 caracteres")
    private String address;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF precisa ser no formato XXX.XXX.XXX-XX")
    private String cpf;

    @NotBlank(message = "Defeito é obrigatório")
    @Size(max = 255, message = "o defeito deve conter no máximo 255 caracteres")
    private String defect;

    @NotBlank(message = "Tipo de produto é obrigatório")
    @Size(max = 32, message = "o tipo de produto deve conter no máximo 32 caracteres")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "o tipo do produto deve conter apenas letras e espaços")
    private String product_type;
}
