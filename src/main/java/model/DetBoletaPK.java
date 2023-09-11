package model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
public class DetBoletaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(insertable = false, updatable = false)
	private int num_bol;
	@Column(insertable = false, updatable = false)
	private String id_prod;
}
