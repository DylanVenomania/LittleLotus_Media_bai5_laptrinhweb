package com.littlelotus.media.dto;

import com.littlelotus.media.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String username;
    private String email;

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
