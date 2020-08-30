package com.seller.portal.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long roleId;
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role(String string) {
		this.roleName = string;
	}

}