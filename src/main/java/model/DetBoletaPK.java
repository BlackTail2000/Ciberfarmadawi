package model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
public class DetBoletaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int num_bol;
	private String id_prod;
}
